/**
 * 작성자 : 노철희
 */
import * as authApi from "@/api/auth";

import http from "@/api/http";
import jwt from "@/api/jwt";

export default {
  namespaced: true,
  state: {
    token: {
      accessToken: jwt.getToken(),
      // 토큰의 정보 가져오기
    },
    isAuthenticated: !!jwt.getToken(),
    // `!!` 의 사용확실한 논리연산자를 위해서. [참고] https://hermeslog.tistory.com/279
  },
  getters: {
    getAccessToken: function (state) {
      return state.token.accessToken;
    },
    isAuthenticated: function (state) {
      return state.isAuthenticated;
    },
  },
  mutation: {
    setToken(state, payload) {
      // 토큰을 state에 저장하는 함수.
      (state.token.accessToken = payload.accessToken),
        (state.isAuthenticated = true),
        jwt.saveToken(payload.accessToken);
    },
    delToken(state) {
      (state.token.accessToken = ""),
        (state.isAuthenticated = false),
        jwt.destroyToken();
    },
  },
  action: {
    async login(context, { userId, password }) {
      try {
        // authAPI 를 이용해서 로그인시도
        const response = await authApi.login(userId, password);
        // 200 응답일때 토큰설정
        if (response.status === 200) {
          context.commit("setToken", response.data.token);
        }
        Promise.resolve(response);
      } catch (e) {
        alert("아이디와 암호를 확인하세요");
      }
    },
    logout(context) {
      // 로그아웃시 토큰삭제
      return new Promise((resolve) => {
        setTimeout(function () {
          context.commit("delToken");
          resolve();
        }, 1000); // 1초 후 로그아웃됨
      });
    },
    async register(context, payload) {
      const userdata = {
        loginId: payload.loginId,
        loginPwd: payload.loginPwd,
        name: payload.name,
        phone: payload.phone,
        email: payload.email,
        nickname: payload.nickname,
        role: "buyer",
      };

      // 회원가입하는 로직 추가해야함. + auth.api에
      http.post("/api/member/join", userdata);
    },
  },
  module: {},
};
