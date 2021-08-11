<template>
  <!-- 메뉴 -->
  <div
    class="listAction list-num2"
    v-for="(menu, index) in $store.state.menu.menuList"
    :key="index"
  >
    <!-- 메뉴 리스트 시작-->
    <a href="#" class="list-group-item list-group-item-action">
      <img src="{{menu.pht}}" />
      <div class="f-right">
        <div class="d-flex w-100 justify-content-between">
          <!--가게종류-->
          <h5 class="col-mb-1">{{ menu.shopType }}</h5>
          <!--오픈시간-->
          <small>{{ menu.regDate }}</small>
        </div>
        <!--가게이름-->
        <p class="col-mb-1">{{ menu.shopName }}</p>
        <!--주소-->
        <small class="Address">{{ menu.address }}</small
        ><br />
        <!--전화번호-->
        <!--자세히보기-->
        <small class="phoneNum">{{ menu.number }}</small
        >&nbsp;&nbsp;<small><span class="details">상세보기</span></small>
      </div>
    </a>
    <!-- 끝-->
  </div>
</template>

<script>
export default {};
</script>

<style>
.listAction {
  margin-top: 10px;
}

.listAction .list-group-item {
  width: 100%;
  float: right;
}

.listAction .list-group-item img {
  float: left;
  width: 20%;
}

.listAction .list-group-item .f-right {
  float: right;
  width: 75%;
  height: 22vh;
}

.detail {
  float: left;
}
</style>
