<template>
  <div>
    <div class="btn btn-primary" @click="getUserInfo">정보요청</div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  methods: {
    getUserInfo() {
      http
        .post("/api/member/info")
        .then((res) => console.log(res))
        .catch((e) => console.log(e));
    },
  },
};
</script>

<style></style>
