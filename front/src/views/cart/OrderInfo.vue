<template>
  <div>
    <div class="m-4">
      <h1>주문내역</h1>
      <hr />
      <div v-if="loaded == false">로딩중</div>
      <div v-else>
        <!-- 로딩완료 -->
        로딩완료, {{}}
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
        console.log(res.data);
        this.loaded = true;
      });
  },
};
</script>

<style scoped></style>
