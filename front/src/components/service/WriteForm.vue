<template>
  <div class="notice col-12 h-50">
    타이틀<input type="text" />
    <hr />
    내용 <input type="text" />
    <hr />
    <button @click="te1">1111</button>
  </div>
</template>

<script>
import * as authAPI from "@/api/auth";

export default {
  computed: {
    data() {
      return {
        articleId: null,
        notice: [],
      };
    },
  },
  methods: {
    test(articleId) {
      this.articleId = articleId;
    },
    getList(articleId) {
      authAPI
        .list(articleId)
        .then((res) => {
          this.notice = res.data[0];
        })
        .catch((err) => {
          console.log(err);
        });
    },
    te1() {
      console.log(this.notice);
      console.log(this.notice.title);
    },
  },
  mounted() {
    this.test(this.$route.query.articleId), console.log("12이다");
    this.getList(this.articleId);
    console.log("10이다");
  },
};
</script>

<style scoped>
.notice ul li {
  height: 7vh;
  padding-top: 2vh;
  padding-bottom: 2vh;
  font-size: 2vh;
}
</style>
