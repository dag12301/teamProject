<template>
  <div>
    <div class="btn btn-primary" @click="getInfo">정보요청</div>
    <div class="btn btn-primary" @click="whattest">정보나와랏얍</div>
    <div class="btn btn-primary" @click="getBuyer">판매자</div>
  </div>

  <div class="btn btn-primary" @click="test">test</div>
</template>

<script>
import http from "@/api/article.js";
import { mapActions, mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters({
      gettest: "auth/getUserInfo",
    }),
  },
  methods: {
    ...mapActions({
      getInfo: "auth/getInfo",
    }),
    whattest() {
      console.log(this.gettest);
    },
    getUserInfo() {
      http
        .post("/test")
        .then((res) => console.log(res))
        .catch((e) => console.log(e));
    },
    getAdminTest() {
      http
        .post("/admin/test")
        .then((res) => console.log(res))
        .catch((e) => console.log(e));
    },
    getBuyer() {
      http
        .post("/buyer/test")
        .then((res) => console.log(res))
        .catch((e) => console.log(e));
    },
  },
};
</script>

<style></style>
