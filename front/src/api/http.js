/** axios 공통 모듈화
 * 작성자 : 노철희
 * 사용법 :
 *  import http from '@/api/http;
 *  ...
 *  http.post('컨트롤러 매핑주소', { 페이로드 }).then(...).catch(...);
 *  > {페이로드} 는 넘길 객체를 의미합니다.
 */
import axios from "axios";
import store from "@/store";

const instance = axios.create({
  baseURL: "http://localhost:8083", // 스프링 BackEnd 의 주소,
});

instance.interceptors.request.use(function (config) {
  if (store.state.auth.token != null) {
    config["headers"] = {
      Authrization: `Bearer ${store.state.auth.token}`,
    };
  }
  return config;
});

// 받는 모든 요청에, 이 인터셉터가 관여한다.
instance.interceptors.response.use(
  function (response) {
    // 요청이 성공했을 때의 콜백정의.
    store.commit("error/setValidationError", {}); // 아무값도 집어넣지 않는다.
    return response;
  },
  function (errors) {
    if (errors.response.status === 422) {
      store.commit("error/setValidationError", errors.response.data.data); // error 응답의 data.data 페이로드로 갖는 setValidationError 호출.
    } else {
      return Promise.reject(errors); // 422 가 아니면 거부.
    }
  }
);
export default instance;
