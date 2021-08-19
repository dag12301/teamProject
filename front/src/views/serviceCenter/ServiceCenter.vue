<template>
  <div class="service-center">
   
    
    <div class="service-group mb-5 ">
        
        <router-link class="btn btn-success me-5 col-2" :to="{ name: 'Notice' }" >공지사항</router-link>
        
        <router-link class="btn btn-success mx-5 col-2" :to="{ name: 'Q&A' }" >Q&A</router-link>
        
        <router-link class="btn btn-success ms-5 col-2" :to="{ name: 'FQA' }">FQA</router-link>
      
    </div>


    <router-view ></router-view>
  </div>  
</template>

<script>
import * as authAPI from "@/api/article.js";
import { mapMutations } from 'vuex';

export default {
  data () {
    return {
      articleId: null
    }
    
  },
  computed: {

},
  methods: {
    ...mapMutations([
      "serviceCenter/setCenterList","SET_COUNT_NOTICE", "SET_COUNT_QUEAN", "SET_COUNT_FQA"
    ]),
    getCountNotice(boardId) {     // notice 통신 
      if(this.$store.state.countNotice){
       
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_NOTICE(false)
        
      }else{
        return console.log("else 일때" +this.$store.state.countNotice)
      }
    },
    getCountQueAn(boardId) {       // Q&A 통신 
      if(this.$store.state.countQueAn){
         console.log("if 일때" +this.$store.state.countQueAn)
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_QUEAN(false)
        
      }else{
        return console.log("Q&A")
      }
    },
    getCountFQA(boardId) {         // FAQ 통신 
      if(this.$store.state.countFAQ){
        return this.downAllList(boardId, 1, 1), this.SET_COUNT_FQA(false)
        
      }else{
        return console.log("FAQ")
      }
    },

    downAllList(boardId, page, range) {        //리스트 axios 통신 query = boardId  page:페이지  range: 범위  boardId 
      authAPI
      .getBoardList(boardId, page, range)
      //.allList(query)     //페이지 전체 불러오기 list
      .then(res => {
        console.log(res)
        console.log("1")
        console.log(res.data)
        console.log("2")
        console.log(res.data.paging)
        console.log("3")
        console.log(res.data.list)
        console.log("4")
        //페이징 vuex에 넣기
        if(boardId == 1){
          this.$store.commit('serviceCenter/setPagingNotices', res.data.paging)
        }else if(boardId == 2){
          this.$store.commit('serviceCenter/setPagingQueAn', res.data.paging)
        }else if(boardId == 3){
          this.$store.commit('serviceCenter/setPagingFQA', res.data.paging)
        }
        
        res.data.list.forEach(el => {      //store에 데이터 넣기 게시판 종류  (1.공지사항, 2.Q&A, 3.FQA)
          if(boardId == 1){
            this.$store.commit('serviceCenter/setCenternotices', el)
          }else if(boardId == 2){
            this.$store.commit('serviceCenter/setCenterQueAn', el)
          }else if(boardId == 3){
            this.$store.commit('serviceCenter/setCenterFQA', el)
          }
        })
      })
      .catch(err => {
        console.log(err)
      })
    }
    
  },
  mounted() {           //DB 통신 하기 boardId 값
    this.getCountNotice(1),
    this.getCountQueAn(2),
    this.getCountFQA(3)
  }
}
</script>

<style scoped>


  /* .service-group button{
    height: 4vh;
    width: 10vw;
  } */
</style>