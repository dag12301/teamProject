<template>
  <h1>로그아웃화면</h1>
  <button @click="logout()">로그아웃</button>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "Logout",
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
    logout() {
      this.$store
        .dispatch("logout", {})
        .then(() => this.redirect())
        .catch(({ message }) => alert(message));
    },
  },
};
</script>

<style></style>
