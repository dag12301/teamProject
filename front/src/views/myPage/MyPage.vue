<template>
<center>
  <div id="profileMenu">
    <ul>
      <li>
        <a @click="changeView('myProfile')">내정보</a>
      </li>

      <li>
        <a @click="changeView('myCoupon')">쿠폰</a>
      </li>

      <li>
        <a @click="changeView('myOrderHistory')">주문내역</a>
      </li>

      <li>
        <a @click="changeView('myReview')">리뷰관리</a>
      </li>

      <li>
        <a @click="changeView('myQuestion')">질문내역</a>
      </li>
    </ul>
  </div>
  <my-profile v-if="currentView == 'myProfile'" />
  <my-coupon v-if="currentView == 'myCoupon'" />
  <my-order-history v-if="currentView == 'myOrderHistory'" />
  <my-review v-if="currentView == 'myReview'" />
  <my-question v-if="currentView == 'myQuestion'" />
</center>
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
#profileMenu {
  margin: 50px;
  width: 1000px;
  height: 80px;
}
#profileMenu ul li {
  float: left;
  width: 200px;
  height: 80px;
  line-height: 80px;
  text-align: center;
  border: 1px solid black;
  overflow: hidden;
  cursor:pointer;
}
#profileMenu ul li a {
  display: block;
}
#profileMenu ul li a:hover {
  background: gray;
  color:#fff;
}
a {
  text-decoration:none;
  color:#404040;
}
ul {
  padding: 0px;
}
li {
  list-style:none;
}
.side-row {
  border-bottom: 1px solid black;
}
.item {
  padding: 20px;
}

</style>
