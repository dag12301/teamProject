<template>
  <div class="notice col-12 h-50">
    <h2 class="mb-5 font-weight-bold">FQA</h2>

    <div class="p-1 bg-dark text-white"></div>
    <!-- 리스트 시작 -->
    <ul class="list-group list-group-flush">
      <li
        class="list-group-item cursor-pointer"
        v-for="notice in this.$store.state.serviceCenter.FQA"
        :key="notice.id"
        @click="listPage(notice.articleId)"
      >
        <span class="position-absolute" style="left: 1vw"
          >[{{ notice.nickname }}]</span
        >
        <span class="position-absolute" style="left: 8vw">
          <span>
            {{ notice.title }}
          </span>
          <span v-if="notice.children > 0"> [{{ notice.children }}] </span>
        </span>
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
      <li v-if="this.getFQATotal == 0" style="font-weight: 700; font-size: 2vh">글이 없습니다.</li>
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
        <li class="page-item" v-for="num in getFQANumSize" :key="num">        
          <a class="page-link" href="#" @click="numPage(num, this.paging.range,this.listSize,this.rangeSize)" v-if="paging.page != num">{{num}}</a>
          <a class="page-link" href="#" @click="numPage(num, this.paging.range,this.listSize,this.rangeSize)" style="background-color: #0d6efd; color: #fff;" v-else>{{num}}</a>
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
      getFQAPaging: "serviceCenter/getFQAPaging",
      getFQANumSize: "serviceCenter/getFQANumSize",
      getFQATotal: "serviceCenter/getFQATotal"
    }),
  },
  methods: {
    ...mapMutations({
      setCenterFQA: "serviceCenter/setCenterFQA",
      nullPagingFQA: "serviceCenter/nullPagingFQA",
      nullCenterFQA: "serviceCenter/nullCenterFQA",
      setPagingFQA: "serviceCenter/setPagingFQA",
    }),

    listPage(articleId) {
      //페이지 이동
      console.log(articleId);
      location.href = "/boardList?board=fqa&articleId=" + articleId;
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
      authAPI.getBoardList(3, page, range, listSize, rangeSize).then((res) => {
        this.nullCenterFQA(); //테이터 삭제
        this.nullPagingFQA(); //페이징 삭제

        this.setPagingFQA(res.data.paging);

        res.data.list.forEach((element) => {
          //vuex에 데이터 넣기
          this.setCenterFQA(element);
        });
      });
    },
  },
  //고객센터통해 접근할 경우
  mounted() {
    console.log(
      "1....srange : " + this.$store.state.serviceCenter.pagingFQA.range
    );
    this.paging = this.getFQAPaging; //this.$store.state.serviceCenter.pagingQueAn
  },
  //버튼 클릭시
  updated() {
    this.paging = this.getFQAPaging; //this.$store.state.serviceCenter.pagingQueAn
    (this.listSize = this.paging.listSize),
      (this.rangeSize = this.paging.rangeSize);
  },
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
