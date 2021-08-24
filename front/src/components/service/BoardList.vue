<template>
  <h2 style="text-align: center">게시물</h2>
  <br /><br /><br />
  <!-- 신고버튼 -->
  <button
    style="float: right; margin: 10px"
    type="button"
    class="btn btn-warning"
  >
    신고하기
  </button>
  <!-- 타이틀 -->

  <table border="2" class="table">
    <thead id="title" style="text-align: center" >
      <tr v-if="update == true">
        <th class="col-md-2">{{ list.nickname }}</th>
        <th class="col-md-6">제목: {{ list.title }}</th>
        <th class="col-md-2">등록일:{{ list.regDate }}</th>
        <th class="col-md-2">조회수:{{ list.hit }}</th>
      </tr>
      <tr v-else>
        <th class="col-md-2">{{ list.writerId }}</th>
        <th class="col-md-6">제목: <input type="text" v-model="title"></th>
        <th class="col-md-2">등록일:{{ list.regDate }}</th>
        <th class="col-md-2">조회수:{{ list.hit }}</th>
      </tr>
    </thead>
  </table>
  <!-- 본문 -->
  <div id="textarea" class="input-group" v-if="update == true">
    <div
      style="text-align: center"
      class="form-control"
      aria-label="With textarea"
    >
      {{ list.body }}
    </div>
  </div>
  <textarea id="textarea" class="input-group" v-model="body" v-else>
    
  </textarea>
  <!-- 이미지 -->
   <div class="w-32 h-32 border-2 border-dotted border-blue-500">
  <div v-if="images != null" class="w-full h-full flex items-center" >
    <div v-for="image in images" :key="image.articlefileId">
      <img :src="image.name" alt="image.orgName">
    </div>
    
  </div>
  
</div>

  <!-- 수정및목록이동 버튼 -->
  <div class="col-md-12 text-center">
    <span v-if="MYPAGE == true">
      <button id="btn" type="button" class="btn btn-success" @click="test" v-if="update == true">
        수정
      </button>
      <button class="btn btn-primary" v-else @click="boardUpdate">
        보내기
      </button>
      
      <button id="btn" type="button" class="btn btn-success" @click="listdelete">
        삭제
      </button>
    </span>
    
    <router-link class="btn btn-danger" :to="{ path: this.board }"
      >목록</router-link
    >
  </div>
  <br /><br />
  <!-- 댓글 -->
  <div id="comment" class="col-md-8 text-right">
    <div>
      <input type="text" v-model.trim ="commentTitle" style="background-color: rgba(176, 201, 183, 0.219);
                                text-align: center;" />
      <button class="btn btn-dark" @click="commentProc">댓글입력</button>
    </div>
    <br /><br /><br/>
    
    <ul style="list-style: none">
      <li v-for="comment in comments" :key="comment.articleId">
        <div id="title">
          <span>{{comment.nickname}} : </span>
          <span>  {{comment.title}}</span>
          <button id="commentdel" type="button" class="btn btn-light" @click="commentDelete(comment.writerId, comment.articleId)">
            삭제
          </button>
        </div>
      </li>
     
    </ul>
    <modal v-if="modalToggle == ture" />
  </div>
</template>

<script>
import * as authAPI from "@/api/article.js";
import modal from "@/components/modal/Delete_list.vue";
import { mapMutations } from 'vuex';

