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
// notice, Q&A FAQ
export async function allList(query) {
  return http.get("/article/board?boardId=" + query)
}
//테스트용
export async function test() {
  return http.get("/article/test")
}
//게시글 죄회
export  async function list(query) {
  return http.get("/article/board/list?articleId=" + query)
}
//게시글 등록
export async function writeProc(params){
  return http.post("/article/board/writeProc", {
    params
  })
}

