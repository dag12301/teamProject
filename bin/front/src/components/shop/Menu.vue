<template>
  <splide :options="options">
    <splide-slide>
      <div class="img">
        <img
          src="https://folkency.nfm.go.kr/upload/img/20190305/20190305123024_t_.jpg"
        />
        <h1>짜장면</h1>
      </div>
    </splide-slide>
    <splide-slide>
      <div class="img">
        <img
          src="https://cdn.crowdpic.net/list-thumb/thumb_l_A08F847BFEFEE3253E37C141B669AA68.jpg"
        />
        <h1>짬뽕</h1>
      </div>
    </splide-slide>
    <splide-slide>
      <div class="img">
        <img
          src="https://t1.daumcdn.net/liveboard/realfood/33f2dfa47b3e472bb782011ab4376194.JPG"
        />
        <h1>탕수육</h1>
      </div>
    </splide-slide>
    <splide-slide>
      <div class="img">
        <img
          src="https://recipe1.ezmember.co.kr/cache/recipe/2016/04/22/8e8d26b8f9a2fef59896ba3e9fc22c0c1.jpg"
        />
        <h1>양장피</h1>
      </div>
    </splide-slide>
    <splide-slide>
      <div class="img">
        <img
          src="https://image.news1.kr/system/hp/2019/11/29/3943620/dims/optimize"
        />
        <h1>유산슬</h1>
      </div>
    </splide-slide>
    <splide-slide>
      <div class="img">
        <img
          src="https://blog.kakaocdn.net/dn/0MBDg/btq2krWATq4/eFdLBCVquxKFlJESeoKpI1/img.png"
        />
        <h1>고량주</h1>
      </div>
    </splide-slide>
  </splide>
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
export default {
  components: {
    Splide,
    SplideSlide,
  },
  data() {
    return {
      options: {
        rewind: true, //첫 번째 슬라이드 이전 또는 마지막 슬라이드 이후에 슬라이더를 되감는지 여부.
        width: 1000, //슬라이더 최대 너비를 정의합니다.
        height: 280,
        gap: "3em", // 슬라이드 사이의 간격.
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
      },
    };
  },
};
</script>

<style>
img {
  width: 200px;
  height: 200px;
}
</style>
