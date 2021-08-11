<template>
  <div class="shopList">
    <!-- shopList 시작-->
    <!-- v-for -->
    <a
      href="#"
      class="
        list-group-item list-group-item-action
        flex-column
        align-items-start
      "
      v-for="(shop, index) in $store.state.shopList.shops"
      :key="index"
    >
      <div class="d-flex w-100 justify-content-between">
        <h5 class="mb-1">{{ shop.shopName }}</h5>
        <small class="text-muted">{{ shop.regDate }}</small>
      </div>
      <p class="mb-1">{{ shop.content }}</p>
    </a>
    <!-- 끝 -->
  </div>
</template>

<script>
export default {};
</script>

<style></style>
