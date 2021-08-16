<template>
  <div>
    <button @click="test">wqe</button>
                
</div>
</template>

<script>

import instance from "@/api/http"


export default {
  methods: {
    test () {
       instance.get("/board")
       .then(res => {
         console.lig(res)
       })
       .catch(err => {
         console.log(err)
       })
    }
  }
}

</script>
  
<style scoped>



</style>
