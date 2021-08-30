<template>
<div class="container" style="width: 1000px;">
  <br /><br />
  <!-- 신고버튼 -->
  <div class="boardDelButtom">
    <button type="button" class="btn btn-danger" style="margin-left:10px;">신고</button>
    <div v-if="MYPAGE == true">
      <button type="button" class="btn btn-primary" style="margin-right:20px;">수정</button>
      <button
        id="btn"
        type="button"
        class="btn btn-danger"
        @click="listdelete"
      >
        삭제
      </button>
    </div>
  </div>
  <br />
  <!-- 타이틀 -->
  <table style="border: 2px solid gray;">
    <thead id="title" style="text-align: center">
      <tr style="border-bottom: 1px solid gainsboro;">
        <th class="boardTitle" colspan='3' style="text-align: left; padding: 20px;">
          <span>구분</span><span style="padding-left:50px; font-size: 25px;">{{ list.title }}</span>
        </th>
      </tr>
      <tr style="height:40px;">
        <th class="col-md-5" style="text-align: left; padding-left: 20px;">{{ list.nickname }}</th>
        <th class="col-md-2"><i class="far fa-clock"></i>&nbsp;&nbsp;{{ list.regDate }}</th>
        <th class="col-md-1"><i class="far fa-eye"></i>&nbsp;&nbsp;{{ list.hit }}</th>
      </tr>
    </thead>
  </table>
  <!-- 본문 -->
  <div id="textarea" class="input-group" style="text-align: left;">
    <div class="form-control" style="padding:30px;">
    <!-- 이미지 -->
      <div v-if="images != null" class="w-full h-full flex items-center">
        <div v-for="image in images" :key="image.articlefileId"  style="padding-bottom:30px;">
          <img :src="image.name" alt="image.orgName">
        </div>
      </div>
    <!-- 이미지 끝 -->
      {{ list.body }}
    </div>
  </div>
  <br />
  <!-- 목록이동 버튼 -->
  <router-link class="btn btn-primary" :to="{ path: this.board }">
    목록
  </router-link>
  <br /><br />
  <!-- 댓글 -->
  <div>
    <ul class="list-group" style="list-style: none">
      <li class="list-group-item commentList" v-for="comment in comments" :key="comment.articleId">
        <div class="commentListTitle">
          <div>
            <strong style="padding-right: 10px; font-size:18px;">{{ comment.nickname }}</strong>
            ({{ comment.regDate }})
          </div>
          <div>
            <span v-if="MYPAGE == true">
              <button type="button" class="btn btn-outline-dark" style="padding:1px; margin-right:10px; font-size:15px;">수정</button>
              <button type="button" class="btn btn-outline-dark" style="padding:1px; margin-right:10px; font-size:15px;">삭제</button>
            </span>
            <button type="button" class="btn btn-outline-dark" style="padding:1px; font-size:15px;">신고</button>
          </div>
        </div>
        <div style="text-align: left;">
          {{ comment.title }}
        </div>
      </li>
      <li class="list-group-item commentInput" style="background-color:gainsboro;">
        <textarea class="form-control" v-model.trim="commentTitle" id="floatingTextarea2" style="resize: none; width:800px; height:80px;"></textarea>
        <button class="btn btn-light" style="width:100px; height:80px;" @click="commentProc1">댓글입력</button>
      </li>
    </ul>
  </div>
</div>
</template>

<script>
import * as authAPI from "@/api/article.js";

import { mapMutations } from 'vuex';

