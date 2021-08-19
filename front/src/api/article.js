import http from "@/api/http";


// notice, Q&A FAQ  리스트
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
//페이지처리된 리스트  page:페이지  range: 범위  boardId 테이블 종류
export async function getBoardList(boardId, page, range){
  return http.get("/article/pagingBoard?boardId=" + boardId + "&page=" + page + "&range=" +  range)
}
//페이징 필요값들
export async function paging() {
  return http.get("/article/pagingBoard/paging")
}




