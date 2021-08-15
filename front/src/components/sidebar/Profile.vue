<template>
  <transition name="fade">
    <div class="card" v-if="!collapsed">
      <!-- 프로필 -->
      {{ userInfo[1] }} 님 안녕하세요. ㅇㅇ ㅇㅇ
      <div v-if="userInfo[0]">
        <button
          type="button"
          class="btn btn-success"
          @click="this.$store.dispatch('auth/logout')"
        >
          로그아웃
        </button>
      </div>
      <div v-else>
        <button
          type="button"
          class="btn btn-success"
          @click="SET_MODAL_LOGIN(true)"
        >
          로그인
        </button>
        <button class="btn btn-primary" @click="SET_MODAL_REGISTER(true)">
          회원가입
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

export default {
  props: {},
  setup() {
    return { collapsed };
  },
  computed: {
    ...mapGetters({
      userInfo: "auth/getUserInfo",
    }),
  },
  methods: {
    ...mapMutations(["SET_MODAL_LOGIN", "SET_MODAL_REGISTER"]),
  },
  components: {
    LoginModal,
    RegisterModal,
  },
};
</script>

<style scoped>
.card {
  display: flex;
  align-items: center;

  position: relative;
  font-weight: 400;
  user-select: none;

  margin: 1em 0;
  padding: 0.4em;
  border-radius: 0.25em;
  height: 10rem;
  width: 200px;

  background-color: white;
  text-decoration: none;
}

.fade-enter-active,
.fade-leave-active {
  transition: 0.3s ease;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
