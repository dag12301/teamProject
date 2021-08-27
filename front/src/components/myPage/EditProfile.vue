<template>
<center>
  <div class="container" style="width: 800px; margin-right: 10px">
    <div>
      <div class="row" style="border-top: 1px solid black">
        <div class="col-4 edit-1">이름 :</div>
        <div class="col-8 edit-2">이름</div>
      </div>
      <div class="row">
        <div class="col-4 edit-1">닉네임 :</div>
        <div class="col-8 edit-2">
          <input
            v-model="edit.nickname"
            type="text"
            placeholder="한글,영문,숫자만 2~10자리"
            maxlength="10"
          />
          <div v-if="!nicknameValid" class="msg">
            닉네임을 잘못입력하셨습니다.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-4 edit-1">아이디 :</div>
        <div class="col-8 edit-2">아이디</div>
      </div>
      <div class="row">
        <div class="col-4 edit-1">비밀번호 :</div>
        <div class="col-8 edit-2">
          <input
            v-model="edit.pwd"
            type="password"
            placeholder="숫자,대문자,소문자 8~16자리"
            maxlength="16"
          />
          <div v-if="!pwdValid" class="msg">비밀번호를 잘못입력하셨습니다.</div>
        </div>
      </div>
      <div class="row">
        <div class="col-4 edit-1">비밀번호 확인 :</div>
        <div class="col-8 edit-2">
          <input
            v-model="edit.pwdCheck"
            type="password"
            placeholder="위 비밀번호랑 똑같이 적으셈"
            maxlength="16"
          />
          <div v-if="!pwdCheckValid" class="msg">틀리게적음</div>
        </div>
      </div>
      <div class="row">
        <div class="col-4 edit-1">핸드폰번호 :</div>
        <div class="col-8 edit-2">
          <input
            v-model="edit.number"
            type="text"
            placeholder="-없이 적어주세요"
            maxlength="11"
          />
          <div v-if="!numberValid" class="msg">번호 이상함</div>
        </div>
      </div>
      <div class="row">
        <div class="col-4 edit-1" style="border-bottom: 1px solid black">
          이메일 :
        </div>
        <div class="col-8 edit-2" style="border-bottom: 1px solid black">
          <input
            v-model="edit.email"
            type="email"
            placeholder="이메일 적어주셈"
            maxlength="20"
          />
          <div v-if="!emailValid" class="msg">이메일 이상함</div>
        </div>
      </div>
    </div>
  </div>
</center>
</template>

<script>
export default {
  methods: {
    editProfile() {
      alert("수정되었습니다!");
    },
  },
  data() {
    return {
      edit: {
        name: null,
        nickname: null,
        id: null,
        pwd: null,
        pwdCheck: null,
        number: null,
        email: null,
      },
    };
  },
  computed: {
    nicknameValid() {
      return /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/.test(this.edit.nickname);
    },
    pwdValid() {
      return /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(this.edit.pwd);
    },
    pwdCheckValid() {
      return /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(
        this.edit.pwdCheck
      );
    },
    numberValid() {
      return /^\d{11}$/.test(this.edit.number);
    },
    emailValid() {
      return /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(
        this.edit.email
      );
    },
  },
};
</script>

<style>
.edit-1 {
  font-weight: 400;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border-style: solid;
  border-width: 0px 1px 1px 1px;
}
.edit-2 {
  font-weight: 400;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-style: solid;
  border-width: 0px 1px 1px 0px;
}
.msg {
  color: red;
  font-size: 15px;
}
</style>
