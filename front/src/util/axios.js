import axios from "axios";
import JWT from "@/api/jwt";

// 파일전송
const requestFile = axios.create({
  baseURL: "http://localhost:8083", // 스프링 BackEnd 의 주소,
  headers: { "Content-Type": "multipart/form-data" },
});

requestFile.interceptors.request.use(function (config) {
  try {
    let access_token = JWT.getToken();
    if (access_token != null) {
      config["headers"] = {
        accesstoken: access_token,
      };
    } else {
      JWT.destroyToken();
    }
    return config;
  } catch (error) {
    console.log(error);
  }
});

export default requestFile;
