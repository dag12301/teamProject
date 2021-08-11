/** 로그인 구현
 * 작성자 : 노철희
 * 사용법 :
 *  import * as authAPI
 */
import http from "@/api/http";

export async function login(userId, password) {
  return http.post("/api/member/login", {
    // MemberController 의 url주소 참고.
    userId,
    password,
  });
}
