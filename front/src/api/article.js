import http from "@/api/http";

// notice, Q&A FAQ  리스트
export async function allList(query) {
  return http.get("/article/board?boardId=" + query);
}
//테스트용
export async function test() {
  return http.get("/article/test");
}
//게시글 죄회
export function list(query) {
  return http.get("/article/board/list?articleId=" + query);
}
//게시글 등록
export async function writeProc(params) {
  return http.post("/article/board/writeProc", {
    params,
  });
}
//댓글 등록
export async function commentProc(params) {
  return http.post("/article/board/commentProc", {
    params,
  });
}
//게시글 삭제
export async function listDelete(params) {
  return http.post("/article/board/delete", {
    params,
  });
}
//페이징 리스트
export async function getBoardList(boardId, page, range, listSize, rangeSize) {
  return http.get("/article/pagingBoard", {
    params: {
      boardId: boardId,
      page: page,
      range: range,
      listSize: listSize,
      rangeSize: rangeSize,
    },
  });
}
//수정 페이지
export async function update(params) {
  return http.post("/article/board/update", {
    params,
  });
}

//이미지 업로드
export function imageupload(form) {
  return http.post("/article/imageUpload", form, {
    headers: { "Content-Type": "multipart/form-data" },
  });
}
//본인 확인
export async function articleVerify(params) {
  return http.post("/article/articleVerify", {
    params,
  });
}
