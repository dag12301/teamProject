<template>
    <div class="Map">
        <Kakao></Kakao>
    </div>
</template>

<script>
import Kakao from "@/components/map/Kakao.vue";

export default {
  components: {
    Kakao,
  },
};
</script>

<style scoped>
.Map {
    width: 100%;
}
</style>