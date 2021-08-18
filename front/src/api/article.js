import http from "@/api/http";


// notice, Q&A FAQ
export async function allList(query) {
  return http.get("/article/board?boardId=" + query)
}
//테스트용
export async function test() {
  return http.get("/article/test")
}
//게시글 죄회
export   function list(query) {
  return http.get("/article/board/list?articleId=" + query)
}
//게시글 등록
export async function writeProc(params){
  return http.post("/article/board/writeProc", {
    params
  })
}
//게시글 삭제
export async function listDelete(params){
  return http.post("/article/board/delete", {
    params
  })
}