export default {
  components: {
    modal,
  },
  data() {
    return {
      //삭제
      modalToggle: false,
      //조회
      board: null,
      list: {},
      MYPAGE: null,//작성자 일때 true 토글
      images: '', //이미지
      //댓글
      comments: null,//댓글 리스트
      //수정
      title: null,
      body: null,
      update: true,//수정 토글
      //댓글 보내기
      commentTitle: null
    };
  },
  created() {
    //게시물 불러오기
    let query = this.$route.query.articleId; //articleId받기
    this.board = this.$route.query.board; //게시판 종류  (1.공지사항, 2.Q&A, 3.FQA)

    authAPI //통신코드
      .list(query)
      .then((res) => {
        
        console.log(res.data.articleImageFile)
        this.list = res.data.article //list에  DB데이터 박기
        this.MYPAGE = res.data.MYPAGE
        this.comments = res.data.list
        this.images = res.data.articleImageFile//이미지
      });
  },
  methods: {
    ...mapMutations([
      "SET_MODAL_LOGIN"
    ]),
    //댓글 달기 통신
    commentProc() {
      if(this.commentTitle === "" || this.commentTitle == null){
        return alert("글을 입력하세요")
      }
      let params={
        title: this.commentTitle,
        parantId: this.list.articleId
      }
      authAPI
      .commentProc(params)
      .then(res => {
        console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        console.log(res)
        if(res.data == 100){
        alert("입력되었습니다.")
        location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
        }else if(res.data == 200){
          alert("비회원은 권한이 없습니다.")
          this.SET_MODAL_LOGIN(true)
        }else if(res.data == 250){
          alert("작성자가 아닙니다.")
        location.href = "/"+this.board;
        }else if(res.data == 300){
          alert("DB문제입니다")
        location.href = "/"+this.board;
        }else if(res.data == 400){
          alert("글없습니다")
        location.href = "/"+this.board;
        }else{
          alert("서버에 문제가 있습니다.")
        location.href = "/"+this.board;
        }
      })
      .catcherr(err => {
        console.log(err)
      })
    },
    //댓글 삭제
    commentDelete(writerId, articleId) {
      let params = {
        //삭제에 필요한 데이터들
        writerId: writerId,
        articleId: articleId,
      }
      console.log("2222222222222222222222222222222")
      console.log(params)
      authAPI //삭제 통신
        .listDelete(params)
        .then((res) => {
          console.log(res);
          if(res.data == 100){
          alert("삭제되었습니다.")
          location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
          }else if(res.data == 200){
            alert("비회원은 권한이 없습니다.")
            this.SET_MODAL_LOGIN(true)
          //location.href = "/"+this.board;
          }else if(res.data == 250){
            alert("작성자가 아닙니다.")
          //location.href = "/"+this.board;
          }else if(res.data == 300){
            alert("DB문제입니다")
          //location.href = "/"+this.board;
          }else if(res.data == 400){
          alert("글없습니다")
          location.href = "/"+this.board;
          }else{
            alert("서버에 문제가 있습니다.")
          location.href = "/"+this.board;
          }
        });
    },
    //수정 페이지 버튼 
    test() {
      this.update = false
      this.title = this.list.title,
      this.body = this.list.body
    },
    //수정-----------------------
    boardUpdate() {
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
        writerId: this.list.writerId
      }
      authAPI
        .update(params)
        .then(res => {
          console.log(res)
          if(res.data == 100){
          alert("수정되었습니다.")
          location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
          }else if(res.data == 200){
            alert("비회원은 권한이 없습니다.")
            this.SET_MODAL_LOGIN(true)
          //location.href = "/"+this.board;
          }else if(res.data == 250){
            alert("작성자가 아닙니다.")
          //location.href = "/"+this.board;
          }else if(res.data == 300){
            alert("DB문제입니다")
          //location.href = "/"+this.board;
          }else if(res.data == 400){
          alert("글없습니다")
          location.href = "/"+this.board;
          }else{
            alert("서버에 문제가 있습니다.")
          location.href = "/"+this.board;
          }
        })
        .catch(err => {
          console.log(err)
          console.log(params)
        })
    },

    listdelete() {      //삭제 코드
      let params = {
        //삭제에 필요한 데이터들
        writerId: this.list.writerId,
        articleId: this.list.articleId,
      }

      authAPI //삭제 통신
        .listDelete(params)
        .then((res) => {
          console.log(res);
          if(res.data == 100){
          alert("삭제되었습니다.")
          location.href = "/boardList?board=" + this.$route.query.board + "&articleId=" + this.$route.query.articleId;
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
        });
    },
    //모달 열림 메서드
    MADAL_REGISTER() {
      if (this.modalToggle == false) {
        return (this.modalToggle = true);
      }
      this.modalToggle = false;
      console.log(this.modalToggle);
    },
  },
};
</script>

<style scoped>
#textarea {
  height: 500px;
  margin: 5px;
  resize: none;
}
#title {
  background-color: rgba(176, 201, 183, 0.219);
}
#btn {
  margin: 15px;
}
#comment {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
#title {
  height: 46px;
  border-style: solid;
}
li {
  margin: 10px;
}
#commentBtn {
  position: absolute;
  left: 93%;
  transform: translateX(-50%);
}
#commentdel {
  width: 80px;
  float: right;
}
</style>
