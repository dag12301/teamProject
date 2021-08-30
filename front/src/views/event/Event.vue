<template>
  <div id="event-wrapper">
    <div id="event-content">
      <div class="event-main-box">
        <section class="event-tex-box">
          <h2 class="event-h2">이벤트</h2>
          <ul class="event-ul">
            <li class="event-li">
              <button
                class="event-a"
                @click="$router.push({ name: 'coupon-all' })"
              >
                전체
              </button>
            </li>
            <li class="event-li">
              <button
                class="event-a"
                @click="$router.push({ name: 'coupon-on' })"
              >
                진행중
              </button>
            </li>
            <li class="event-li">
              <button
                class="event-a"
                @click="$router.push({ name: 'coupon-end' })"
              >
                종료
              </button>
            </li>
          </ul>
        </section>
      </div>
      <div class="coupon-div">
        <div class="coupon-box">
          <router-view> </router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script></script>

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
  border: none;
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
}
.coupon-box {
  width: 840px;
}
</style>
