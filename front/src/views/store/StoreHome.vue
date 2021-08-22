<template>
  <div>
    <!-- 제목 -->
    <h1 class="ml-4" v-if="userInfo">{{ userInfo[2] }} 님, 환영합니다!</h1>
    <!-- 노티 -->
    <notifications
      group="notifyApp"
      position="bottom right"
      style="margin-right: 30vh"
    />
    <!-- 스피너 -->
    <div v-if="!dataLoaded">
      <div class="spinner-border mt-4" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>
    <div v-else>
      <!-- 가게상세정보 -->
      <div>
        <store-detail
          :storeFiles="storeFiles"
          :storeInfo="storeInfo"
          v-if="storeInfo.status != null"
        ></store-detail>
      </div>
      <!-- 가게가 없을때 -->
      <div id="storeWrapper" class="container" v-if="storeInfo.status == null">
        <div class="row align-items-center">
          <div class="col align-self-center">
            <div id="choices">
              <div>
                <span>아직 가게를 등록하지 않으셨군요?</span><br />
                <router-link to="storeRegister">
                  <button class="btn btn-primary mt-2">가게 등록</button>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import { error, success, normal } from "@/api/notification";
import axios from "axios";
import storeDetail from "@/components/store/StoreDetail.vue";

export default {
  components: {
    storeDetail,
  },
  data() {
    return {
      userInfo: null,
      storeInfo: null,
      storeFiles: null,
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
    ...mapMutations({
      setMyStore: "SET_MY_STORE",
    }),
    getStoreFiles(storeId) {
      axios
        .get("http://localhost:8083/store/getStoreFiles", {
          params: {
            storeId: storeId,
          },
        })
        .then((response) => {
          if (response.status === 200) {
            this.storeFiles = response.data;
            success("가게사진들을 불러왔습니다!", this);
            this.dataLoaded = true;
            return;
          }
        })
        .catch((err) => {
          console.log(err);
          error("오류가 발생했습니다. 다시 시도해주세요", this);
          // this.$store.dispatch("auth/logout");
          // this.$router.push({ path: "/" });
        });
    },
  },
  mounted() {
    this.userInfo = this.getInfo;
    console.log(this.userInfo);
    normal("가게정보를 검색합니다...", this);
    if (this.userInfo[1] != "SELLER") {
      error("이 페이지에 접근할 수없습니다.", this);
      this.$store.dispatch("auth/logout");
      this.$router.push({ path: "/" });
      return;
    }
    // 등록된가게가있는지 확인해볼것
    const token = this.getToken;
    const headers = {
      "content-type": "application/json",
      accesstoken: token,
    };

    // 권한이 있는지 헤더를 실어서 보낸다.
    axios
      .post("http://localhost:8083/store/findStore", null, { headers })
      .then((response) => {
        if (response.status === 200) {
          console.log(response.data);
          this.storeInfo = response.data;
          if (this.storeInfo.status != null) {
            success("등록된 가게정보를 찾았습니다!", this);
            // 전역변수에 가게 등록로직
            this.setMyStore(this.storeInfo);
            this.getStoreFiles(this.storeInfo.storeId);
            return;
          }
          error("등록된 가게가 없습니다!", this);
        }
      })
      .catch((err) => {
        console.log(err);
        error("오류가 발생했습니다. 다시 시도해주세요", this);
        this.$store.dispatch("auth/logout");
        this.$router.push({ path: "/" });
      });
  },
};
</script>

<style scoped>
#storeWrapper {
  width: 40rem;
  height: 30rem;
  position: relative;
  top: 2rem;
  border: 2px dashed palegreen;
  border-radius: 10px;
  text-align: center;
}
#choices {
  position: absolute;
  left: 30%;
  top: 40%;
}
</style>
