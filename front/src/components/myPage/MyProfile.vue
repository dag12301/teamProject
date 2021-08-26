<template>
  <div style="height: 660px">
    <div v-if="profile != null">
      <div class="row" style="padding: 20px">
        <div class="img" style="padding-bottom: 20px">
          <img
            src="https://pbs.twimg.com/profile_images/1381919597884936196/qPT_Lcw__400x400.jpg"
            style="width: 150px; height: 150px; border-radius: 50%"
          />
        </div>
        <div class="filebox">
          <label for="ex_file">업로드</label>
          <input type="file" id="ex_file" />
        </div>
      </div>
      <div class="row">
        <div class="col-4 profile-1">이름 :</div>
        <div class="col-8 profile-2">
          {{ profile.name }}
          <!-- 배열위치는 언제든 바뀔수있음. 그보다 정확한 변수명을 명시해줄것 -->
        </div>
      </div>
      <div class="row">
        <div class="col-4 profile-1">닉네임 :</div>
        <div class="col-8 profile-2">
          {{ profile.nickname }}
        </div>
      </div>
      <div class="row">
        <div class="col-4 profile-1">아이디 :</div>
        <div class="col-8 profile-2">
          {{ profile.loginId }}
        </div>
      </div>
      <div class="row">
        <div class="col-4 profile-1">핸드폰번호 :</div>
        <div class="col-8 profile-2">
          {{ profile.tel }}
          <!-- tel 로 받아놓게했는데 DB에선 phone 으로 했음. 헷갈릴수있음 -->
        </div>
      </div>
      <div class="row">
        <div class="col-4 profile-1" style="border-bottom: 1px solid black">
          이메일 :
        </div>
        <div class="col-8 profile-2" style="border-bottom: 1px solid black">
          {{ profile.email }}
        </div>
      </div>
    </div>
    <div class="retouch">
      <button type="button" @Click="secession()" class="btn btn-secondary">
        <span style="font-size: 20px">탈퇴하기</span>
      </button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { error, success, normal } from "@/api/notification";

export default {
  data() {
    return {
      profile: null,
    };
  },
  methods: {
    secession() {
      alert("탈퇴되었습니다!");
      // 탈퇴로직 작성
    },
  },
  mounted() {
    http
      .get("/profile/myProfile")
      .then((response) => {
        if (response.status === 200) {
          this.profile = response.data;
          if (this.profile != null) {
            success("프로필을 불러왔습니다.", this);
            return;
          }
          // 로직상 이쪽에 올리가 없음. 만약 오게된다면 통신장애.
          error("통신중 오류가 발생했습니다.", this);
        }
        error("정보가 존재하지 않습니다.", this);
        // 이러면 잘못된 정보로 요청을 한것이고, 로그아웃을 시켜야함.
        // 로그아웃이되면, 토큰정보와 로그인정보가 사라짐.
        // jwt의 단점인 토큰말소의 어려움을 해소할수 있는 방법중 하나.
        this.$store.dispatch("auth/logout");
      })
      .catch((err) => {
        // 에러 내용을 보려면 err.response
        // err 와 error 는 다른것이기에 이렇게 작성한것은 잘했음.
        console.log(err.response);
        error("오류가 발생했습니다. 다시 시도해주세요", this);
        // 만약 500 오류가 나면 어떻게 처리할것인가?
      });
  },
};
</script>

<style>
.profile-1 {
  font-weight: 400;
  padding: 20px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border-style: solid;
  border-width: 1px 1px 0px 0px;
}
.profile-2 {
  font-weight: 400;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-style: solid;
  border-width: 1px 0px 0px;
}
.retouch {
  padding: 20px;
}
.filebox label {
  display: inline-block;
  padding: 0.5em 0.75em;
  color: white;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #2f855a;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: 0.25em;
}
.filebox input[type="file"] {
  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}
</style>
