import { createStore } from "vuex";

import jwt from "@/common/jwt";
import http from "@/common/http";

export default createStore({
  state: {
    count: 0,
    token: {
      accessToken: jwt.getToken(),
    },
    isAuthenticated: !!jwt.getToken(),
  },
  getters: {
    getAccessToken: function (state) {
      return state.token.accessToken;
    },
    isAuthenticated: function (state) {
      return state.token.isAuthenticated;
    },
  },
  mutations: {
    logout: function (state) {
      (state.token.accessToken = ""),
        (state.isAuthenticated = false),
        jwt.destroyToken();
    },
    login: function (state, payload = {}) {
      (state.token.accessToken = payload.accessToken),
        (state.isAuthenticated = true),
        jwt.saveToken(payload.accessToken);
    },
  },
  actions: {
    logout: function (context, payload) {
      return new Promise((resolve) => {
        setTimeout(function () {
          context.commit("logout", payload);
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
        address: payload.address,
        addressDetail: payload.addressDetail,
        userType: payload.userType,
      };
      return new Promise((resolve, reject) => {
        http
          .post("api/join", params)
          .then((response) => {
            const { data } = response;
            context.commit("login", {
              accessToken: data.accessToken,
            });
            resolve(response);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    login: function (context, payload) {
      let params = {
        loginId: payload.loginId,
        loginPwd: payload.loginPwd,
      };
      return new Promise((resolve, reject) => {
        http
          .post("/api/login", params)
          .then((response) => {
            const { data } = response;
            context.commit("login", {
              accessToken: data.accessToken,
            });
            resolve(response);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
  },
  modules: {},
});
