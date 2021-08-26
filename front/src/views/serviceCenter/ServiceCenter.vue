<template>
  <div class="service-center mt-5">
    <div class="service-group mb-5 ">
        <router-link class="btn btn-success me-5 col-2" :to="{ name: 'Notice' }" @click="clickPages(1)" v-if="clickNumber == 1">공지사항</router-link>
        <router-link class="btn btn-warning me-5 col-2" :to="{ name: 'Notice' }" @click="clickPages(1)" v-else>공지사항</router-link>

        <router-link class="btn btn-success mx-5 col-2" :to="{ name: 'Q&A' }" @click="clickPages(2)" v-if="clickNumber == 2">Q&A</router-link>
        <router-link class="btn btn-warning mx-5 col-2" :to="{ name: 'Q&A' }" @click="clickPages(2)" v-else>Q&A</router-link>
        
        <router-link class="btn btn-success ms-5 col-2" :to="{ name: 'FQA' }" @click="clickPages(3)" v-if="clickNumber == 3">FQA</router-link>
        <router-link class="btn btn-warning ms-5 col-2" :to="{ name: 'FQA' }" @click="clickPages(3)" v-else>FQA</router-link>
    </div>

    <router-view ></router-view>

  </div>  
</template>

<script>
import * as authAPI from "@/api/article.js";
import { mapMutations } from 'vuex';

export default {
  data() {
    return {
      clickNumber: null
    }
    
  },
  computed: {},
  methods: {
    ...mapMutations([
      "SET_COUNT_NOTICE",
      "SET_COUNT_QUEAN",
      "SET_COUNT_FQA",
    ]),
    ...mapMutations({
      // 페이지저장
      setCenternotices: "serviceCenter/setCenternotices",  
      setCenterQueAn: "serviceCenter/setCenterQueAn",
      setCenterFQA: "serviceCenter/setCenterFQA",
      //버튼 페이징 저장
      setPagingNotices: "serviceCenter/setPagingNotices",
      setPagingQueAn: "serviceCenter/setPagingQueAn",
      setPagingFQA: "serviceCenter/setPagingFQA",
      //게시물 삭제
      nullCenterNotices: "serviceCenter/nullCenterNotices",
      nullCenterQueAn: "serviceCenter/nullCenterQueAn",
      nullCenterFQA: "serviceCenter/nullCenterFQA",
      //페이징 삭제
      nullPagingpagingQueAn: "serviceCenter/nullPagingpagingQueAn",
      nullPagingNotice: "serviceCenter/nullPagingNotice",
      nullPagingFQA: "serviceCenter/nullPagingFQA"

    }),
    clickPages(num) {
      this.clickNumber = num
    },
    
    getCountNotice(boardId) {     // notice 서버 통신 

      if(this.$store.state.countNotice){
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_NOTICE(false)
      }else{
        return console.log("else 일때" +this.$store.state.countNotice)
      }
    },
    getCountQueAn(boardId) {       // Q&A 서버 통신 
      if(this.$store.state.countQueAn){
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_QUEAN(false)
      }else{
        return console.log("Q&A")
      }
    },
    getCountFQA(boardId) {         // FAQ 서버 통신 
      if(this.$store.state.countFAQ){
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_FQA(false)
        
      }else{
        return console.log("FAQ")
      }
    },

    downAllList(boardId, page, range) {        //리스트 axios 통신 query = boardId  page:페이지  range: 범위  boardId 
      authAPI
      .getBoardList(boardId, page, range)
      .then(res => {
        
        if(boardId == 1){
          this.setPagingNotices(res.data.paging)  
        }else if(boardId == 2){
          this.setPagingQueAn(res.data.paging)
        }else if(boardId == 3){
          this.setPagingFQA(res.data.paging) 
        }
        res.data.list.forEach(el => {      //store에 데이터 넣기 게시판 종류  (1.공지사항, 2.Q&A, 3.FQA)  
          if(boardId == 1){
            this.setCenternotices(el)
            
          }else if(boardId == 2){
            this.setCenterQueAn(el)
           
          }else if(boardId == 3){
            this.setCenterFQA(el)
          }
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteAll(){
      //게시물 삭제
      this.nullCenterNotices()
      this.nullCenterQueAn()
      this.nullCenterFQA()
      //페이징 삭제
      this.nullPagingpagingQueAn()
      this.nullPagingNotice()
      this.nullPagingFQA()
      
    }
    
  },
  
  mounted() {           //DB 통신 하기 boardId 값
    this.getCountNotice(1),
    this.getCountQueAn(2),
    this.getCountFQA(3)
  },
  beforeUnmount() {   //component 종료전 데이터 삭제
    this.deleteAll()
      
  }
}
</script>

<style scoped>

</style>
