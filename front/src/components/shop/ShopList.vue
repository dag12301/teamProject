<template>
  <a href="#" class="shopList">
    <div class="row storeHeader">
      <div class="col-10 storeTitle">
        <h5 class="mb-1">{{ shopInfo.name }}</h5>
      </div>
      <div class="col-2 storeKind">
        <small class="text-muted">{{ getStoreKind }}</small>
      </div>
    </div>
    <div class="row">
      <!-- 음식사진시작 -->
      <div class="col-12">
        {{ fileList }}
      </div>
      <!-- 종료 -->
    </div>
    <div class="row address">
      <small> {{ shopInfo.address }}, {{ shopInfo.addressDetail }} </small>
    </div>
  </a>
</template>

<script>
import http from "@/api/http";

export default {
  components: {},

  props: ["shopInfo"],
  mounted() {
    if (this.shopInfo.storeId != null) {
      http
        .get("/store/getStoreFiles", {
          params: {
            storeId: this.shopInfo.storeId,
          },
        })
        .then((res) => {
          if (res.status === 200) {
            this.fileList = res.data;
            console.log(res.data);
            this.renderSplide();
          }
        })
        .catch((err) => {
          console.log("사진불러오는데 에러" + err);
        });
    }
  },
  data() {
    return {
      fileList: [],
    };
  },
  methods: {},
  computed: {
    getStoreKind() {
      let arr = this.shopInfo.storeKind.split(",");
      return arr[0] + ", " + arr[1] + "...";
    },
  },
};
</script>

<style scoped>
.shopList {
  width: 100%;
  display: grid;
  justify-content: space-around;
  color: black;
  text-decoration: none;
}
.storeTitle {
  text-align: left;
  padding: 5px 0 0;
}
.storeKind {
  padding: 5px 0 0;
}
.address {
  text-align: left;
  justify-self: start;
  padding: 0px;
}
@media screen and (min-width: 1200px) {
  .slide {
    width: 300px;
    display: none;
  }
  .menuImg {
    width: 0px;
    height: 0px;
    border: 2px solid blue;
  }
  .storeHeader {
    width: 300px;
    height: 50px;
  }
}
@media screen and (max-width: 1200px) {
  .menuImg {
    width: 100px;
    height: 70px;
  }
}
</style>
