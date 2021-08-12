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
  akiBoolean: true,
  components: {
    Arkinator,
  },
  computed: {
    ...mapGetters({
      akion: "toggle/aki",
      
    })
  },
  created() {
    const doAki = window.localStorage.getItem("doAki");
    if (doAki == null || !!doAki == false) {
      console.log("아키네이터 안할거라고 쿠키에 저장해둠");
    }
    console.log("아키네이터 생성됨");
  },
};
</script>
<style scoped></style>
