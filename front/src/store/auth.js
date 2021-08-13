/**
 * 작성자 : 노철희
 */
import * as authApi from "@/api/auth";

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
          }
          return Promise.resolve(response);
        },
        (error) => {
          console.log("에러가 발생했습니다. 아이디와 암호를 확인하세요.");

          return Promise.reject(error);
        }
      );
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
    register() {},
  },
  modules: {},
};
