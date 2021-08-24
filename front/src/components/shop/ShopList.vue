<template>
  <div class="shopList" style="width: 100%; height: 450px">
    <a
      href="#"
      class="
        list-group-item list-group-item-action
        flex-column
        align-items-start
      "
    >
      <div class="d-flex w-100 justify-content-between">
        <h5 class="mb-1">{{ shopInfo.name }}</h5>
        <small class="text-muted">{{ shopInfo.storeKind }}</small>
      </div>
      <small class="text-muted d-flex"
        >{{ shopInfo.address }}, {{ shopInfo.addressDetail }}</small
      >
    </a>
  </div>
</template>

<script>
export default {
  props: ["shopInfo"],
};
</script>

<style scoped></style>
