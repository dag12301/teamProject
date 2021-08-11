/**
 * 작성자 : 노철희
 */
import error from "@/store/index";
import { getAkiConfig, setAkiConfig, getQuestions } from "@/api/akinator";
/**
 * 더미데이터의 형식
 * 문제: 흠..
 *
 * 선택지: 3개?
 */
export default {
  namespaced: true,
  state: {
    questions: [
      {
        id: "1",
        query: "얼마나 맵게요?",
        answers: ["맵게", "안맵게", "잘 모르겠음"],
      },
      {
        id: "2",
        query: "채식주의세요?",
        answers: ["네 저는 양입니다", "고기좋아 ㅎㅎ", "아무거나잘먹어용!"],
      },
      {
        id: "3",
        query: "아무거나누르면 나가요",
        answers: ["넹", "시러욧", "졸려요"],
      },
    ],
    answers: [],
    config: {
      doAki: getAkiConfig(),
    },
  },
  getters: {
    doAki: function (state) {
      if (state.config.doAki == false) {
        return false;
      }
      return true;
    },
    getQuestion(state) {
      return state.questions;
    },
  },
  mutation: {
    nomoreAki() {
      setAkiConfig(false);
    },
    setQuestions: function (state, payload) {
      state.questions = payload;
    },
  },
  action: {
    getStats(context) {
      const howManyStats = 5;
      getQuestions(howManyStats)
        .then((response) => {
          context.setQustions = response;
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  module: {
    error,
  },
};
