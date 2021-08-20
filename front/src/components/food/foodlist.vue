<template>
  <div
    class="col-xs-8 col-sm-6"
    v-for="(foodlist, index) in $store.state.foodlist.foodlists"
    :key="index"
  >
    <a href="#" class="list-group-item" style="height: 100px"
      ><router-link :to="{ name: 'FoodDetail' }" exact>
        <div class=".col-md-3 .col-md-pull-9 f-left">
          <img
            class="menu-img"
            src="https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDJfMjY4/MDAxNTY5OTc1Njg1MzA3.rRfHuw5dm4Lw-nbgvTlW92K9JHTMbdy6lbkfEGJT6Fsg.au6b5GbdbOaykDXH2mXC6JnKhF8XE5RcXdlbySPq97sg.JPEG.wlsgkthd18/1569975685162.jpg?type=w800"
          />
        </div>
        <div class=".col-md-9 .col-md-push-3 f-right">
          <div class="d-flex w-100 justify-content-between">
            <h6 class="mb-1">{{ foodlist.title }}</h6>
            <!--가게이름-->
            <small>{{ foodlist.time }}</small>
          </div>
          <p class="mb-1 menu-p">{{ foodlist.explain }}</p>
          <!--소개-->
          <p class="menu-p" style="margin-bottom: 5px">
            {{ foodlist.address }}
          </p>
          <!--주소-->
          <p class="menu-p">
            {{ foodlist.phone }}&nbsp;&nbsp;<span class="details">{{
              foodlist.detail
            }}</span>
          </p>
        </div>
      </router-link></a
    >
  </div>
</template>

<style scoped>
.col-xs-8 .list-group-item {
  border: 2px solid lightskyblue;
}
.menu-img {
  width: 80px;
  height: 80px;
  left: 14px;
}
.mb-1 {
  font-weight: bold;
}
.menu-p {
  text-align: left;
  font-size: 10px;
}
.f-left {
  float: left;
  width: 28%;
}
.f-right {
  float: right;
  width: 70%;
  height: 80px;
}

.row div div a {
  margin-top: 10px;
}
</style>
