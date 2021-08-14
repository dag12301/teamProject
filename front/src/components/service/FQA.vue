<template>
  FQA
  <button @click="test">통신</button>
  <input type="text" v-model="this.email">이메일
  <input type="password" v-model="this.pwd">패스워드

  <button @click="postTest(this.email, pwd)">통신2</button>
  <br>
  {{email}}
  <br>
  {{pwd}}
</template>

<script>
import axios from "axios"

export default {
  data () {
    return {email: null, pwd: null}
  },
  methods: {
    test () {
      axios
      .get("https://reqres.in/api/users?page=2")
      .then( res => {
        // success
        console.log(res)
      })
      .catch(error => {
        // handle error
        console.log(error)
      })
      .then(() => {
        // alaway executed
        console.log("test")
      })
    },

    //로그인
    postTest (email, pwd) {
      axios
      .get("http://localhost:8083/api/member/test", {
        id: email,
        password: pwd
      })
      .then( res => {
        console.log(res)
          console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
      .then( () => {
        console.log("test2")
      })
    }
  }
}
</script>

<style>

</style>