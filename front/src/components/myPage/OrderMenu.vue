<template>
<div class="container" style="width: 800px; border-style: solid; border-width: 1px 1px 0px 1px;">
  
  <div class="row" style="height: 130px; cursor: pointer;"  v-for="(orderList, index) in orderLists" :key="index" @click="changePage(orderList.orderId)">
    <div class="col-4 orderMenu-img" >
      <router-link to="/food">
        <img
          class="shopImg"
          :src="orderList.image"
          :alt='orderList.orgName'
        />
      </router-link>
    </div>
    <div class="col-3 orderMenu-name">
      <h4>{{orderList.storeName}}</h4>
    </div>
    <div class="col-5 orderMenu-list">
      <div class="myMenu">
        <h5>
          {{orderList.foodName}} {{orderList.quantity}}개 <br />
          
        </h5>
      </div>
      <div class="time">
        {{orderList.orderDate}}
        <button
          type="button"
          @Click="orderDel()"
          class="btn btn-danger"
          style="font-size: 13px"
        >
          삭제
        </button>
      </div>
    </div>


  </div>
</div>
</template>

<script>
import http from "@/api/http";

export default {
  data () {
    return {
      phone: '',
      orderLists:[]
    }
  },
  methods: {
    orderDel() {
      alert("삭제되었습니다!");
    },
    //주문 상세 페이지
    changePage(orderId) {
      this.$router.push({name: 'OrderInfo', query: {orderId: orderId}})
    },
    orderList() {
      http.post("/profile/getOrder", {phone: this.phone })
      .then(res => {
        console.log(res.data)
        this.orderLists = res.data
        
      })
      .catch(err => {
        console.log(err)
      })


    },
  },
  mounted() {
    this.orderList()
  }
};
</script>

<style>
.orderMenu-name {
  border-bottom: 1px solid black;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.orderMenu-img {
  border-bottom: 1px solid black;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.orderMenu-list {
  border-bottom: 1px solid black;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: stretch;
}
.myMenu {
  padding-top: 10px;
}
.time {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-end;
  padding-bottom: 5px;
}
.shopImg {
  width: 150px;
  height: 100px;
}
</style>
