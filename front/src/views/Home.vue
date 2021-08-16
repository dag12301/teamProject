<template>
  <div class="ShopList">
    <div id="layoutSidenav_content">
      <main>
        <!--메인 이름, 지도, 가게 리스트 -->
        <div class="container-fluid px-4">
          <!--메인 이름 WHAT POO -->
          <h1 class="mt-4">WHAT POO</h1>
          <div class="row">
            <!--지도 -->
            <div class="col-xl-7">
              <div class="card mb-4">
                <kakao></kakao>
              </div>
            </div>
            <!--가게 리스트 -->
            <div class="col-xl-5">
              <div class="card mb-4">
                <div class="list-group">
                  <ShopList></ShopList>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 메뉴 리스트 시작-->
        <div class="card mb-4"></div>
      </main>
    </div>
  </div>
</template>

<script>
<<<<<<< HEAD
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
      
    })
  },
  created() {
    console.log("아키네이터 생성됨");
  },
};
</script>
<style scoped></style>
=======
import Kakao from "@/components/map/Kakao.vue";
import ShopList from "@/components/shop/ShopList.vue";

export default {
  components: {
    Kakao,
    ShopList,
  },
};
</script>

<style></style>
>>>>>>> e0f0338db4ada06b0aa9991639b474ccaabac6ab
