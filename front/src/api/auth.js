import http from "@/api/http";

export async function login(loginId, password) {
  return http.post("/api/member/login", {
    loginId,
    password,
  });
}

export async function getInfo(token) {
  return http.post("/api/member/getInfo", {
    token,
  });
}
//화원가입
export async function register(params) {
  return http.post("/api/member/register", {
    params,
  });
}
