import { createStore } from "vuex";

import auth from "@/store/auth";
import Akinator from "@/store/akinator";
import fooddiv from "./foodDiv/foodDiv.js";
import foodlist from "./foodDiv/foodList.js";
import shopList from "./shopList/shopList.js";
import menu from "./menu/menu.js";
import createPersistedState from "vuex-persistedstate";
import serviceCenter from "./serviceCenter";

import couponon from "./event/couponon.js";
import couponend from "./event/couponend.js";

export default createStore({
  state: {
    loading: false, // 로딩 컴포넌트를 위함

    loginModal: false, // 로그인 모달
    registerModal: false, // 레지스터 모달
    mapModal: false, // 맵 모달
    selectRegister: "BUYER",

    akinator: false, // 아키네이터 상태

    countNotice: true, //notice 상태
    countQueAn: true, //Q&A 상태
    countFAQ: true, //FQA 상태

    selectedPlace: "주소찾기를 눌러주세요...", // 주소찾기에서 선택한 주소
    selectedAddressDetail: "", // 주소찾기에서 선택한 상세주소
  },
  mutations: {
    SET_MODAL_LOGIN(state, boolean) {
      // 로그인 모달 실행
      state.loginModal = boolean;
    },
    SET_MODAL_REGISTER(state, boolean) {
      // boolean fals =>로그인, register 모달 닫기
      state.loginModal = false;
      state.registerModal = boolean; //boolean true => 회원가입 열기
    },
    SET_AKINATOR(state, boolean) {
      state.akinator = boolean;
    },
    SET_SELECT_REGISTER(state, data) {
      state.selectRegister = data;
    },
    SET_COUNT_NOTICE(state, boolean) {
      //notice 상태 확인 변화
      state.countNotice = boolean;
      console.log(state.countNotice);
    },
    SET_COUNT_QUEAN(state, boolean) {
      //notice 상태 확인 변화
      state.countQueAn = boolean;
      console.log(state.countQueAn);
    },
    SET_COUNT_FQA(state, boolean) {
      //notice 상태 확인 변화
      state.countFAQ = boolean;
      console.log(state.countFAQ);
    },
    SET_MODAL_MAP(state, boolean) {
      state.mapModal = boolean;
    },
    SET_SELECTED_PLACE(state, [place, detail]) {
      state.selectedPlace = place;
      state.selectedAddressDetail = detail;
    },
  },
  modules: {
    auth,
    Akinator,
    shopList,
    serviceCenter,
    menu,
    fooddiv,
    foodlist,
    couponon,
    couponend,
  },
  plugins: [
    createPersistedState({
      paths: ["auth"],
    }),
  ],
});
