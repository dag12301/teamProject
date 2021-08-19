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
      <tr>
        <th class="col-md-2">{{ list.writerId }}</th>
        <th class="col-md-6">제목: {{ list.title }}</th>
        <th class="col-md-2">등록일:{{ list.regDate }}</th>
        <th class="col-md-2">조회수:{{ list.hit }}</th>
      </tr>
    </thead>
  </table>
  <!-- 본문 -->
  <div id="textarea" class="input-group">
    <div
      style="text-align: center"
      class="form-control"
      aria-label="With textarea"
    >
      {{ list.body }}
    </div>
  </div>

  <!-- 수정및목록이동 버튼 -->
  <div class="col-md-12 text-center">
    <!-- Button trigger modal -->
    <button
      type="button"
      class="btn btn-primary"
      data-bs-target="#exampleModal"
      @click="MADAL_REGISTER"
    >
      모달</button
    >{{ modalToggle }}
    <button id="btn" type="button" class="btn btn-success" @click="test">
      수정
    </button>
    <button id="btn" type="button" class="btn btn-success" @click="listdelete">
      삭제
    </button>
    <router-link class="btn btn-danger" :to="{ path: this.board }"
      >목록</router-link
    >
  </div>
  <br /><br />
  <!-- 댓글 -->
  <div id="comment" class="col-md-8 text-right">
    <button id="commentBtn" type="button" class="btn btn-dark">댓글입력</button>
    <br /><br />
    <ul style="list-style: none">
      <li>
        <h2
          style="
            background-color: rgba(176, 201, 183, 0.219);
            text-align: center;
          "
        >
          댓글
        </h2>
      </li>
      <li>
        <div id="title">
          <span>닉네임: </span>
          <span>내용</span>
          <button id="commentdel" type="button" class="btn btn-light">
            삭제
          </button>
        </div>
      </li>
      <li>
        <div id="title">
          <span>마징가: </span>
          <span>내용없음</span>
          <button
            id="commentdel"
            type="button"
            data-bs-toggle="modal"
            class="btn btn-light"
          >
            삭제
          </button>
        </div>
      </li>
      <li>
        <div id="title">
          <span>홍길동: </span>
          <span>됐다!</span>
          <button id="commentdel" type="button" class="btn btn-light">
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

export default {
  components: {
    modal,
  },
  data() {
    return {
      modalToggle: false,
      board: null,
      list: {},
    };
  },
  created() {
    //게시물 불러오기
    let query = this.$route.query.articleId; //articleId받기
    this.board = this.$route.query.board; //게시판 종류  (1.공지사항, 2.Q&A, 3.FQA)

    authAPI //통신코드
      .list(query)
      .then((res) => {
        this.list = res.data; //list에  DB데이터 박기
      });
  },
  methods: {
    test() {
      console.log(String(this.list.writerId)),
        console.log(String(this.list.articleId));
    },

    listdelete() {
      //삭제 코드

      let params = {
        //삭제에 필요한 데이터들
        writerId: String(this.list.writerId),
        articleId: String(this.list.articleId),
      };

      authAPI //삭제 통신
        .listDelete(params)
        .then((res) => {
          alert("삭제되었습니다.");
          console.log(res);
          location.href = "/";
        });
    },
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
