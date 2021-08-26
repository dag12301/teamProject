<template>
  <div>
    <div class="wrapper m-4 container-fluid p-2">
      <div class="cart">
        <h1 class="m-2">장바구니</h1>
        <hr />
        <div class="cartOrder">
          <div class="orderText">
            <p>배달정보</p>
          </div>
          <div class="orderAddress">
            <table>
              <tr>
                <td style="width: 25%; text-align: center">
                  <span>주소</span>
                </td>
                <td style="padding-left: 10px">
                  <span>인천 미추홀구 계양동 122-222</span>
                  <input
                    type="text"
                    placeholder="(필수) 상세주소 입력"
                    style="width: 100%"
                  />
                </td>
              </tr>
            </table>
          </div>
        </div>
        <div class="orderText">
          <p>주문내역</p>
        </div>
        <!-- 로딩이 안됐을땐, -->
        <div v-if="!onloaded">
          <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>
        <div v-else>
          <!-- 장바구니에 담지 않았을 때 -->
          <div v-if="!foodList || foodList.length == 0">
            <div class="foodContainer d-block m-4 emptyContainer">
              <span> 장바구니가 비어있습니다! </span>
            </div>
          </div>
          <!-- 장바구니에 무엇인가를 담아뒀을 때 -->
          <div v-else-if="foodList.length > 0">
            <div v-for="(food, index) in foodList" :key="index">
              <div class="foodContainer d-block">
                <div class="row">
                  <img :src="food.fileUrl" class="foodImage col-4" />
                  <div class="text col-8">
                    <div class="row">
                      <div>
                        <h4 class="d-inline m-3">
                          {{ food.name }}
                        </h4>
                        <span
                          class="badge bg-success d-inline"
                          v-if="food.status == 1"
                          >판매중</span
                        >
                        <span
                          class="badge bg-secondary d-inline"
                          v-else-if="food.status == 2"
                          >품절</span
                        >
                        <span
                          class="badge bg-danger d-inline"
                          v-else-if="food.status == 3"
                          >판매중지</span
                        >
                      </div>
                    </div>
                    <div class="row">
                      <div>
                        <span>{{ food.price }} 원</span>
                      </div>
                      <nav aria-label="Page navigation example">
                        <ul class="pagination">
                          <li class="page-item">
                            <a
                              class="page-link"
                              @click="decreaseQuantity(food.foodId)"
                              >-</a
                            >
                          </li>
                          <li class="page-item">
                            <a class="page-link">{{
                              foodQuantity(food.foodId)
                            }}</a>
                          </li>

                          <li class="page-item">
                            <a
                              class="page-link"
                              @click="increaseQuantity(food.foodId)"
                              >+</a
                            >
                          </li>
                          <div
                            class="btn btn-primary"
                            @click="removeFood(food.foodId)"
                          >
                            삭제하기
                          </div>
                        </ul>
                      </nav>
                      <div>
                        총액 : {{ totalPrice(food.foodId, food.price) }} 원
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="orderText" style="margin-top: 30px">
            <p>할인 쿠폰</p>
          </div>
          <div class="couponWrapper d-block">
            <!-- 사용할 수 있는 쿠폰이 있으면 불러오기 -->
            <!-- 사용기한, 체크박스로 체크하면 사용 -->
            <div>쿠폰정보가 없습니다</div>
          </div>
          <!-- 결제수단 선택 -->
          <div class="cartOrder">
            <div class="orderText">
              <p>결제수단 선택</p>
            </div>
            <div class="orderPay">
              <div>
                <span
                  style="font-weight: 550; font-size: 18px; margin-top: 10px"
                  >WAHT POO에서 결제</span
                ><span style="font-size: 14px; color: lightgray"
                  >웹에서 미리 결제</span
                ><br />
                <div class="payKakao">
                  <img
                    src="../../assets/payment_icon_yellow_large.png"
                    width="140"
                  />
                </div>
              </div>
              <div>
                <span style="font-weight: 550; font-size: 18px">현장 결제</span
                ><span style="font-size: 14px; color: lightgray"
                  >음식받고 직접 결제</span
                >
                <div style="margin-left: 33%">
                  <div style="margin-top: 10px; width: 50%">
                    <div class="payLeft">
                      <div class="Pay leftPay" style="line-height: 60px">
                        <label style="vertical-align: middle">신용카드</label>
                      </div>
                    </div>
                    <div class="payRight">
                      <div class="Pay rightPay" style="line-height: 60px">
                        <label style="verticla-align: middle">현금</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="calculatorWrapper d-block" style="padding-top: 0px">
            <div class="orderText">
              <p>주문내역</p>
            </div>
            <div class="orderList">
              <div v-for="(food, index) in foodList" :key="index">
                <div
                  class="col-8"
                  style="float: left; background-color: orange"
                >
                  {{ food.name }} X {{ foodQuantity(food.foodId) }} 개
                </div>
                <div
                  class="col-4"
                  style="float: right; background-color: yellow"
                >
                  {{ totalPrice(food.foodId, food.price) }} 원
                </div>
              </div>
              <hr />
              <div class="col-8" style="float: left">
                <span>총 결제 금액</span>
              </div>
              <div class="col-4" style="float: right">
                <span>총액</span>
              </div>
            </div>
            <!-- orderList 에 대한 총액정리 쿠폰 적용 후. -->
          </div>
          <div class="commandOrderWrapper" v-if="foodList.length > 0">
            <div class="row">
              <div class="col commandOrder" @click="putOrder">주문하기</div>
              <div class="col commandCancel" @click="clearOrder">취소하기</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import http from "@/api/http";
