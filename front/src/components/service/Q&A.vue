<template>
  <div>
    <h2>Q&A</h2>
    <div id="Search" class="input-group mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="여기다 글쓰고 검색하셈"
        aria-label="Recipient's username"
        aria-describedby="button-addon2"
      />
      <button
        class="btn btn-outline-secondary"
        type="button"
        id="button-addon2"
      >
        검색
      </button>
    </div>
    <!-- 글쓰기 -->
    <div class="">
      <button type="button" class="btn btn-primary">내글조회</button>
      <router-link class="btn btn-primary" :to="{ name: 'WriteForm' }"
        >글쓰기</router-link
      >
    </div>

    <br />
    <!-- 리스트 -->
    <table class="table table-striped">
      <tbody>
        <tr
          v-for="(qn, index) in this.$store.state.serviceCenter.queAn"
          :key="index"
          @click="listPage(qn.articleId, qn.status)"
        >
          <td class="col-1">{{ index }}</td>
          <td class="col-1">{{ qn.title }}</td>
          <td class="col-5 text-left">{{ qn.body }}</td>
          <td class="col-2">{{ qn.regDate }}</td>
          <td class="col-1">{{ qn.hit }}</td>
          <td class="col-1" v-if="privateToggle(qn.status)">
            <i class="fas fa-lock-open"></i>
          </td>
          <td class="col-1" v-else>
            <i class="fas fa-lock"></i>
          </td>
        </tr>
      </tbody>
    </table>

      <!-- 순서 버튼 -->
    <nav aria-label="Page navigation example" class="mt-5 position-relative .center-block" style="">
      <ul class="pagination position-absolute" style="left: 30vw" >
        <!-- 이전 순서 버튼 -->
        <li class="page-item" v-if="this.$store.state.serviceCenter.pagingQueAn.prev == true">
          <a class="page-link" href="#" aria-label="Previous" @click="fn_prev(paging.range, paging.rangeSize)">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <!-- 숫자 순서 버튼 -->
        <li class="page-item" v-for="num in this.getnumSize" :key="num">        
          <a class="page-link" href="#" @click="numPage(num, this.$store.state.serviceCenter.pagingQueAn.range)" >{{num}}</a>
        </li>
        
        <!-- 다음 순서 버튼 -->
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next" @click="fn_next(paging.range, paging.rangeSize)">
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
import { mapMutations, mapGetters  } from 'vuex';

export default {
  data () {
    return {
      paging:[]
    }
  },
  ...mapMutations([
      
      
    ]),
  computed: {
    ...mapGetters({
      getnumSize: "serviceCenter/getnumSize",
      getQueAnPaging: "serviceCenter/getQueAnPaging"
      
      
    })
  },

  
  methods: {
    ...mapMutations({
    setCenterQueAn: 'serviceCenter/setCenterQueAn', 
    nullPagingpagingQueAn: "serviceCenter/nullPagingpagingQueAn",
    nullCenterQueAn: "serviceCenter/nullCenterQueAn",
    setPagingQueAn: "serviceCenter/setPagingQueAn"
    }),
    listPage(articleId, status) {     //페이지 이동
      if(this.privateToggle(status)){
        return location.href="/boardList?board=qna&articleId=" + articleId
      }
      return alert("비공개 입니다");
    },
    privateToggle(status) {
      //공개 비공개 검사
      if (status === "b") {
        return true;
      }
    },
    fn_prev( range1, rangeSize1) {       //이전버튼
      let page = ((range1 - 2) * rangeSize1) + 1
      let range = range1 - 1
      if(range < this.$store.state.serviceCenter.originRange[2])
      {
        range = this.$store.state.serviceCenter.originRange[2]
      }
      console.log("prev---------------------------")
      console.log(range1)
      console.log("---------------------------")
      this.numPage(page, range)
    },
    fn_next(range1, rangeSize1) {       ////다음버튼
      console.log("next---------------------------")
      console.log(range1)
      console.log("---------------------------")
      var page = parseInt((range1 * rangeSize1)) 
      var range = parseInt(range1) + parseInt(1)//this.$store.state.serviceCenter.originRange[2]
      console.log("---------------------------" + range)
      this.numPage(page, range)
    },
    numPage(page, range) {           //페이지 번호로 이동 axios
      authAPI
      .getBoardList(2, page, range)
      .then(res => {
        this.nullCenterQueAn()         //테이터 삭제
        this.nullPagingpagingQueAn()
        console.log(res.data.paging)
        this.setPagingQueAn(res.data.paging)
        console.log("영기이"+ this.$store.state.serviceCenter.pagingQueAn.range)
        res.data.list.forEach(element => {      //vuex에 데이터 넣기
          this.setCenterQueAn(element)   
        });
        
      })
    }
  },
  mounted() {
    console.log("---------------------------")
      console.log(this.$store.state.serviceCenter.pagingQueAn.prev)
      console.log("---------------------------")
    
    this.paging = this.getQueAnPaging
  }
}
</script>

<style scoped>
#Search {
  width: 35%;
  left: 35%;
}
button {
  width: 100px;
  float: right;
}
</style>
