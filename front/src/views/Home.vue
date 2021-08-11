<template>
  <!-- 아키네이터 띄우기 -->
  <div class="home">
    <Arkinator v-if="akion" />
  </div>
</template>

<script>
// @ 는 /src 의 축약입니다.
import Arkinator from "@/components/akinator/Akinator";
import { mapGetters } from "vuex";
export default {
  name: "Home",
  components: {
    Arkinator,
  },
  computed: {
    ...mapGetters({
      akion: "toggle/aki",
    }),
  },
};
</script>
<style scoped></style>
