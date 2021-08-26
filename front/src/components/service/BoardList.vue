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
    <thead id="title" style="text-align: center">
      <tr v-if="update == true">
        <th class="col-md-2">{{ list.nickname }}</th>
        <th class="col-md-6">제목: {{ list.title }}</th>
        <th class="col-md-2">등록일:{{ list.regDate }}</th>
        <th class="col-md-2">조회수:{{ list.hit }}</th>
      </tr>
      <tr v-else>
        <th class="col-md-2">{{ list.writerId }}</th>
        <th class="col-md-6">제목: <input type="text" v-model="title" /></th>
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
  <textarea id="textarea" class="input-group" v-model="body" v-else> </textarea>
  <!-- 이미지 -->
  <div class="w-32 h-32 border-2 border-dotted border-blue-500" v-if="update == true">
    <div v-if="images != null" class="w-full h-full flex items-center" >
      <div v-for="image in images" :key="image.articlefileId">
        <img :src="image.name" alt="image.orgName">
      </div>
    </div>
  </div>
  
  <!-- 첨부파일 -->
    <div class="room-deal-information-container" v-else>
        <div class="room-file-upload-wrapper">
          <div v-if="!files.length" class="room-file-upload-example-container">
            <div class="room-file-upload-example">
              <div class="room-file-notice-item room-file-upload-button">
                <div class="image-box">
                  <label for="file">사진 등록</label>
                  <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                </div>
              </div>
            </div>
          </div>
            <div v-else class="file-preview-content-container" >
              <div class="file-preview-container" >
                <div v-for="(file, index) in this.files" :key="index" class="file-preview-wrapper">
                  <div class="file-close-button" @click="fileDeleteButton(index)" >
                    x
                  </div>
                  <img :src="file.name" />
                </div>
                <div class="file-preview-wrapper-upload">
                  <div class="image-box" >
                    <label for="file">추가 사진 등록</label>
                    <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                  </div>       
                </div>
              </div>
            </div>
        </div>
      </div>

  <!-- 수정및목록이동 버튼 -->
  <div class="col-md-12 text-center">
    <span v-if="MYPAGE == true">
      <button
        id="btn"
        type="button"
        class="btn btn-success"
        @click="test"
        v-if="update == true"
      >
        수정
      </button>
      <button class="btn btn-primary" v-else @click="boardUpdate">
        보내기
      </button>

      <button
        id="btn"
        type="button"
        class="btn btn-success"
        @click="listdelete"
      >
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
      <input
        type="text"
        v-model.trim="commentTitle"
        style="background-color: rgba(176, 201, 183, 0.219); text-align: center"
      />
      <button class="btn btn-dark" @click="commentProc">댓글입력</button>
    </div>
    <br /><br /><br />

    <ul style="list-style: none">
      <li v-for="comment in comments" :key="comment.articleId">
        <div id="title">
          <span>{{ comment.nickname }} : </span>
          <span> {{ comment.title }}</span>
          <button
            id="commentdel"
            type="button"
            class="btn btn-light"
            @click="commentDelete(comment.writerId, comment.articleId)"
          >
            삭제
          </button>
        </div>
      </li>
    </ul>
    
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
    //게시물 불러오기
    let query = this.$route.query.articleId; //articleId받기
    this.board = this.$route.query.board; //게시판 종류  (1.공지사항, 2.Q&A, 3.FQA)

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
  methods: {
    ...mapMutations(["SET_MODAL_LOGIN"]),
    //댓글 달기 통신
    commentProc() {
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
          console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          console.log(res);
          if (res.data == 100) {
            alert("입력되었습니다.");
            location.href =
              "/boardList?board=" +
              this.$route.query.board +
              "&articleId=" +
              this.$route.query.articleId;
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
            location.href = "/" + this.board;
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

/* https://loy124.tistory.com/203        */
.main-container {
            width: 1200px;
            height: 400px;
            margin: 0 auto;
        }
        
        .room-deal-information-container {
            margin-top: 50px;
            color: #222222;
            border: 1px solid #dddddd;
        }
        
        .room-deal-information-title {
            text-align: center;
            font-size: 18px;
            line-height: 60px;
            border-bottom: 1px solid #dddddd;
        }
        
        .room-deal-information-content-wrapper {
            min-height: 50px;
            display: flex;
        }
        
        .room-deal-informtaion-content-title {
            font-size: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            width: 150px;
            background-color: #f9f9f9;
        }
        
        .room-deal-information-content {
            width: 100%;
            font-size: 14px;
        }
        
        .room-deal-option-selector {
            display: flex;
            align-items: center;
            padding: 15px;
        }
        
        .room-deal-option-item {
            width: 100px;
            height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 1px solid #cccccc;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .room-deal-option-item:last-child {
            margin-left: 10px;
        }
        
        .room-deal-option-notice {
            margin-left: auto;
            font-size: 14px;
            color: #888888;
        }
        
        .room-deal-option-item-deposit {
            margin-left: 10px;
        }
        
        .room-deal-information-wrapper {
            display: flex;
            flex-direction: column;
        }
        
        .room-deal-information-option {
            padding: 10px;
            display: flex;
            align-items: center;
        }
        
        .room-deal-information-option:last-child {
            border-bottom: 1px solid #dddddd;
        }
        
        .room-deal-information-item-type {
            font-size: 13px;
            color: #fff;
            background-color: #61b6e5;
            min-width: 50px;
            height: 26px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 3px;
        }
        
        .room-deal-information-item-wrapper {
            display: flex;
            align-items: center;
            margin-left: 10px;
            height: 46px;
            width: 100%;
        }
        
        .room-deal-information-item-wrapper>input {
            border: 1px solid #dddddd;
            width: 140px;
            height: 100%;
            padding: 0 15px;
            font-size: 15px;
        }
        
        .room-deal-inforamtion-won {
            margin: 0 10px;
        }
        
        .room-deal-information-example {
            color: #888888;
        }
        
        .room-deal-information-option:not(:first-child) {
            margin-top: 10px;
        }
        
        .room-deal-inforamtion-divide {
            font-size: 22px;
            margin: 0 8px;
            color: #222222;
            font-weight: 100;
        }
        
        .room-deal-close-button-wrapper {
            margin-left: auto;
            cursor: pointer;
        }
        
        .room-deal-close-button {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 22px;
            height: 22px;
            background-color: #666666;
            color: rgb(220, 220, 220);
        }
        
        .room-deal-cliked {
            background-color: rgb(235, 235, 235);
            color: rgb(170, 170, 170);
        }
        
        .room-file-upload-example {
            height: 100%;
        }
        
        .room-write-content-container {
            border-top: 1px solid #dddddd;
            min-height: 260px;
        }
        
        .room-picture-notice {
            margin: 20px;
            padding: 20px 40px;
            border: 1px solid #dddddd;
        }
        
        .file-preview-content-container {
            height: 100%;
        }
        
        .room-file-upload-wrapper {
            margin: 20px;
            border: 1px solid #dddddd;
            background-color: #f4f4f4;
            min-height: 350px;
            font-size: 15px;
            color: #888888;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
        }
        
        .room-file-upload-example-container {
            display: flex;
            align-items: center;
            justify-content: center;
            /* height: 100%;
  width: 100%; */
        }
        
        .room-file-image-example-wrapper {
            text-align: center;
        }
        
        .room-file-notice-item {
            margin-top: 5px;
            text-align: center;
        }
        
        .room-file-notice-item-red {
            color: #ef4351;
        }
        
        .image-box {
            margin-top: 30px;
            padding-bottom: 20px;
            text-align: center;
        }
        
        .image-box input[type='file'] {
            position: absolute;
            width: 0;
            height: 0;
            padding: 0;
            overflow: hidden;
            border: 0;
        }
        
        .image-box label {
            display: inline-block;
            padding: 10px 20px;
            background-color: #232d4a;
            color: #fff;
            vertical-align: middle;
            font-size: 15px;
            cursor: pointer;
            border-radius: 5px;
        }
        
        .file-preview-wrapper {
            padding: 10px;
            position: relative;
        }
        
        .file-preview-wrapper>img {
            position: relative;
            width: 190px;
            height: 130px;
            z-index: 10;
        }
        
        .file-close-button {
            position: absolute;
            /* align-items: center; */
            line-height: 18px;
            z-index: 99;
            font-size: 18px;
            right: 5px;
            top: 10px;
            color: #fff;
            font-weight: bold;
            background-color: #666666;
            width: 20px;
            height: 20px;
            text-align: center;
            cursor: pointer;
        }
        
        .file-preview-container {
            height: 100%;
            display: flex;
            flex-wrap: wrap;
        }
        
        .file-preview-wrapper-upload {
            margin: 10px;
            padding-top: 20px;
            background-color: #888888;
            width: 190px;
            height: 130px;
        }
        
        .room-write-button-wrapper {
            margin-top: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #222222;
        }
        
        .room-write-button-wrapper>div {
            width: 160px;
            height: 50px;
            border: 1px solid #dddddd;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 15px;
            cursor: pointer;
        }
        
        .room-write-button {
            margin-left: 15px;
            color: #fff;
            background-color: #1564f9;
        }
        
        .room-write-button:hover {
            opacity: 0.8;
        }
</style>
