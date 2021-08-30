<template>
  <div>
    <div class="m-4">
      <h1>주문상세정보페이지</h1>
      <hr />
      <div v-if="loaded == false">
        <div class="spinner-border" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
      <div v-else>
        <!-- 로딩완료 -->
        로딩완료, {{ order }}
        {{ foodList }}
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
export default {
  data() {
    return {
      orderId: null,
      loaded: false,
      order: null,
      foodList: null,
    };
  },
  mounted() {
    this.orderId = this.$route.query.orderId;
    http
      .get("/order/getOrderedFoodList", {
        params: {
          orderId: this.orderId,
        },
      })
      .then((res) => {
        this.order = res.data.order;
        this.foodList = res.data.foodList;
        this.loaded = true;
      });
  },
};
</script>

<style scoped></style>
