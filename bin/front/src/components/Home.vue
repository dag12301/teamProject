<template>
  <div class="hello">
    <button @click="test">서버통신테스트</button>
    {{ text }}
    <h1>JWT 홈 화면</h1>
    {{ isAuthenticated ? "로그인됨" : "로그인안됨" }} from Home.comp
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "HelloWorld",
  data() {
    return {
      text: "",
    };
  },
  methods: {
    test() {
      axios
        .get("http://localhost:8083/test")
        .then((res) => {
          this.text = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapGetters(["isAuthenticated"]),
  },
};
</script>

<style scoped></style>
