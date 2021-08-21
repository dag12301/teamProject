<template>
  <div>
    <h1>등록한 음식들</h1>
    <span
      >등록한 음식이 없으시네요?
      <router-link to="/addMenu">음식 추가하기</router-link>
    </span>
    <!-- 음식들 컴포넌트로 뿌려줌 -->
    <div id="tableWrapper">
      <menu-box v-if="list"></menu-box>
      <no-menu></no-menu>
    </div>
  </div>
</template>

<script>
import MenuBox from "@/components/store/StoreMenu.vue";
import NoMenu from "@/components/store/StoreMenuNone.vue";

export default {
  components: {
    MenuBox,
    NoMenu,
  },
  data() {
    return {
      list: false,
    };
  },
};
</script>

<style scoped>
#tableWrapper {
  width: 100%;
  height: 100%;
  display: table;
  margin: 1rem;
}
</style>
