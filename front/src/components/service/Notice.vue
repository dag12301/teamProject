<template>
<div class="container" style="width: 1000px;">
  <div class="notice col-12 h-50">
    <ul class="list-group list-group-horizontal" style="border-top: 2px solid gray;">
      <li class="list-group-item noticeTitle" style="width: 100px;"><strong>No.</strong></li>
      <li class="list-group-item noticeTitle" style="width: 700px;"><strong>제목</strong></li>
      <li class="list-group-item noticeTitle" style="width: 200px;"><strong>시간</strong></li>
    </ul>
    <!-- 리스트 시작 -->
    <ul class="list-group list-group-horizontal" 
        style="cursor: pointer; border-top: 2px solid gray;"
        @click="listPage(notice.articleId)"
        v-for="(notice, index) in this.$store.state.serviceCenter.notices"
        :key="notice.id">
      <li class="list-group-item noticeList" style="border: 0px; width: 100px; justify-content: center;">
        <span>
          {{ ++index }}.
        </span>
      </li>
      <li class="list-group-item noticeList" style="border: 0px; width: 700px;">
        <strong style="font-size: 18px;">
          {{ notice.title }}
        </strong>
      </li>
      <li class="list-group-item noticeList" style="border: 0px; width: 200px; justify-content: center;">
        <span>
          {{ notice.regDate }}
        </span>
      </li>
      <li v-if="this.getNoticeTotal == 0" style="font-weight: 700; font-size: 2vh;"> 글이 없습니다.</li>
    </ul>
    <!-- 끝 -->
    <div style="border-top: 2px solid gray;"></div>
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

      let page = parseInt(range1 * rangeSize) + 1;
      let range = range1 + 1;
 
      this.numPage(page, range, listSize, rangeSize);
    },

    numPage(page, range, listSize, rangeSize) {
      //페이지 번호로 이동 axios
      authAPI.getBoardList(1, page, range, listSize, rangeSize).then((res) => {
        this.nullCenterNotices(); //테이터 삭제
        this.nullPagingNotice(); //페이징 삭제
        this.setPagingNotices(res.data.paging);
  
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
.noticeList {
  padding-top: 30px;
  padding-bottom: 30px;
  display: flex;
  align-items: center;
}
.noticeTitle {
  border: 0px;
  padding-top: 15px;
  padding-bottom: 15px;
  font-size: 18px;
}
</style>
