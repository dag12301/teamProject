<template>
  <div>
    <div class="wrapper m-4 container-fluid p-2">
      <div class="cart">
        <h1 class="m-2">장바구니</h1>
        <hr />
        <!-- 로딩이 안됬을땐, -->
        <div v-if="!onloaded">
          <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>
        <div v-else>
          <!-- 장바구니에 담지 않았을 때 -->
          <div v-if="!foodList || foodList.length == 0">
            <div class="foodContainer d-block m-4">
              <span> 장바구니가 비어있습니다! </span>
            </div>
          </div>
          <!-- 장바구니에 무엇인가를 담아뒀을 때 -->
          <div v-else-if="foodList.length > 0">
            <div v-for="food in foodList" :key="food">
              <div class="foodContainer d-block m-4">
                <div class="row">
                  <img :src="food.fileUrl" class="foodImage col-4" />
                  <div class="text col-8">
                    <h4>{{ food.name }}</h4>
                    <br />
                    <div class="mt-2 desc">{{ food.description }}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import http from "@/api/http";
export default {
  data() {
    return {
      foodList: [],
      onloaded: false,
    };
  },
  computed: {
    ...mapGetters(["checkCart"]),
  },
  mounted() {
    if (this.checkCart != null) {
      this.getFoodList(this.checkCart);
    } else {
      this.onloaded = true;
    }
  },
  methods: {
    getFoodList(foodIdSet) {
      const foodIdList = Array.from(foodIdSet);
      //
      foodIdList.push(1);
      //
      http
        .post("/order/getFoodList", {
          foodIdList,
        })
        .then((res) => {
          if (res.status === 200) {
            this.foodList = res.data;
          }
        })
        .catch((e) => console.log(e));
      this.onloaded = true;
    },
  },
};
</script>

<style>
.wrapper {
  height: 100%;
  width: 100%;
}
.cart {
  border: 2px solid tomato;
}
.foodContainer {
  height: 9rem;
  overflow: hidden;
  border: 1px solid gainsboro;
  display: flex;
  align-items: center;
}
.foodContainer span {
  position: relative;
  top: 45px;
}
.foodImage {
  margin: 1rem;
  width: 8rem;
  height: 6.8rem;
}
.text {
  padding: 4px;
}
h4 {
  position: relative;
  top: 1.7rem;
}
.desc {
  display: -webkit-box;
  font-weight: lighter;
  color: grey;
  font-size: 12px;
  padding: 5px;
  text-overflow: ellipsis;
  white-space: normal;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  line-height: 1.2;
  height: 3.6rem;
}
</style>
