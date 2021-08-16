import { createStore } from "vuex";

import auth from "@/store/auth";
import Akinator from "@/store/akinator";
import fooddiv from "./foodDiv/foodDiv.js";
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
    SET_MODAL_LOGIN(state, boolean) { // 로그인 모달 실행
      state.loginModal = boolean;
    },
    SET_MODAL_REGISTER(state, boolean) { // boolean fals =>로그인, register 모달 닫기
      state.loginModal = false;
      state.registerModal = boolean; //boolean true => 회원가입 열기
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
    menu,
    foodDiv
  },
  plugins: [
    createPersistedState({
      paths: ["auth"],
    }),
  ],
});
