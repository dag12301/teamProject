import { createStore } from "vuex";

import auth from "@/store/auth";
import error from "@/store/error";
import toggle from "@/store/toggle";
import akinator from "@/store/akinator";

import shopList from "./shopList/shopList.js";
import menu from "./menu/menu.js";

export default createStore({
  state: {
    loginModal: false,
    selectRegister: "",
    registerModal: false,
    loading: false, //로딩 컴포넌트를 위함
  },
  mutations: {
    SET_MODAL_LOGIN(state, boolean) {
      state.loginModal = boolean;
    },
    SET_MODAL_REGISTER(state, boolean) {
      //로그인 창 닫기
      state.loginModal = false;
      //회원가입창 열어주기
      state.registerModal = boolean;
    },
    SET_SELECT_REGISTER(state, boolean) {
      state.selectRegister = boolean;
    },
  },
  modules: {
    auth,
    error,
    toggle,
    akinator,

    shopList,
    menu,
  },
});
