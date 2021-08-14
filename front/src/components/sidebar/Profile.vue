<template>
  <transition name="fade">
    <div class="card" v-if="!collapsed">
      <!-- 프로필 -->
      <button
        type="button"
        class="btn btn-success"
        @click="SET_MODAL_LOGIN(true)"
      >
        로그인
      </button>
      <button type="button" class="btn btn-success" @click="getInfo">
        정보
      </button>
      <button
        type="button"
        class="btn btn-success"
        @click="this.$store.dispatch('auth/logout')"
      >
        로그아웃
      </button>
    </div>
  </transition>
  <LoginModal v-if="this.$store.state.loginModal == true" />
</template>

<script>
import { collapsed } from "./state";
import { mapGetters, mapMutations } from "vuex";
import LoginModal from "@/components/modal/Login.vue";

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
    ...mapMutations(["SET_MODAL_LOGIN"]),
    getInfo() {
      alert(this.userInfo);
    },
  },
  components: {
    LoginModal,
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
