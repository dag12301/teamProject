<template>
  <div class="row side-row">
    <div class="profilelink d-inline" @click="changeView('myProfile')">
      <div class="item">내정보</div>
    </div>

    <div class="profilelink d-inline" @click="changeView('myCoupon')">
      <div class="item">쿠폰</div>
    </div>

    <div class="profilelink d-inline" @click="changeView('myOrderHistory')">
      <div class="item">주문내역</div>
    </div>

    <div class="profilelink d-inline" @click="changeView('myReview')">
      <div class="item">리뷰관리</div>
    </div>

    <div class="profilelink d-inline" @click="changeView('myQuestion')">
      <div class="item">질문내역</div>
    </div>
  </div>
  <my-profile v-if="currentView == 'myProfile'" />
  <my-coupon v-if="currentView == 'myCoupon'" />
  <my-order-history v-if="currentView == 'myOrderHistory'" />
  <my-review v-if="currentView == 'myReview'" />
  <my-question v-if="currentView == 'myQuestion'" />
</template>
<script>
import myProfile from "@/components/myPage/MyProfile.vue";
import myCoupon from "@/components/myPage/MyCoupon.vue";
import myOrderHistory from "@/components/myPage/OrderMenu.vue";
import myReview from "@/components/myPage/MyReview.vue";
import myQuestion from "@/components/myPage/Question.vue";

export default {
  components: { myProfile, myCoupon, myOrderHistory, myReview, myQuestion },
  data() {
    return {
      currentView: "myProfile",
    };
  },
  methods: {
    changeView(request) {
      this.currentView = request;
    },
  },
};
</script>

<style>
.side-row {
  border-bottom: 1px solid black;
}
.item {
  padding: 20px;
}
.profilelink:link,
.profilelink:visited,
.profilelink:hover {
  text-decoration: none;
  color: black;
}
.profilelink {
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
}
.profilelink {
  line-height: 42px;
  padding: 0;
  border: none;
}
.profilelink:hover {
  background: transparent;
  box-shadow: none;
  background: rgb(151, 151, 151);
}
.profilelink:before,
.profilelink:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  box-shadow: -1px -1px 5px 0px black, 7px 7px 20px 0px #0003,
    4px 4px 5px 0px #0002;
  transition: 400ms ease all;
}
.profilelink:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
.profilelink:hover:before,
.profilelink:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
</style>
