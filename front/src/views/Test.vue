<template>
  <div>
    
    
      <button @click="test">notice</button>
    <form  method="get">
    아이디:<input type="text" v-model="userId" name="userId">
    <button @click.prevent="test">ddd</button>
    </form>
    <button @click="test2">Q&A</button>
    <button @click="test3">FQA</button>
    {{userId}}
                
</div>
</template>

<script>

import instance from "@/api/http"


export default {
  data () {
    return {
      userId: "123"
    }
  },
  methods: {
    test () {
       instance.get("notice")
       .then(res => {
         console.log(res, "성공")
       })
       .catch(err => {
         console.log(err, "에러나옴")
       })
       .then(() => {
         console.log("실행")
       })
    },
    test2 () {
      instance.get("/q&a")
      .then(res => {
        console.log(res, "성공")
      })
      .catch(err => {
         console.log(err, "에러나옴")
       })
       .then(() => {
         console.log("실행")
       })
    },
    test3 () {
      instance.get("/fqa")
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



</style>
