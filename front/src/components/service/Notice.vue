<template>
  <div class="notice col-12 h-50">
    <h2 class="mb-5 font-weight-bold">공지사항</h2>

    <div class="p-1 bg-dark text-white"></div>
    <!-- 리스트 시작 -->
    <ul class="list-group list-group-flush" style="cursor: pointer;  list-style:none;">
      <li
        class="list-group-item"
        @click="listPage(notice.articleId)"
        v-for="notice in this.$store.state.serviceCenter.notices"
        :key="notice.id"
      >
        <span class="position-absolute" style="left: 1vw">[공지]</span>
        <span class="position-absolute" style="left: 6vw"
          >{{ notice.title }}[{{ notice.children }}]</span
        >
        <span class="position-absolute" style="right: 6vw">{{
          notice.regDate
        }}</span>
        <span class="position-absolute" style="right: 4vw">
          <i class="fas fa-eye"></i>
        </span>
        <span class="position-absolute" style="right: 1vw">{{
          notice.hit
        }}</span>
      </li>
      <li v-if="this.getNoticeTotal == 0" style="font-weight: 700; font-size: 2vh"> 글이 없습니다.</li>
    </ul>
    <!-- 끝 -->
    <div class="p-1 bg-dark text-white"></div>
    <!-- 순서 버튼 -->
    <nav
      aria-label="Page navigation example"
      class="mt-5 position-relative .center-block"
      style=""
    >
      <ul class="pagination position-absolute" style="left: 30vw">
        <!-- 이전 순서 버튼 -->
        <li
          class="page-item"
          v-if="this.paging.prev == true"
          @click="prevBotton(paging.range, paging.rangeSize, this.listSize)"
        >
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <!-- 숫자 순서 버튼 -->
        <li class="page-item" v-for="num in this.getnumNoticeSize" :key="num">        
          <a class="page-link" href="#" @click="numPage(num, this.paging.range, this.listSize,this.rangeSize)" v-if="paging.page != num">{{num}}</a>
          <a class="page-link" href="#" @click="numPage(num, this.paging.range, this.listSize,this.rangeSize)" style="background-color: #0d6efd; color: #fff;" v-else>{{num}}</a>
        </li>

        <!-- 다음 순서 버튼 -->
        <li
          class="page-item"
          v-if="this.paging.next == true"
          @click="nextBotton(paging.range, paging.rangeSize, this.listSize)"
        >
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
    <!-- 끝 -->
  </div>
</template>

<script>
import * as authAPI from "@/api/article.js";
import { mapMutations, mapGetters } from "vuex";

export default {
  data() {
    return {
      paging: [],
      listSize: 10, //리스트 수
      rangeSize: 5, //버튼 수
    };
  },
  computed: {
    ...mapGetters({
      getnumNoticeSize: "serviceCenter/getnumNoticeSize",
      getNoticePaging: "serviceCenter/getNoticePaging",
      getNoticeTotal: "serviceCenter/getNoticeTotal"
    }),
  },
  methods: {
    ...mapMutations({
      setCenternotices: "serviceCenter/setCenternotices",
      nullCenterNotices: "serviceCenter/nullCenterNotices",
      nullPagingNotice: "serviceCenter/nullPagingNotice",
      setPagingNotices: "serviceCenter/setPagingNotices",
    }),
    listPage(articleId) {
      //페이지 이동
      console.log(articleId);
      return (this.$router.push({name: 'BoardList', query: { "board": "notice", "articleId": articleId}}))
      //location.href = "/boardList?board=notice&articleId=" + articleId;
    },
    prevBotton(range1, rangeSize, listSize) {
      var page = (range1 - 2) * rangeSize + 1;
      var range = range1 - 1;

      this.numPage(page, range, listSize, rangeSize);
    },
    nextBotton(range1, rangeSize, listSize) {
      console.log("next---------------------------");
      let page = parseInt(range1 * rangeSize) + 1;
      let range = range1 + 1;
      console.log("range1: " + range1);
      console.log(page);
      console.log("range: " + range);

      console.log("---------------------------");
      this.numPage(page, range, listSize, rangeSize);
    },

    numPage(page, range, listSize, rangeSize) {
      //페이지 번호로 이동 axios
      authAPI.getBoardList(1, page, range, listSize, rangeSize).then((res) => {
        this.nullCenterNotices(); //테이터 삭제
        this.nullPagingNotice(); //페이징 삭제
        console.log("삭제---------------------------");
        console.log(
          "setPagingQueAn : " + this.$store.state.serviceCenter.pagingNotices
        );
        console.log("---------------------------");

        this.setPagingNotices(res.data.paging);
        console.log("저장---------------------------");
        console.log(
          "setPagingQueAn : " + this.$store.state.serviceCenter.pagingNotices
        );
        console.log("---------------------------");

        res.data.list.forEach((element) => {
          //vuex에 데이터 넣기
          this.setCenternotices(element);
        });
      });
    },
  },
  //고객센터통해 접근할 경우
  mounted() {
    this.paging = this.getNoticePaging; //this.$store.state.serviceCenter.pagingQueAn
    
  },
  //버튼 클릭시
  updated() {
    this.paging = this.getNoticePaging;
    (this.listSize = this.paging.listSize),
      (this.rangeSize = this.paging.rangeSize);
  },
  beforeUnmount() {   // 종료전 데이터 삭제
    this.$store.commit("serviceCenter/nullCenterNotices")
    this.$store.commit("serviceCenter/nullPagingNotice")
   
  }
};
</script>

<style scoped>
.notice ul li {
  height: 7vh;
  padding-top: 2vh;
  padding-bottom: 2vh;
  font-size: 2vh;
}
</style>
