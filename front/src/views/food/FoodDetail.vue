<template style="background-color: #FAFAFA;">
  <div class="storeBox" style="background-color: #FAFAFA;">
    <div class="storeName" style="font-weight: 800; font-size: 30px; margin-top: 30px;">
      {{shopInfo.storeInfo.name}}
    </div>
    <hr style="width: 100%;"/>
    <div class="storeText" style="width: 100%; height: 300px;">
      <div class="storeImg" style="float:left; width: 45%; text-align: right;">
        <img :src="shopInfo.fileList[0].name" class="col-md-6 mt-2" id="image" style="width: 280px; height: 280px;"/>
      </div>
      <div class="storeInf" style="float:right; width: 50%; text-align: left;">
        
        <p style="color: gray; margin-bottom: 35px;">{{shopInfo.storeInfo.storeKind}}</p>
        <span style="font-weight: 600; font-size: 17px;">업체정보</span>
        <hr style="margin-top: 5px;"/>
        <table>
          <tr>
            <td style="color: gray;">전화번호</td>
            <td style="padding-left: 20px;">{{shopInfo.storeInfo.phone}}</td>
          </tr>
          <tr>
            <td style="color:gray; margin-top: 20px;">주소</td>
            <td style="padding-left: 20px;">{{shopInfo.storeInfo.address}} {{shopInfo.storeInfo.addressDetail}}</td>
          </tr>
          <tr>
            <td style="color:gray; margin-top: 20px;">별점</td>
            <td style="padding-left: 20px;"></td>
          </tr>
        </table>
        
      </div>  
    </div>
    <div
          class="btn-group"
          role="group"
          aria-label="Basic radio toggle button group"
          style="width: 100%; height: 45px; background-color: #FAFAFA;"
        >
          <input
            type="radio"
            class="btn-check"
            name="btnradio"
            id="btnradio1"
            autocomplete="off"
            @click="$router.push({ name: 'shopMenu' })"
            checked
          />
          <label class="btn btn-outline-primary" for="btnradio1"
            >메뉴보기</label
          >                               <!-- StoreMenu.vue -->

          <input
            type="radio"
            class="btn-check"
            name="btnradio"
            id="btnradio2"
            autocomplete="off"
            @click="$router.push({ name: 'shopMap' })"
          />
          <label class="btn btn-outline-primary" for="btnradio2"
            >지도보기</label
          >

          <input
            type="radio"
            class="btn-check"
            name="btnradio"
            id="btnradio3"
            autocomplete="off"
            @click="$router.push({ name: 'shopReview' })"
          />
          <label class="btn btn-outline-primary" for="btnradio3">후기</label>
        </div>
        <router-view></router-view>
        
        <div class="storeBtn" style="width: 100%; height: 80px;">
          <div class="storeShopping" style="float:left; width: 40%; padding: 10px; height: 100%;">
            <button style="border: 0; width: 100%; height: 100%; background-color: yellow; font-weight: 600; font-size: 20px;" >장바구니</button>
          </div>
          <div class="storeOrder" style="float:right; width: 60%; padding:10px; height: 100%;">
            <button style="border: 0; width: 100%; height: 100%; background-color: skyblue; font-weight: 600; font-size: 20px;">주문하기</button>
          </div>
        </div>
  </div>
    
    
    <div style="border: 1px solid black;">
    
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

