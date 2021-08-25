<template>
  <div>
    <div class="btn btn-primary" @click="test">버튼</div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import http from "@/api/http";
export default {
  computed: {
    ...mapGetters(["GET_LOCAL"]),
  },
  methods: {
    test() {
      http.get("/api/test").then((res) => {
        console.log(res);
      });
    },
  },
};
</script>

<style></style>
