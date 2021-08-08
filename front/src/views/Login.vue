<template>
  <div>
    <form @submit.prevent="formSubmit" method="POST">
      <label for="loginId">loginId</label>
      <input type="text" id="loginId" v-model="loginId" />
      <label for="loginPwd">loginPwd</label>
      <input type="text" id="loginPwd" v-model="loginPwd" />
      <button type="submit">login</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      loginId: "",
      loginPwd: "",
    };
  },
  methods: {
    formSubmit() {
      console.log(this.loginId);
      this.$store
        .dispatch("login", {
          loginId: this.loginId,
          loginPwd: this.loginPwd,
        })
        .then((response) => {
          if (response.status == 200) {
            this.$route.push({
              name: "Home",
            });
          }
        })
        .catch(({ message }) => alert(message));

      return true;
    },
  },
};
</script>
