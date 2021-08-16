<template>
  <div class="service-center">
   
    
    <div class="service-group mb-5 ">
        
        <router-link class="btn btn-success me-5 col-2" :to="{ name: 'Notice' }" @click="test">공지사항</router-link>
        
        <router-link class="btn btn-success mx-5 col-2" :to="{ name: 'Q&A' }">Q&A</router-link>
        
        <router-link class="btn btn-success ms-5 col-2" :to="{ name: 'FQA' }">FQA</router-link>
        
      
      
    </div>

    <router-view></router-view>
  </div>  
</template>

<script>
import instance from "@/api/http"

export default {
  components: {
    
  },
  methods: {
    test () {
       instance.get("/serviceCenter/notice")
       .then(res => {
         console.log(res, "성공")
       })
       .catch(err => {
         console.log(err, "에러나옴")
       })
       .then(() => {
         console.log("실행")
       })
    }
  }
}
</script>

<style scoped>


  /* .service-group button{
    height: 4vh;
    width: 10vw;
  } */
</style>