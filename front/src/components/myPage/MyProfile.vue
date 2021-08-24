<template>

  <div style="height:660px;">
    <div v-if="profile != null">
      <div class="row" style="padding: 20px;">
        <div class="img" style="padding-bottom: 20px;">
          <img src="https://pbs.twimg.com/profile_images/1381919597884936196/qPT_Lcw__400x400.jpg" 
              style="width: 150px; height: 150px; border-radius:50%; ">
        </div>
        <div class="filebox" > 
          <label for="ex_file">업로드</label> 
          <input type="file" id="ex_file"> 
        </div>
      </div>
        <div class="row">
          <div class="col-4 profile-1">
            이름 :
          </div>
          <div class="col-8 profile-2">
            {{ profile.name }}
          </div>
        </div>
        <div class="row">
          <div class="col-4 profile-1">
            닉네임 :
          </div>
          <div class="col-8 profile-2">
            {{ profile[2] }}
          </div>
        </div>
        <div class="row">
          <div class="col-4 profile-1">
            아이디 :
          </div>
          <div class="col-8 profile-2">
            {{ profile[2] }}
          </div>
        </div>
        <div class="row">
          <div class="col-4 profile-1">
            핸드폰번호 :
          </div>
          <div class="col-8 profile-2">
            {{ profile[3] }}
          </div>
        </div>
        <div class="row">
          <div class="col-4 profile-1" style="border-bottom: 1px solid black;">
            이메일 :
          </div>
          <div class="col-8 profile-2" style="border-bottom: 1px solid black;">
            {{ profile[4] }}
          </div>
        </div>
      </div>
      <div class="retouch">
        <button type="button" @Click="secession()" class="btn btn-secondary"><span style="font-size:20px;">탈퇴하기</span></button>
      </div>
  </div>

</template>

<script>
import axios from "axios";
import { mapGetters, mapMutations } from "vuex";
import { error, success, normal } from "@/api/notification";


export default {
  data() {
    return {
      profile: null,
      dataLoaded: false,
      
    };
  },
  computed: {
    ...mapGetters({
      getInfo: "auth/getUserInfo",
      getToken: "auth/getAccessToken",
    }),
  },
	methods: {
    secession() {
      alert('탈퇴되었습니다!')
    },
  },
  mounted() {
    console.log("프로필창에 들어옴");
    this.profile = this.getInfo;
    console.log(this.profile);
    normal("프로필을 검색합니다...", this);
    if (this.profile == null) {
      error("이 페이지에 접근할 수없습니다.", this);
      this.$router.push({ path: "/" });
      return;
    }

    const token = this.getToken;
    const headers = {
      "content-type": "application/json",
      accesstoken: token,
    };

    axios
      .post("http://localhost:8083/profile/myProfile", null, { headers })
      .then((response) => {
        if (response.status === 200) {
          console.log("연동됨");
          console.log(response.data);
          this.profile = response.data;
          if (this.profile != null) {
            success("프로필을 불러왔습니다.", this);
            return;
          }
          error("응 안돼 돌아가", this);
          this.dataLoaded = true;
        }
      })
      .catch((err) => {
        console.log(err);
        error("오류가 발생했습니다. 다시 시도해주세요", this);
        
      });
  },
}
</script>

<style>
  .profile-1{
    font-weight: 400;
    padding: 20px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    border-style: solid; 
    border-width: 1px 1px 0px 0px;
  }
  .profile-2{
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
    padding: .5em .75em; 
    color: white; 
    font-size: inherit; 
    line-height: normal; 
    vertical-align: middle; 
    background-color: #2f855a; 
    cursor: pointer; 
    border: 1px solid #ebebeb; 
    border-bottom-color: #e2e2e2; 
    border-radius: .25em; 
  } 
  .filebox input[type="file"] { /* 파일 필드 숨기기 */ 
    position: absolute; 
    width: 1px; 
    height: 1px; 
    padding: 0; 
    margin: -1px; 
    overflow: hidden; 
    clip:rect(0,0,0,0); 
    border: 0; 
  }

</style>
