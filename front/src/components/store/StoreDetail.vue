<template>
  <div>
    <div class="container m-4">
      <div class="row">
        <span class="col align-self-center" id="subject">상호명</span>
        <div class="col-md-8 align-content-between">
          <h2>
            {{ storeInfo.name }}
          </h2>
        </div>
        <div class="btn btn-primary col-md-2 mb-2">정보수정</div>
      </div>
      <div class="row">
        <div id="imageWrapper">
          <img :src="storeFiles[0].name" class="col-md-6 mt-2" id="image" />
          <div class="row">
            <div class="col mt-4">
              <div id="subject">상태</div>
              <div id="subject_a">정상영업중</div>
              <div id="subject">전화번호</div>
              <div id="subject_a">{{ storeInfo.phone }}</div>
              <div id="subject">주소</div>
              <div id="subject_a">
                {{ storeInfo.address }}, {{ storeInfo.addressDetail }}
              </div>
            </div>
          </div>
        </div>
        <div
          class="btn-group"
          role="group"
          aria-label="Basic radio toggle button group"
        >
          <input
            type="radio"
            class="btn-check"
            name="btnradio"
            id="btnradio1"
            autocomplete="off"
            @click="$router.push({ name: 'StoreMenu' })"
            checked
          />
          <label class="btn btn-outline-primary" for="btnradio1"
            >메뉴보기</label
          >

          <input
            type="radio"
            class="btn-check"
            name="btnradio"
            id="btnradio2"
            autocomplete="off"
            @click="$router.push({ name: 'StoreMap' })"
          />
          <label class="btn btn-outline-primary" for="btnradio2"
            >지도보기</label
          >

          <input
            type="radio"
            class="btn-check"
            name="btnradio"
            id="btnradio3"
            autocomplete="off"
            @click="$router.push({ name: 'Review' })"
          />
          <label class="btn btn-outline-primary" for="btnradio3">후기</label>
        </div>

        <router-view></router-view>
      </div>
      {{ storeInfo }}
      <!-- 지도와메뉴등..가게상세페이지는 같이쓸것인가? -->
    </div>
  </div>
</template>

<script>
export default {
  props: ["storeInfo", "storeFiles"],
  components: {},
};
</script>

<style scoped>
.review {
  width: 100%;
  border: 1px solid black;
}
.review-left {
  float: left;
  background-color: orange;
  width: 50%;
  text-align: right;
  font-size: 40px;
  padding-right: 30px;
}
.review-right {
  float: right;
  background-color: yellow;
  width: 50%;
  text-align: left;
  padding-left: 30px;
}
.review-2 {
  margin-left: 10px;
}

.review2 {
  width: 100%;
  text-align: left;
  background-color: lightgray;
  height: 50px;
}
.review2-p {
  width: 100%;
  margin-top: 20px;
}

.review3 {
  width: 100%;
  border: 1px solid black;
  text-align: left;
  padding-top: 15px;
}
.review3-text {
  width: 100%;
}
.review3-span1 {
  font-size: 20px;
  font-weight: bold;
}
.review3-span2 {
  color: lightgray;
  margin-left: 10px;
}
.review3-a {
  text-decoration: none;
  color: gray;
  margin-left: 50%;
  font-weight: bold;
}

.review3-img {
  width: 100%;
  background-color: yellow;
  text-align: center;
}
#img {
  width: 100%;
}

.review3-menu {
  color: burlywood;
  font-size: 14px;
}

.star-1 {
  float: left;
  width: 22%;
}
.star-2 {
  float: right;
  width: 78%;
  border-left: 1.5px solid lightgray;
  padding-left: 10px;
}

.fa-star {
  color: burlywood;
}

#image {
  border-radius: 10px;
  float: left;
  margin: 5px;
  border: 2px solid grey;
}
#subject {
  font-weight: lighter;
  color: grey;
  font-size: 14px;
  margin: 10px 0 0 0;
}
#subject_a {
  font-weight: bold;
  font-size: 18px;
  margin: 2px 0 0 0;
}
</style>
