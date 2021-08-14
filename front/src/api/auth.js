import http from "@/api/http";

export async function login(loginId, password) {
  return http.post("/api/member/login", {
    loginId,
    password,
  });
}
