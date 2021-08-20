<template>
  <Modal>
    <template v-slot:header>
      <div>
        <div class="input-group mb-1">
          <input
            type="text"
            class="form-control"
            placeholder="주소를 입력해주세요"
            aria-label="Recipient's username"
            aria-describedby="button-addon2"
            v-model="keyword"
          />
          <button
            class="btn btn-outline-secondary"
            type="button"
            id="button-addon2"
            @click="searchPlaces()"
          >
            주소검색
          </button>
          <div style="width: 100px; text-align: center">상세주소</div>
          <input
            type="email"
            class="form-control"
            id="exampleFormControlInput1"
            placeholder="상세주소를 입력해주세요"
            v-model="addressDetail"
          />
        </div>
      </div>
    </template>
    <template v-slot:body>
      <div id="map_wrap">
        <div id="map"></div>
        <div id="menu_wrap" class="bg_white">
          <div class="option"></div>
          <ul id="placesList">
            <li
              v-for="(place, i) in places"
              :key="i"
              @click="displayInfo(i, place)"
            >
              <span class="markerbg marker_1">{{ i + 1 }}</span>
              <div class="item info">
                {{ place.place_name }}
                <span v-if="place.road_address_name"
                  >{{ place.road_address_name }}
                  <span class="jibun gray">{{ places.address_name }}</span>
                </span>
                <span v-else> {{ place.address_name }}</span>
                <span>{{ place.phone }}</span>
              </div>
            </li>
          </ul>
          <div id="pagination"></div>
        </div>
      </div>
    </template>
    <template v-slot:footer>
      <span v-if="selectedPlace.address_name">
        {{ selectedPlace.address_name }}, {{ addressDetail }} 를
        선택하시겠습니까?
      </span>
      <span v-else> 주소를 검색하고 왼쪽의 리스트에서 선택해주세요. </span>
      <div class="btn btn-success" @click="confirm">확인</div>
      <div class="btn btn-danger" @click="SET_MODAL_MAP(false)">취소</div>
    </template>
  </Modal>
</template>

<script>
import Modal from "./Modal_map.vue";
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      keyword: "",
      map: null,
      ps: null,
      infowindow: null,
      markers: [],
      places: [],
      selectedPlace: [],
      addressDetail: "",
    };
  },
  components: {
    Modal,
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      [this.map, this.ps, this.infowindow] = this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0feaa40f6d94ad4428a4f4f8a6cab340&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapMutations(["SET_MODAL_MAP", "SET_SELECTED_PLACE"]),
    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.43884057982199, 126.675102369038), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      var map = new kakao.maps.Map(container, options); // eslint-disable-line no-unused-vars
      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places();
      // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      return [map, ps, infowindow];
    },
    searchPlaces() {
      if (!this.keyword.replace(/^\s+|\s+$/g, "")) {
        alert("키워드를 입력해주세요!");
        return;
      }
      if (!this.map || !this.ps) {
        [this.map, this.ps, this.infowindow] = this.initMap();
      }
      // 장소 검색 객체를 통해 키워드로 장소검색을 요청합니다.
      this.ps.keywordSearch(this.keyword, this.placesSearchCB);
    },
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상 검색이 완료되었으면 검색목록콰 마커를 표시
        this.displayPlaces(data);
        // 페이지 번호를 표현
        // this.displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다.");
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    },
    displayPlaces(places) {
      this.places = places;
      // places 검색결과 데이터가 들어옵니다.
      console.log(places);

      var menuEl = document.getElementById("menu_wrap");
      var bounds = new kakao.maps.LatLngBounds();

      this.removeMarker();

      for (var i = 0; i < places.length; i++) {
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
          marker = this.addMarker(placePosition, i);

        bounds.extend(placePosition);
      }
      this.map.setBounds(bounds);
      menuEl.scrollTop = 0;
    },
    addMarker(position, idx) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayInfo(index, place) {
      var content =
        '<div style="padding:5px;z-index:1;">' + place.place_name + "</div>";

      this.selectedPlace = place;

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, this.markers[index]);
    },
    confirm() {
      this.SET_SELECTED_PLACE([this.selectedPlace, this.addressDetail]);
      this.SET_MODAL_MAP(false);
    },
  },
};
</script>

<style scoped>
#map {
  width: 680px;
  height: 380px;
  border: 1px solid gray;
  border-radius: 5px;
  position: relative;
  overflow: hidden;
  right: 160px;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 260px;
  margin: 30px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
  left: -145px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>