export default {
  data() {
    return {
      foodList: [],
      onloaded: false,
      orderList: new Map(),
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
    ...mapMutations(["delCart", "clearCart"]),
    getFoodList(foodIdSet) {
      const foodIdList = Array.from(foodIdSet);
      http
        .post("/order/getFoodList", {
          foodIdList,
        })
        .then((res) => {
          if (res.status === 200) {
            this.foodList = res.data;
            let orderList = new Map();
            for (let food of res.data) {
              orderList.set(food.foodId, food.quantity);
            }
            this.orderList = orderList;
          }
        })
        .catch((e) => console.log(e));
      this.onloaded = true;
    },
    increaseQuantity(foodId) {
      let q = this.orderList.get(foodId);
      this.orderList.set(foodId, ++q);
    },
    decreaseQuantity(foodId) {
      let q = this.orderList.get(foodId);
      if (q == 0) {
        return;
      }
      this.orderList.set(foodId, --q);
    },
    test() {
      console.log(this.orderList);
    },
    foodQuantity(foodId) {
      return this.orderList.get(foodId);
    },
    totalPrice(foodId, price) {
      return this.orderList.get(foodId) * price;
    },
    putOrder() {
      // 계산 진행, 만약 회원이면 정보로 하고, 로그인 아니면 필요정보입력
    },
    clearOrder() {
      let willClear = confirm("정말 장바구니를 비우시겠습니까?");
      if (willClear === true) {
        this.clearCart();
        this.foodList = [];
        this.orderList.clear();
      }
    },
    removeFood(foodId) {
      let filteredFoodList = this.foodList.filter(
        (food) => food.foodId !== foodId
      );
      this.foodList = filteredFoodList;
      if (this.orderList.has(foodId)) {
        this.orderList.delete(foodId);
      }
      this.delCart(foodId);
    },
  },
};
</script>

<style scoped>
.orderText {
  background-color: lightslategray;
  padding: 10px;
}
.orderText p {
  margin: 0px;
}
.orderAddress {
  padding: 10px;
  text-align: left;
}
.orderAddress span {
  font-weight: 550;
}

.Pay {
  height: 60px;
  border: 1px solid lightgray;
}
.payLeft {
  float: left;
  width: 49%;
}
.payRight {
  float: right;
  width: 49%;
}
.payKakao {
  margin-top: 10px;
  margin-bottom: 30px;
}

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
.calculatorWrapper {
  overflow: hidden;
  border: 1px solid gainsboro;
  display: flex;
  align-items: center;
  padding: 50px;
}
.emptyContainer {
  padding: 50px;
}
.couponWrapper {
  overflow: hidden;
  border: 1px solid gainsboro;
  display: flex;
  align-items: center;
  padding: 50px;
}
.foodContainer span {
  position: relative;
}
.foodImage {
  margin: 1rem;
  width: 8rem;
  height: 6.8rem;
}
.text {
  padding: 4px;
}
.desc {
  display: -webkit-box;
  font-weight: lighter;
  color: grey;
  font-size: 12px;
  padding: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  line-height: 1.2;
  height: 3.6rem;
}
.commandOrderWrapper {
  height: 8rem;
  padding: 2rem;
}
.commandOrder {
  height: 50px;
  cursor: pointer;
  border: 1px solid gainsboro;
}
.commandCancel {
  cursor: pointer;
  border: 1px solid gainsboro;
}
</style>
