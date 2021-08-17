<template>
  <div id="event-wrapper">
    <div id="event-content">
      <div class="event-main-box">
        <section class="event-tex-box">
          <h2 class="event-h2">이벤트</h2>
          <ul class="event-ul">
            <li class="event-li"><a href="" class="event-a">전체</a></li>
            <li class="event-li"><a href="" class="event-a">진행중</a></li>
            <li class="event-li"><a href="" class="event-a">종료</a></li>
          </ul>
        </section>
      </div>
      <div class="coupon-div">
        <div class="coupon-box">
          <coupon></coupon>
        </div>
      </div>
      <div class="coupon-div">
        <div class="coupon-box">
          <couponend></couponend>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import coupon from "../../components/event/coupon.vue";
import couponend from "../../components/event/couponend.vue";

export default {
  components: {
    coupon,
    couponend,
  },
};
</script>

<style scoped>
#event-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}
#event-content {
  width: 100%;
}
.event-main-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}
.event-tex-box {
  margin-top: 30px;
}
.event-li {
  list-style-type: none;
  float: left;
  margin-left: 20px;
}
.event-a {
  text-decoration: none;
  font-weight: bold;
  color: #b8b8b8;
}
.event-tex-box {
  width: 500px;
  margin-left: 50px;
}
.event-h2 {
  float: left;
  font-weight: bold;
  margin-top: 5px;
}
.event-ul {
  float: right;
  line-height: 30px;
  margin-top: 5px;
}

.coupon-div {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}
.coupon-box {
  width: 840px;
}
</style>
