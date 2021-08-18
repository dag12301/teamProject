<template>
  <h1>마이페이지화면</h1>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "MyPage",
  components: {},
  computed: {
    ...mapGetters(["isAuthenticated"]),
  },
  methods: {
    redirect() {
      console.log(this.isAuthenticated);
      if (!this.isAuthenticated) {
        this.$router.push({
          name: "Home",
        });
      }
    },
  },
};
</script>

<style></style>
