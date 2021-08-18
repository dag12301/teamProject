import * as authApi from "@/api/auth";

import jwt from "@/api/jwt";

export default {
  namespaced: true,
  state: {
    token: {
      accessToken: jwt.getToken(),
    },
    isAuthenticated: !!jwt.getToken(),
    userRole: "null",
    userNickname: "null",
    userEmail: "null",
    userName: "null",
    userPhone: "null",
  },
  getters: {
    getAccessToken: function (state) {
      return state.token.accessToken;
    },
    getUserInfo: function (state) {
      const info = [
        state.isAuthenticated,
        state.userRole,
        state.userNickname,
        state.userPhone,
        state.userEmail,
      ];
      return info;
    },
  },
  mutations: {
    setToken(state, payload) {
      // 토큰을 state에 저장하는 함수.
      (state.token.accessToken = payload),
        (state.isAuthenticated = true),
        jwt.saveToken(payload);
    },
    delToken(state) {
      (state.token.accessToken = ""),
        (state.isAuthenticated = false),
        jwt.destroyToken();
    },
    setUserInfo(state, userdata) {
      state.isAuthenticated = true;
      state.userRole = userdata.role;
      state.userNickname = userdata.nickname;
      state.userPhone = userdata.phone;
      state.userEmail = userdata.email;
    },
    delUserInfo(state) {
      state.isAuthenticated = false;
      state.userRole = null;
      state.userNickname = null;
      state.userPhone = null;
      state.userEmail = null;
    },
  },
  actions: {
    login(context, { userId, password }) {
      return authApi.login(userId, password).then(
        (response) => {
          //응답이 성공적이었을 시,
          if (response.status === 200) {
            // 응답이 200인지 확인
            // 토큰을 저장한다.
            context.commit("setToken", response.headers.authorization);
            // 토큰을 이용해서 유저정보 불러오기
          }
          return Promise.resolve(response);
        },
        (error) => {
          console.log("에러가 발생했습니다. 아이디와 암호를 확인하세요..");

          return Promise.reject(error);
        }
      );
    },
    getInfo(context) {
      const token = jwt.getToken();
      console.log("로그인 유지를 위한 정보 요청을 보냅니다");
      return authApi.getInfo(token).then((response) => {
        console.log(response.data);
        context.commit("setUserInfo", response.data);
      });
    },
    logout(context) {
      // 로그아웃시 토큰삭제
      return new Promise((resolve) => {
        setTimeout(function () {
          context.commit("delToken");
          context.commit("delUserInfo");
          console.log("로그아웃 했음.");
          resolve();
        }, 1000); // 1초 후 로그아웃됨
      });
    },
  },
};
