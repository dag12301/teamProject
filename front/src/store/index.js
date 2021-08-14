import { createStore } from "vuex";

import auth from "@/store/auth";
import akinator from "@/store/akinator";

import shopList from "./shopList/shopList.js";
import menu from "./menu/menu.js";
import foodDiv from "../store/foodDiv";

export default createStore({
  state: {
    loading: false, //로딩 컴포넌트를 위함

    loginModal: false, // 로그인 모달
    registerModal: false, // 레지스터 모달
    sidebar: false,     //사이드바
    akinator: false,

    selectRegister: "", // 가입하는 유저 종류
  },
  mutations: {
    SET_MODAL_LOGIN(state, boolean) { // 로그인 모달 실행
      state.loginModal = boolean;
    },
    SET_MODAL_REGISTER(state, boolean) { // boolean fals =>로그인, register 모달 닫기
      state.loginModal = false;
      state.registerModal = boolean; //boolean true => 회원가입 열기
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
    akinator,
    shopList,
    menu,
    foodDiv
  },
});
