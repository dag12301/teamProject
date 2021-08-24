<template>
  <div class="shopList">
    <div class="shopBox">
      <a
        href="#"
        class="
          list-group-item list-group-item-action
          flex-column
          align-items-start
        "
      >
        <div class="row">
          <div class="col storeTitle">
            <h5 class="mb-1">{{ shopInfo.name }}</h5>
          </div>
          <!-- 음식사진시작 -->
          <div class="col-9">
            <div class="slide">
              <splide :options="options">
                <splide-slide v-for="file in fileList" :key="file">
                  <img class="menuImg" :src="file.name" />
                </splide-slide>
              </splide>
            </div>
          </div>
          <!-- 종료 -->
          <div class="col-1 storeKind">
            <small class="text-muted">{{ shopInfo.storeKind }}</small>
          </div>
        </div>
        <div class="row address">
          <small> {{ shopInfo.address }}, {{ shopInfo.addressDetail }} </small>
        </div>
      </a>
    </div>
  </div>
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
import http from "@/api/http";

export default {
  components: {
    Splide,
    SplideSlide,
  },

  props: ["shopInfo"],
  mounted() {
    console.log(this.shopInfo.storeId); // 사진을 불러오기위함
    if (this.shopInfo.storeId != null) {
      http
        .get("/store/getStoreFiles", {
          params: {
            storeId: this.shopInfo.storeId,
          },
        })
        .then((res) => {
          if (res.status === 200) {
            this.fileList = res.data;
            console.log(res.data);
          }
        })
        .catch((err) => {
          console.log("사진불러오는데 에러" + err);
        });
    }
  },
  data() {
    return {
      fileList: [],
      options: {
        rewind: true, //첫 번째 슬라이드 이전 또는 마지막 슬라이드 이후에 슬라이더를 되감는지 여부.
        gap: "2em", // 슬라이드 사이의 간격.
        width: 700,
        speed: 300, // 밀리초 단위의 전환 속도입니다.
        rewindSpeed: 300, //되감기 시 전환 속도(밀리초)입니다.
        perPage: 5, //페이지당 표시할 슬라이드 수를 결정합니다.
        perMove: 1, //슬라이더가 다음 또는 이전 페이지로 이동할 때 이동해야 하는 슬라이드 수를 결정합니다.
        autoplay: true, //자동재생 활성화 여부.
        interval: 3000, //밀리초 단위의 자동 재생 간격.
        pauseOnHover: true, //슬라이더를 가리키고 있는 동안 자동 재생을 중지할지 여부입니다.
        resetProgress: true, //재개 시 자동 재생 타이머의 진행 상황을 재설정할지 여부입니다.
        focus: "center", //어떤 슬라이드에 초점을 맞춰야 하는지 결정합니다.
        type: "loop",
        breakpoints: {
          1585: {
            width: 700,
            perPage: 5,
          },
          1430: {
            width: 600,
            perPage: 3,
          },
          930: {
            width: 400,
            perPage: 3,
          },
        },
      },
    };
  },
};
</script>

<style scoped>
.shopList {
  width: 100%;
  height: 500px;
  display: grid;
}
.shopBox {
  height: 100px;
}
.menuImg {
  width: 100px;
  height: 70px;
}
.storeTitle {
  text-align: left;
  padding: 0px;
}
.storeKind {
  text-align: right;
  width: 70px;
  padding: 0px;
}
.address {
  text-align: left;
  justify-self: start;
  padding: 0px;
}
@media screen and (min-width: 1200px) {
  .slide {
    position: absolute;
    display: none;
  }
}
</style>
