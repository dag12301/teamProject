<template>
  <div class="service-center m-5">
    <div class="service-group mb-5 ">
        <router-link class="btn btn-success me-5 col-2 serviceMenu" :to="{ name: 'Notice' }" @click="clickPages(1)" v-if="this.$store.state.serviceCenters == 1">공지사항</router-link>
        <router-link class="btn btn-warning me-5 col-2 serviceMenu" :to="{ name: 'Notice' }" @click="clickPages(1)" v-else>공지사항</router-link>

        <router-link class="btn btn-success mx-5 col-2 serviceMenu" :to="{ name: 'Q&A' }" @click="clickPages(2)" v-if="this.$store.state.serviceCenters == 2">Q&A</router-link>
        <router-link class="btn btn-warning mx-5 col-2 serviceMenu" :to="{ name: 'Q&A' }" @click="clickPages(2)" v-else>Q&A</router-link>
        
        <router-link class="btn btn-success ms-5 col-2 serviceMenu" :to="{ name: 'FAQ' }" @click="clickPages(3)" v-if="this.$store.state.serviceCenters == 3">FAQ</router-link>
        <router-link class="btn btn-warning ms-5 col-2 serviceMenu" :to="{ name: 'FAQ' }" @click="clickPages(3)" v-else>FAQ</router-link>
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
      pageNumber: 0
    }
    
  },
  computed: {},
  methods: {
    ...mapMutations([
      "SET_COUNT_NOTICE",
      "SET_COUNT_QUEAN",
      "SET_COUNT_FAQ",
    ]),
    ...mapMutations({
      // 페이지저장
      setCenternotices: "serviceCenter/setCenternotices",  
      setCenterQueAn: "serviceCenter/setCenterQueAn",
      setCenterFAQ: "serviceCenter/setCenterFAQ",
      //버튼 페이징 저장
      setPagingNotices: "serviceCenter/setPagingNotices",
      setPagingQueAn: "serviceCenter/setPagingQueAn",
      setPagingFAQ: "serviceCenter/setPagingFAQ",
      //게시물 삭제
      nullCenterNotices: "serviceCenter/nullCenterNotices",
      nullCenterQueAn: "serviceCenter/nullCenterQueAn",
      nullCenterFAQ: "serviceCenter/nullCenterFAQ",
      //페이징 삭제
      nullPagingpagingQueAn: "serviceCenter/nullPagingpagingQueAn",
      nullPagingNotice: "serviceCenter/nullPagingNotice",
      nullPagingFAQ: "serviceCenter/nullPagingFAQ",

      //serviceCenter 상태
      SET_serviceCenters: "SET_serviceCenters"

    }),
    clickPages(num) {   //클릭시
      this.SET_serviceCenters(num)
      if(num === 1){
        
        this.getCountNotice(1)
        

      }else if(num === 2){
        this.getCountQueAn(num)
       

      }else if(num === 3){
        this.getCountFQA(num)
        
      }

    },
    
    getCountNotice(boardId) {     // notice 서버 통신 

      if(this.$store.state.countNotice){
        this.nullCenterNotices()
        this.nullPagingpagingQueAn()
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_NOTICE(false)
      }else{
        return this.SET_COUNT_NOTICE(true)
      }
    },
    getCountQueAn(boardId) {       // Q&A 서버 통신 
      if(this.$store.state.countQueAn){
        this.nullCenterQueAn()
         this.nullPagingNotice()
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_QUEAN(false)
      }else{
        return  this.SET_COUNT_QUEAN(true)
      }
    },
    getCountFAQ(boardId) {         // FAQ 서버 통신 
      if(this.$store.state.countFAQ){
        this.nullCenterFQA()
        this.nullPagingFQA()
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_FQA(false)
      }else{
        return this.SET_COUNT_FQA(true)
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
          this.setPagingFAQ(res.data.paging) 
        }
        res.data.list.forEach(el => {      //store에 데이터 넣기 게시판 종류  (1.공지사항, 2.Q&A, 3.FAQ)  
          if(boardId == 1){
            this.setCenternotices(el)
            
          }else if(boardId == 2){
            this.setCenterQueAn(el)
           
          }else if(boardId == 3){
            this.setCenterFAQ(el)
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
      this.nullCenterFAQ()
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
    if(this.$store.state.serviceCenters == 1){
      this.$router.push({name: 'Notice'})
    }
  },
  updated(){
    this.clickPages(this.$store.state.serviceCenters)
    
  },
  beforeUnmount() {   //component 종료전 데이터 삭제  
    this.deleteAll()
  }
}
</script>

<style scoped>
.serviceMenu {
  font-size: 20px;
  padding: 10px;
}
</style>
