<template>
  <div
    id="map"
    style="
      width: 100%;
      height: 450px;
      border: 1px solid gray;
      border-radius: 5px;
    "
    @change="refreshLocation"
  ></div>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["GET_LOCAL"]),
  },
  data() {
    return {
      latitude: 37.43884057982199,
      longitude: 126.675102369038,
      local: null,
      map: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0feaa40f6d94ad4428a4f4f8a6cab340&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },

  methods: {
    ...mapMutations(["SET_LOCAL", "SET_LETLNG"]),
    initMap() {
      this.refreshLocation();
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(this.latitude, this.longitude), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      this.map = new kakao.maps.Map(container, options); // eslint-disable-line no-unused-vars
      //지도 생성 및 객체 리턴
      const markerPosition = new kakao.maps.LatLng(
        this.latitude,
        this.longitude
      );

      const marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(this.map);
    },
    refreshLocation() {
      console.log("refreshLocation");
      return navigator.geolocation.watchPosition((position) => {
        this.setLocation(position.coords.latitude, position.coords.longitude);
      });
    },
    setLocation(latitude, longitude) {
      this.latitude = latitude;
      this.longitude = longitude;
      this.SET_LETLNG(latitude, longitude);
      console.log("사용자 위치 추적: " + latitude + ", " + longitude);

      let geocoder = new kakao.maps.services.Geocoder();

      let coord = new kakao.maps.LatLng(latitude, longitude);

      const addressSearch = (coord) => {
        return new Promise((resolve, reject) => {
          geocoder.coord2Address(
            coord.getLng(),
            coord.getLat(),
            function (result, status) {
              if (status === kakao.maps.services.Status.OK) {
                resolve(result);
              } else {
                reject(status);
              }
            }
          );
        });
      };
      const setLocal = (result) => {
        this.local = result[0].address;
        this.$store.commit("SET_LOCAL", result[0].address);
        var coords = new kakao.maps.LatLng(this.latitude, this.longitude);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: coords,
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
          content:
            '<div style="width:150px;text-align:center;padding:6px 0;">현재위치</div>',
        });
        infowindow.open(this.map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        this.map.setCenter(coords);
      };
      // async
      (async () => {
        try {
          const result = await addressSearch(coord);
          setLocal(result);
        } catch (e) {
          console.log(e);
        }
      })();
    },
    // 지도이동
  },
};
</script>

<style></style>
