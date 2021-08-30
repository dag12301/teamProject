<template>
  <transition name="fade">
    <div class="card" id="profile" v-if="!collapsed">
      <notifications
        group="notifyApp"
        position="bottom right"
        style="margin-right: 30vh"
      />
      <!-- 프로필 -->
      <div v-if="userInfo[0]">
        <div style="text-align: center">
          <div class="user" style="padding-bottom: 10%">
            <img
              src="https://image.news1.kr/system/hp/2019/11/29/3943620/dims/optimize"
              class="profile"
            />
            <span style="color: black"
              ><span style="font-weight: bold">{{userInfo[2]}}</span> 님
              <br />
              안녕하세요.</span
            >
          </div>
        </div>
        <div class="login">
          <router-link to="/myPage">
            <button type="button" class="btn btn-success">프로필</button>
          </router-link>
          <button
            type="button"
            class="btn btn-success"
            @click="this.$store.dispatch('auth/logout')"
          >
            로그아웃
          </button>
        </div>
      </div>
      <div v-else>
        <button
          type="button"
          class="btn btn-success"
          @click="SET_MODAL_LOGIN(true)"
        >
          로그인
        </button>
      </div>
    </div>
  </transition>
  <LoginModal v-if="this.$store.state.loginModal == true" />
  <RegisterModal v-if="this.$store.state.registerModal == true" />
</template>

<script>
import { collapsed } from "./state";
import { mapGetters, mapMutations } from "vuex";
import LoginModal from "@/components/modal/Login.vue";
import RegisterModal from "@/components/modal/Register.vue";
import { success } from "@/api/notification";


export default {
  props: {},
  setup() {
    return { collapsed };
  },
  data() {
    return {
      profile: ''
    }
  },
  computed: {
    ...mapGetters({
      userInfo: "auth/getUserInfo",
    }),
  },
  methods: {
    ...mapMutations(["SET_MODAL_LOGIN", "SET_MODAL_REGISTER"]),
    logout() {
      this.$store.dispatch("auth/logout");
      setTimeout(() => {
        this.$router.push({ path: "/" });
      }, 500);
      success("성공적으로 로그아웃 하였습니다!", this);
    },
   
    
  },
  components: {
    LoginModal,
    RegisterModal,
  },
};
</script>

<style scoped>
#profile {
  display: flex;

  position: relative;
  font-weight: 400;
  user-select: none;

  margin: 1em 0;
  padding: 0.4em;
  border-radius: 0.25em;
  width: 200px;

  background-color: white;
  text-decoration: none;
}
.login {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  height: 100px;
}
.noLogin {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  height: 150px;
}
.fade-enter-active,
.fade-leave-active {
  transition: 0.3s ease;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.profile {
  width: 70px;
  height: 70px;
  border-radius: 50%;
}
.user {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
</style>
