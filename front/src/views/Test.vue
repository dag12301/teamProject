<template>
  <div>
    <div class="btn btn-primary" @click="test">버튼</div>
  </div>
</template>

<script>
import http from "@/api/http";
export default {
  methods: {
    test() {
      http.get("/api/test").then((res) => console.log(res));
    },
  },
};
</script>

<style></style>
