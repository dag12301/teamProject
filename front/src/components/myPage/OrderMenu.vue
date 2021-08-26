<template>
    <div class="row" style="height: 130px;">
      <div class="col-4 orderMenu-img">
        <router-link to="/food">
          <img class="shopImg" src="https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile7.uf.tistory.com%2Fimage%2F991A404A5E3BB843188D6B" alt="...">
        </router-link>
      </div>
      <div class="col-3 orderMenu-name">
        <h4>짜장면집</h4>
      </div>
      <div class="col-5 orderMenu-list">
        <div class="myMenu">
          <h5>짜장면 1개 <br /> 탕수육 1개 <br /> 짬뽕 1개</h5>
        </div>
        <div class="time">
          2021.08.17
          <button type="button" @Click="orderDel()" class="btn btn-danger" style="font-size:13px;">삭제</button>
        </div>
      </div>
    </div>

</template>

<script>

export default {
  methods: {
    orderDel() {
      alert('삭제되었습니다!')
    }
  }
}
</script>

<style>
.orderMenu-name{
  border-bottom: 1px solid black;
  display: flex; 
  flex-direction: row; 
  justify-content: center; 
  align-items: center;
}
.orderMenu-img{
  border-bottom: 1px solid black;
  display: flex; 
  flex-direction: row ; 
  justify-content: center;
  align-items: center;
}
.orderMenu-list{
  border-bottom: 1px solid black;
  display: flex; 
  flex-direction: column ; 
  justify-content: space-between;
  align-items: stretch;
}
.myMenu {
  padding-top: 10px;
}
.time {
  display: flex; 
  flex-direction: row ; 
  justify-content: space-between;
  align-items: flex-end;
  padding-bottom: 5px;
}
.shopImg {
  width:150px;
  height:100px;
}
</style>
