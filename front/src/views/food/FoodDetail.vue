<template>
  <div>
    가게정보 : {{ shopInfo }} <br />
    음식정보 : {{ foodInfo }}
  </div>
</template>

<script>
import http from "@/api/http";
export default {
  mounted() {
    let storeId = this.$route.query.shopInfo;
    this.getStoreInfo(storeId);
  },
  data() {
    return {
      shopInfo: null,
    };
  },
  methods: {
    getStoreInfo(storeId) {
      http
        .get("/store/getStoreInfos", {
          params: {
            storeId: storeId,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.shopInfo = res.data;
        });
    },
  },
};
</script>

<style></style>
