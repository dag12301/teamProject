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
        {{ storeInfo }}
        <!-- 지도와메뉴등..가게상세페이지는 같이쓸것인가? -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["storeInfo", "storeFiles"],
};
</script>

<style scoped>
#imageWrapper {
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
