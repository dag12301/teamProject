import http from "@/api/http";

// 질문 얻어오기.
export async function getQuestions(quantity) {
  return await http.get("/api/arkinator/getQuestions", quantity);
}

// 아키네이터 설정정보 키설정
const AKI_CONFIG = "doAki";

export const getAkiConfig = () => {
  console.log("아키네이터 할건지, 얻어오기");
  return window.sessionStorage.getItem(AKI_CONFIG);
};

export const setAkiConfig = (boolean) => {
  console.log("아키네이터 할건지, 쿠키값 : " + boolean);
  window.sessionStorage.setItem(AKI_CONFIG, boolean);
};

export default {
  getAkiConfig,
  setAkiConfig,
  getQuestions,
};
