import { createStore } from "vuex";

import auth from "@/store/auth";
import Akinator from "@/store/akinator";
import fooddiv from "./foodDiv/foodDiv.js";
import foodlist from "./foodDiv/foodList.js"
import shopList from "./shopList/shopList.js";
import menu from "./menu/menu.js";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    loading: false, // 로딩 컴포넌트를 위함

    loginModal: false, // 로그인 모달
    registerModal: false, // 레지스터 모달
    selectRegister: "BUYER",

    akinator: false, // 아키네이터 상태
  },
  mutations: {
    SET_MODAL_LOGIN(state, boolean) {
      state.loginModal = boolean;
    },
    SET_MODAL_REGISTER(state, boolean) {
      state.loginModal = false;
      state.registerModal = boolean;
    },
    SET_AKINATOR(state, boolean) {
      state.akinator = boolean;
    },
    SET_SELECT_REGISTER(state, data) {
      state.selectRegister = data;
    },
  },
  modules: {
    auth,
    Akinator,
    shopList,
    fooddiv,
    foodlist,
    menu,
  },
  plugins: [
    createPersistedState({
      paths: ["auth"],
    }),
  ],
});
