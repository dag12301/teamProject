import { createStore } from "vuex";

import auth from "@/store/auth";
import Akinator from "@/store/akinator";

import shopList from "./shopList/shopList.js";
import menu from "./menu/menu.js";

export default createStore({
  state: {
    loading: false, //로딩 컴포넌트를 위함
    isLogined: false,

    loginModal: false, // 로그인 모달
    registerModal: false, // 레지스터 모달
    sidebar: false,
    akinator: false,

    selectRegister: "", // 가입하는 유저 종류
  },
  mutations: {
    SET_MODAL_LOGIN(state, boolean) {
      state.loginModal = boolean;
    },
    SET_MODAL_REGISTER(state, boolean) {
      state.loginModal = false;
      state.registerModal = boolean;
    },
    SET_SELECT_REGISTER(state, boolean) {
      state.selectRegister = boolean;
    },
    SET_SIDEBAR(state, boolean) {
      state.sidebar = boolean;
    },
    SET_AKINATOR(state, boolean) {
      state.akinator = boolean;
    },
  },
  modules: {
    auth,
    Akinator,

    shopList,
    menu,
  },
});
