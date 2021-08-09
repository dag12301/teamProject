<template>
  <div>
    <h1>회원가입화면</h1>
    <form @submit.prevent="formSubmit" method="Post">
      <label for="loginId">loginId</label>
      <input type="text" id="loginId" v-model="loginId" />
      <label for="loginPwd">loginPwd</label>
      <input type="text" id="loginPwd" v-model="loginPwd" />
      <label for="name">name</label>
      <input type="text" id="name" v-model="name" />
      <label for="phone">phone</label>
      <input type="text" id="phone" v-model="phone" />
      <label for="email">email</label>
      <input type="text" id="email" v-model="email" />

      <!-- // address adressDetail userType -->
      <button type="submit">회원가입</button>
    </form>
  </div>
</template>
<script>
export default {
  name: "Register",
  components: {},
  data() {
    return {
      loginId: "",
      loginPwd: "",
      name: "",
      phone: "",
      email: "test@test.test",
      address: "testAddress",
      addressDetail: "testAddressDetail",
      userType: "BUYER",
    };
  },
  methods: {
    async formSubmit() {
      this.$store
        .dispatch("register", {
          loginId: this.loginId,
          loginPwd: this.loginPwd,
          name: this.name,
          phone: this.phone,
          email: this.email,
          address: this.address,
          addressDetail: this.addressDetail,
          userType: this.userType,
        })
        .then((response) => {
          if (response.status == 200) {
            this.$router.push({
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
