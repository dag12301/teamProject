import { createStore } from "vuex";

import jwt from "@/common/jwt";
import axios from "axios";

export default createStore({
  state: {
    count: 0,
    token: {
      accessToken: jwt.getToken(),
    }, // 토큰정보 가져오기.
    isAuthenticated: !!jwt.getToken(),
    // 확실한 논리연산자를 위해서. [참고] https://hermeslog.tistory.com/279
  },
  getters: {
    getAccessToken: function (state) {
      return state.token.accessToken;
    },
    isAuthenticated: function (state) {
      return state.isAuthenticated;
    },
  },
  mutations: {
    logout: function (state) {
      (state.token.accessToken = ""),
        (state.isAuthenticated = false),
        jwt.destroyToken();
    },
    login: function (state, payload) {
      (state.token.accessToken = payload.accessToken),
        (state.isAuthenticated = true),
        jwt.saveToken(payload.accessToken);
    },
  },
  actions: {
    logout: function (context) {
      return new Promise((resolve) => {
        setTimeout(function () {
          context.commit("logout");
          resolve({});
        }, 1000);
      });
    },
    register: function (context, payload) {
      let params = {
        loginId: payload.loginId,
        loginPwd: payload.loginPwd,
        name: payload.name,
        phone: payload.phone,
        email: payload.email,
        nickname: "testNickname",
        address: payload.address,
        addressDetail: payload.addressDetail,
        userType: payload.userType,
      };
      axios
        .post("api/member/join", params)
        .then((response) => {
          context.commit("login", {
            accessToken: response.headers.authorization,
          });
          Promise.resolve(response);
        })
        .catch((error) => {
          Promise.reject(error);
        });
    },
    login: function (context, payload) {
      let params = {
        loginId: payload.loginId,
        loginPwd: payload.loginPwd,
      };
      console.log(params);
      axios
        .post("/api/member/login", params)
        .then((response) => {
          console.log("나의상태는? =" + response.status);
          context.commit("login", {
            accessToken: response.headers.authorization,
          });
          Promise.resolve(response);
        })
        .catch((error) => {
          Promise.reject(error);
        });
    },
  },
  modules: {},
});
