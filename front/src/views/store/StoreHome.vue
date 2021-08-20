<template>
  <div>
    <h1 class="ml-4" v-if="userInfo">{{ userInfo[2] }} 님, 환영합니다!</h1>
    <notifications
      group="notifyApp"
      position="bottom right"
      style="margin-right: 30vh"
    />
    <div id="storeWrapper" class="container">
      <div class="row align-items-center">
        <div class="col align-self-center">
          <div id="choices">
            <span>아직 가게를 등록하지 않으셨군요?</span><br />
            <button class="btn btn-primary mt-2">가게 등록</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { error, success } from "@/api/notification";

export default {
  data() {
    return {
      userInfo: null,
    };
  },
  computed: {
    ...mapGetters({
      getInfo: "auth/getUserInfo",
      getToken: "auth/getAccessToken",
    }),
  },
  mounted() {
    this.userInfo = this.getInfo;
    console.log(this.userInfo);
    if (this.userInfo[1] != "SELLER") {
      error("이 페이지에 접근할 수없습니다.", this);
      this.$store.dispatch("auth/logout");
      this.$router.push({ path: "/" });
      return;
    }
    // 등록된가게가있는지 확인해볼것
    const token = this.getToken;
    const headers = {
      "content-type": "application/json",
      accesstoken: token,
    };

    // 권한이 있는지 헤더를 실어서 보낸다.
  },
};
</script>

<style scoped>
#storeWrapper {
  width: 40rem;
  height: 30rem;
  left: 4rem;
  position: relative;
  top: 2rem;
  border: 2px solid gray;
  border-radius: 10px;
  text-align: center;
}
#choices {
  position: absolute;
  left: 30%;
  top: 40%;
}
</style>
