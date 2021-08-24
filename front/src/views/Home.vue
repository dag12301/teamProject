<template>
  <div class="ShopList">
    <div id="layoutSidenav_content">
      <main>
        <!--메인 이름, 지도, 가게 리스트 -->
        <div class="container-fluid px-4">
          <!--메인 이름 WHAT POO -->
          <h1 class="mt-4">WHAT POO</h1>
          <div class="row">
            <!--지도 -->
            <div class="col-xl-7">
              <div class="card mb-4">
                <kakao style="z-index: 0"></kakao>
              </div>
            </div>
            <!--가게 리스트 -->
            <div class="col-xl-5">
              <div class="card mb-4">
                <div class="list-group">
                  <ShopList></ShopList>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 메뉴 리스트 시작-->
        <div class="card mb-4"></div>
        <div class="btn btn-primary" @click="showStoreList">선택</div>
      </main>
    </div>
  </div>
</template>

<script>
import Kakao from "@/components/map/Kakao.vue";
import ShopList from "@/components/shop/ShopList.vue";
import { mapGetters } from "vuex";
import http from "@/api/http";

export default {
  components: {
    Kakao,
    ShopList,
  },
  computed: {
    ...mapGetters({ LAT: "GET_LAT", LON: "GET_LON", OBS: "GET_OBSERVED" }),
  },
  methods: {
    showStoreList() {
      if (this.OBS === true) {
        this.getStoreListByLocation(this.LAT, this.LON);
      }
    },
    getStoreListByLocation(lat, lon) {
      http
        .post("/store/getStoreListByLatLoc", { lat, lon })
        .then((res) => {
          console.log(res);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    // this.showStoreList();
  },
};
</script>

<style></style>