export default {
 
  data() {
    return {
      //삭제
      modalToggle: false,
      //조회
      board: null,
      list: {},
      MYPAGE: null,//작성자 일때 true 토글
      images: '', //이미지 불러오기
      //댓글
      comments: null, //댓글 리스트
      //수정
      title: null,
      body: null,
      update: true,//수정 토글

      imageDelete: [],  //삭제 이미지
      files: [], //업로드용 파일
    
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
      
      //댓글 보내기
      commentTitle: null,
    };
  },
  created() {
    this.boardListPage()
  },
  
  methods: {
    ...mapMutations(["SET_MODAL_LOGIN", "SET_serviceCenters"]),
    //화면 통신
    boardListPage() {
    //게시물 불러오기
      let query = this.$route.query.articleId; //articleId받기
      this.board = this.$route.query.board; //게시판 종류  (1.공지사항, 2.Q&A, 3.FQA)
      if(this.board == "notice"){
        this.SET_serviceCenters(1)
      }else if(this.board == "qna"){
        this.SET_serviceCenters(2)
      }else if(this.board == "fqa"){
        this.SET_serviceCenters(3)
      }
    
      authAPI //통신코드
        .list(query)
        .then((res) => {
          console.log(res.data.articleImageFile);
          this.list = res.data.article; //list에  DB데이터 박기
          this.MYPAGE = res.data.MYPAGE;
          this.comments = res.data.list;
          this.images = res.data.articleImageFile; //이미지
        });
    },
    //댓글 달기 통신
    commentProc1() {
      if (this.commentTitle === "" || this.commentTitle == null) {
        return alert("글을 입력하세요");
      }
      let params = {
        title: this.commentTitle,
        parantId: this.list.articleId,
      };
      authAPI
        .commentProc(params)
        .then((res) => {
          console.log(res);
          if (res.data == 100) {
            alert("입력되었습니다.");
              this.boardListPage
              return this.commentTitle=''
              
          } else if (res.data == 200) {
            alert("비회원은 권한이 없습니다.");
            this.SET_MODAL_LOGIN(true);
          } else if (res.data == 250) {
            alert("작성자가 아닙니다.");
            location.href = "/" + this.board;
          } else if (res.data == 300) {
            alert("DB문제입니다");
            location.href = "/" + this.board;
          } else if (res.data == 400) {
            alert("글없습니다");
            location.href = "/" + this.board;
          } else {
            alert("서버에 문제가 있습니다.");
            location.href = "/" + this.board;
          }
        })
        .catcherr((err) => {
          console.log(err);
        });
    },
    //이미지
    imageAddUpload() {
      this.files.push (
          //이미지 업로드
          {   //실제 파일
              file: this.$refs.files.files[0],
              //이미지 프리뷰
              name: URL.createObjectURL(this.$refs.files.files[0]),
              //삭제및 관리를 위한 number
          }
      )
    },
    fileDeleteButton(index) {
        //보낼 파일에서 데이터 삭제
        if(this.files[index].file > 0){
          this.imageDelete.push(this.files[index].file)
        }
        this.files.pop(index)
        // console.log(this.files);
    },
    //댓글 삭제
    commentDelete(writerId, articleId) {
      let params = {
        //삭제에 필요한 데이터들
        writerId: writerId,
        articleId: articleId,
      };
      console.log("2222222222222222222222222222222");
      console.log(params);
      authAPI //삭제 통신
        .listDelete(params)
        .then((res) => {
          console.log(res);
          if (res.data == 100) {
            alert("삭제되었습니다.");
            location.href =
              "/boardList?board=" +
              this.$route.query.board +
              "&articleId=" +
              this.$route.query.articleId;
          } else if (res.data == 200) {
            alert("비회원은 권한이 없습니다.");
            this.SET_MODAL_LOGIN(true);
            //location.href = "/"+this.board;
          } else if (res.data == 250) {
            alert("작성자가 아닙니다.");
            //location.href = "/"+this.board;
          } else if (res.data == 300) {
            alert("DB문제입니다");
            //location.href = "/"+this.board;
          } else if (res.data == 400) {
            alert("글없습니다");
            location.href = "/" + this.board;
          } else {
            alert("서버에 문제가 있습니다.");
            location.href = "/" + this.board;
          }
        });
    },
    //수정 페이지 버튼
    test() {
      this.update = false             //버튼 상태
      this.title = this.list.title,  //title
      this.body = this.list.body   //body 
      //this.files = this.images      //이미지 이동
      for(let i = 0; i < this.images.length; i++)
      {
        let image = {name: this.images[i].name,
                     file: this.images[i].fileId}
        this.files.push(image)
      }
    },
    //수정-----------------------
    async boardUpdate() {
      if (this.title === "" || this.title == null) {
        return alert("제목을 입력하세요");
      }
      if (this.body === "" || this.body == null) {
        return alert("내용을 입력하세요");
      }
      let params = {
        //수정 필요한 데이터들
        articleId: this.list.articleId,
        title: this.title,
        body: this.body,
        writerId: this.list.writerId,
        imageDelete: this.imageDelete//삭제 이미지
      }
      let articleId = 0

      await authAPI
        .update(params)
        .then(res => {
          console.log(res)
          if(res.data > 0){
            articleId = res.data
          }
          if(res.data == "no"){
            alert("비회원은 권한이 없습니다.")
            this.SET_MODAL_LOGIN(true)
          }else if(res.data == "ok"){
            return alert("수정 되었습니다.")
          }else if(res.data == 0){
            return alert("다시 로그인 해주세요")
          }
        })
        .catch(err => {
          console.log(err)
          console.log(params)
        })

        
        //이미지 있는지 확인
      if( this.files.length > 0){
         
        let form = new FormData()
        let image
        
        for(let i = 0 ; i < this.files.length; i++)
        {
          image = this.files[i].file
          
          //이미지 업로드 articleId = res.data
          if(!(image > 0)){
            form.append('image', image)
          }
        }

        //파일 보내기
        authAPI
        .write12(form, articleId)
        .then( res => {
          if(res.data == 100){
            alert("글을 작성하였습니다")
            location.href = "/" + this.board;
          }else if(res.data == 300){
            alert("글 작성 중 오류가 발생습니다")
          }else if(res.data == 400){
            alert("글 작성을 실패했습니다")
          }else{  
            alert("글 오류")
          }
        })
        .catch( err => {
          console.log(err)
          console.log("에러")
          })
      }else{
        //이지미 없을 때
        alert("글을 작성하였습니다")
            this.boardListPage()
      }

    },

    listdelete() {
      //삭제 코드
      let params = {
        //삭제에 필요한 데이터들
        writerId: this.list.writerId,
        articleId: this.list.articleId,
      };


      authAPI //삭제 통신
        .listDelete(params)
        .then((res) => {
          if(res.data == 100){
          alert("삭제되었습니다.")
          location.href = "/" + this.$route.query.board;
          }else if(res.data == 200){
            alert("비회원은 권한이 없습니다.")
            this.SET_MODAL_LOGIN(true)
          location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
          }else if(res.data == 250){
            alert("작성자가 아닙니다.")
          location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
          }else if(res.data == 300){
            alert("DB문제입니다")
          location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
          }else if(res.data == 400){
          alert("글없습니다")
          location.href = "/"+this.board;
          }else{
            alert("서버에 문제가 있습니다.")
          location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
          }
        })
        .catch(err => {
          console.log(err)
        }) 
    },
  },
};
</script>

<style scoped>
.boardTitle{
  display: flex;
  align-items: center;
}
.boardDelButtom {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.commentList {
  display: flex;
  flex-direction: column;
}
.commentListTitle {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.commentInput {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>