<template>
    <h2 class="coupon-h2" style="margin-top: 50px;">쿠폰 추가 페이지<h5>(관리자 & 판매자 전용)</h5></h2>
    <form action="" name="CouponAdd" v-on:submit.prevent="CouponForm">
        <table>
            <tr>
                <td class="td1">쿠폰번호:</td>
                <td class="td2"><input type="text" class="couponadd-input" name="couponNumber" v-model="couponNumber" id="couponNumber"/></td>
            </tr>
            <tr>
                <td class="td1">쿠폰명:</td>
                <td class="td2"><input type="text" class="couponadd-input" name="couponName" v-model="couponName" id="couponName"/></td>
            </tr>
            <tr>
                <td class="td1">사용종료일:</td>
                <td class="td2"><input type="text" class="couponadd-input" name="couponEnd" v-model="couponEnd" id="couponEnd"/></td>
            </tr>
            <tr>
                <td class="td1">할인금액:</td>
                <td class="td2"><input type="number" class="couponadd-input" name="couponPrice" v-model="couponPrice" id="couponPrice"/></td>
            </tr>
            <tr>
                <td class="td1">할인율:</td>
                <td class="td2"><input type="number" class="couponadd-input" name="discountRate" v-model="discountRate" id="discountRate"/></td>
            </tr>

        
        </table>
        <input type="submit" class="couponadd-button" value="추가" @click="couponbutton"/>
        
    </form>
</template>

<script>
import { normal, error, success } from "@/api/notification";
import axios from "axios";

export default {
  data:function(){
    return{
       couponNumber : this.couponNumber,
       couponName : this.couponName,
       couponEnd : this.couponEnd,
       couponPrice : this.couponPrice,
       discountRate : this.discountRate
    }
  },
  methods:{
    CouponForm:function(){
      console.log("확인");
      console.log(this.couponNumber, this.couponName, this.couponEnd, this.couponPrice, this.discountRate);
    
    
      var data = {
        couponNumber : this.couponNumber,
        couponName : this.couponName,
        couponEnd : this.couponEnd,
        couponPrice : this.couponPrice,
        discountRate : this.discountRate
      }
      
      axios.post('http://localhost:8083/coupon/couponinsert', data)
        .then((data) => {
          console.log(data);
          
           success("데이터 로딩 완료!", this);
        })
        
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
 
</script>


<style scoped>
table{
    margin-left:25%;
}
.td1 {
    padding-top: 20px;
    width: 100px;
}
.couponadd-input {
    margin-top: 20px;
    width: 250px;
    height: 40px;
}
.couponadd-button {
    margin-top: 40px;
}
</style>
