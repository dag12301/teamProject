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
          @click="listPage(qn.articleId)"
        >
          <td class="col-1">{{ qn.nickname }}</td>
          <td class="col-1">{{ qn.title }}</td>
          <td class="col-5 text-left">
            <span>
              {{ qn.body }}
            </span>
            <span v-if="qn.children > 0">
              [{{qn.children}}]
            </span>
          </td>
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
        <li class="page-item" v-if="this.paging.prev == true" @click="prevBotton(paging.range, paging.rangeSize, this.listSize)">
          <a class="page-link" href="#" aria-label="Previous" >
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <!-- 숫자 순서 버튼 -->
        <li class="page-item" v-for="num in this.getnumSize" :key="num">        
          <a class="page-link" href="#" @click="numPage(num, this.paging.range, this.listSize,this.rangeSize)" >{{num}}</a>
        </li>
        
        <!-- 다음 순서 버튼 -->
        <li class="page-item" v-if="this.paging.next == true" @click="nextBotton(paging.range, paging.rangeSize, this.listSize)">
          <a class="page-link" href="#" aria-label="Next" >
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
      paging:[],
      listSize: 10,     //리스트 수
      rangeSize: 5      //버튼 수
    }
  },
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
    listPage(articleId) {     //페이지 이동
      let params = {
        articleId: articleId,
      }

      authAPI
        .articleVerify(params)    //페이지 검사
        .then(res => {
          console.log(res)
          if(res.data == 100){
            return location.href="/boardList?board=qna&articleId=" + articleId
          }else if(res.data == 250){
            return alert("비공개 입니다");
          }else if(res.data == 400){
            return alert("글 없습니다");
          }else{
            return alert("다시 시도해 주세요");
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    privateToggle(status) {
      //공개 비공개 검사
      if (status === "Y") {
        return true;
      }
    },
    prevBotton(range1, rangeSize, listSize) {
      var page = ((range1 - 2) * rangeSize) + 1;
		  var range = range1 - 1;

      this.numPage(page, range, rangeSize, listSize)
    },
    nextBotton(range1, rangeSize, listSize) {
      console.log("next---------------------------")
      let page = parseInt((range1 * rangeSize)) + 1
		  let range = range1 + 1
    console.log("range1: " + range1)
    console.log(page)
    console.log("range: " + range)
    
    console.log("---------------------------")
      this.numPage(page, range, rangeSize, listSize)
    },

    numPage(page, range, listSize, rangeSize) {           //페이지 번호로 이동 axios
      authAPI
      .getBoardList(2, page, range, listSize, rangeSize)
      .then(res => {
        this.nullCenterQueAn()         //테이터 삭제
        this.nullPagingpagingQueAn()    //페이징 삭제
        console.log("삭제---------------------------")
        console.log("setPagingQueAn : " + this.$store.state.serviceCenter.pagingQueAn)
        console.log("---------------------------")
        
        this.setPagingQueAn(res.data.paging)
        console.log("저장---------------------------")
        console.log("setPagingQueAn : " + this.$store.state.serviceCenter.pagingNotices.next)
        console.log("---------------------------")
        
        res.data.list.forEach(element => {      //vuex에 데이터 넣기
          this.setCenterQueAn(element)   
        });
        
      })
    }
  },
  //고객센터통해 접근할 경우
  mounted() {
    this.paging =  this.getQueAnPaging  
  },
  //버튼 클릭시
  updated() {
    this.paging = this.getQueAnPaging  
    this.listSize = this.paging.listSize,
    this.rangeSize = this.paging.rangeSize
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
