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
        <h5 class="mb-1">세상에서 제일 맛있는 짜장면집</h5>
      </div>
      <!-- 음식사진시작 -->
      <div class="col">
        <div class="slide">
          <splide :options="options">
            <splide-slide>
              <img
                class="menuImg"
                src="https://cdn.crowdpic.net/list-thumb/thumb_l_A08F847BFEFEE3253E37C141B669AA68.jpg"
              />
            </splide-slide>
            <splide-slide>
              <img
                class="menuImg"
                src="https://cdn.crowdpic.net/list-thumb/thumb_l_A08F847BFEFEE3253E37C141B669AA68.jpg"
              />
            </splide-slide>
            <splide-slide>
              <img
                class="menuImg"
                src="https://t1.daumcdn.net/liveboard/realfood/33f2dfa47b3e472bb782011ab4376194.JPG"
              />
            </splide-slide>
            <splide-slide>
              <img
                class="menuImg"
                src="https://recipe1.ezmember.co.kr/cache/recipe/2016/04/22/8e8d26b8f9a2fef59896ba3e9fc22c0c1.jpg"
              />
            </splide-slide>
            <splide-slide>
              <img
                class="menuImg"
                src="https://image.news1.kr/system/hp/2019/11/29/3943620/dims/optimize"
              />
            </splide-slide>
            <splide-slide>
              <img
                class="menuImg"
                src="https://blog.kakaocdn.net/dn/0MBDg/btq2krWATq4/eFdLBCVquxKFlJESeoKpI1/img.png"
              />
            </splide-slide>
          </splide>
        </div>
      </div>
      <!-- 종료 -->
      <div class="col-1 storeKind">
        <small class="text-muted">중국집</small>
      </div>
    </div>
    <div class="row address">
      <small>
        학익동 663-1번지 5층 남구 인천광역시 KR
      </small>
    </div>
    </a>
  </div>
</div>
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";

export default {
  components: {
    Splide,
    SplideSlide,
  },
  mounted() {
    console.log("불러옴"); // 사진을 불러오기위함
  },
  data() {
    return {
      options: {
        rewind: true, //첫 번째 슬라이드 이전 또는 마지막 슬라이드 이후에 슬라이더를 되감는지 여부.
        gap: "2em", // 슬라이드 사이의 간격.
        width: 100,
        speed: 300, // 밀리초 단위의 전환 속도입니다.
        rewindSpeed: 300, //되감기 시 전환 속도(밀리초)입니다.
        perPage: 1, //페이지당 표시할 슬라이드 수를 결정합니다.
        perMove: 1, //슬라이더가 다음 또는 이전 페이지로 이동할 때 이동해야 하는 슬라이드 수를 결정합니다.
        autoplay: true, //자동재생 활성화 여부.
        interval: 3000, //밀리초 단위의 자동 재생 간격.
        pauseOnHover: true, //슬라이더를 가리키고 있는 동안 자동 재생을 중지할지 여부입니다.
        resetProgress: true, //재개 시 자동 재생 타이머의 진행 상황을 재설정할지 여부입니다.
        focus: "center", //어떤 슬라이드에 초점을 맞춰야 하는지 결정합니다.
        type: "loop",
        breakpoints: {
          1200 : {
            width: 600,
            perPage: 5,
          },
          1100 : {
            width: 500,
            perPage: 3,
          },
          1000 : {
            width: 400,
            perPage: 3,
          },
          900 : {
           width: 300,
            perPage: 3,
          },
        },
      },
    };
  },
};
</script>

<style scoped>
@media screen and (min-width: 1200px) { 
  .slide { 
    display: none;
  }
}
.storeTitle {
  text-align:left;
  padding: 0px;
}
.storeKind {
  text-align:right; 
  width:70px; 
  padding: 0px;
}
.menuImg {
    width:100px;
    height:70px;
}
.shopList {
  width:100%;
  height: 500px;
  display: grid;
}
.shopBox {
  height: 100px;
}
.address {
  text-align:left; 
  justify-self: start; 
  padding: 0px;
}
</style>