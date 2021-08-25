<template>
  <div class="cart">
    <nav id="navbar-example2" class="cartNav">
      <div>주문하기</div>
    </nav>
    <div class="cartOrder">
      <div class="orderText">
        <p>배달정보</p>
      </div>
      <div class="orderAddress">
        <table>
          <tr>
            <td style="width: 20%;"><span>주소</span></td>
            <td><span>인천 미추홀구 계양동 122-222</span>
        <input type="text" placeholder="(필수) 상세주소 입력" style="width: 100%"/></td>
          </tr>
        </table>
      </div>
    </div>
    <div class="cartOrder">
      <div class="orderText">
        <p>결제수단 선택</p>
      </div>
      <div class="orderPay">
        WATH POO에서 결제<br/>
        현장 결제
      </div>
    </div>
    <div class="cartOrder">
      <div class="orderText">
        <p>주문내역</p>
      </div>
      <div class="orderStore">
        <p>맘스터치(가게이름)</p>
      </div>
      <div class="orderList">
        <div class="listLeft">피자 x 1개</div>
        <div class="listRight">가격</div>
      </div>
      <div class="cartOrder">
      <div class="orderText">
        <p>할인 쿠폰</p>
      </div>
      <div class="orderCoupon">
        쿠폰 
      </div>
    </div>
      <hr style="width:100%; margin: 0px;"/>
      <div class="orderList">
        <div class="listLeft orderLeft">결재금액</div>
        <div class="listRight orderRight">10000원</div>
      </div>
    </div>


    <div class="address">
      <span>주소: 인천 미추홀구 계양동 122-222</span><span style="font-weight: 800;">  (으)로 배달</span>
      <button style="position: relative; left: 100px">수정</button>
    </div>
    <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" class="scrollspy-example" tabindex="0">
      <ul class="cart-list">
        <!-- 음식 리스트 -->
        <li class="chart-item">
          <div class="container">
            <div class="row">
              <div class="col-6 col-sm-4">피자</div>
              <div class="col-6 col-sm-6"></div>
              <div class="col-6 col-sm-2">10000원</div>

              <!-- Force next columns to break to new line at md breakpoint and up -->
              <div class="w-100 d-none d-md-block" style="padding: 10px 0;"> </div>

              <div class="col-6 col-sm-4">
                <button type="button" class="btn-close" aria-label="Close"></button>
              </div>
              <div class="col-6 col-sm-6"></div>
              <div class="col-6 col-sm-2 numcount">
                <button type="button" class="numbox">-</button>
                <div class="numbox">2</div>
                <button type="button" class="numbox">+</button>
              </div>
            </div>
          </div>
        </li>
        <!-- 끝 -->
        <li class="chart-item">
          <div class="container"  >
            <div class="row">
              <div class="col-6 col-sm-4">피자</div>
              <div class="col-6 col-sm-6"></div>
              <div class="col-6 col-sm-2">10000원</div>

              <!-- Force next columns to break to new line at md breakpoint and up -->
              <div class="w-100 d-none d-md-block" style="padding: 10px 0;"> </div>

              <div class="col-6 col-sm-4">
                <button type="button" class="btn-close" aria-label="Close"></button>
              </div>
              <div class="col-6 col-sm-6"></div>
              <div class="col-6 col-sm-2 numcount">
                <button type="button" class="numbox">-</button>
                <div class="numbox">2</div>
                <button type="button" class="numbox">+</button>
              </div>
            </div>
          </div>
        </li>
        
        
      </ul>
    </div>
    <!-- 메뉴 푸가-->
    <div style="color: #006cb7; font-weight: 800; margin-bottom: 10px;">+ 메뉴추가</div>
    <nav id="navbar-example2" class="navbar navbar-light bg-light px-3">
      <div>할인쿠폰</div>
    </nav>
    <!-- 쿠폰-->
    <div style="border:2px solid red; margin: 10px 0">
      <div>쿠폰</div>
      <input type="text" readonly value="쿠폰번호" />
      <!-- Example single primary button -->
    <div class="btn-group">
      <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        Action
      </button>
      <ul class="dropdown-menu">
        <li><a class="dropdown-item" href="#">첫 가입 할인</a></li>
        <li><a class="dropdown-item" href="#">가게 할인</a></li>
        <li><a class="dropdown-item" href="#">1등 쿠폰</a></li>
        
      </ul>
    </div>
    </div>
    <!-- 주문 및 결재-->
    <div class="container">
  <div class="row" style="border-bottom: 1px solid red; margin-bottom: 10px; padding-bottom: 10px">
    <div class="col">
      주문금액
    </div>
    <div class="col">
      10000원
    </div>
  </div>
  <!-- 쿠폰 사용 태그-->
  <div class="row" style="border-bottom: 1px solid red;
                           margin-bottom: 10px;
                            padding-bottom: 10px;
                             color: red;
                              font-weight: 800;">
    <div class="col">
      쿠폰
    </div>
    <div class="col">
      -5000원
    </div>
  </div>

  <div class="row" style="color: #333; font-weight: 800;">
    <div class="col">
      총 결재금액
    </div>
    <div class="col">
      5000원
    </div>
    
  </div>
</div>
    
    <button type="button" class="btn btn-outline-primary">결재하기</button>


  </div>
</template>

<script>
export default {

}
</script>

<style>
.cart{
  width: 100%;
  left: 10px;
  position: relative;
}
.cartNav {
  background-color: #333;
  color: white;
  font-size: 20px;
  font-weight: 550;
}

.cartOrder {
  text-align: left;
}
.orderText {
  background-color: lightslategray;
  padding: 10px;
}
.orderText p {
  margin: 0px;
}
.orderStore{
  padding: 10px;
  background-color: lightgray;
}
.orderStore p {
  margin: 0px;
}
.orderList {
  padding: 10px;
  background-color: lightyellow;
  
}
.orderList p {
  margin: 0px;
}

.listLeft {
  float: left;
  width: 80%;
}
.listRight {
  float: right;
  width: 20%;
}

.orderLeft {
  font-size: 20px;
  font-weight: 550;
}
.orderRight {
  font-size: 20px;
  color: lightslategray;
}


.orderAddress {
  padding: 10px;
  background-color: #FAFAFA;
}
.orderAddress span {
  font-weight: 550;
}





.address{
  text-align: center;
  margin: 30px 0;
  border-bottom: 1px solid red;
  padding-bottom: 10px;
}
.navbar-brand{
  text-align: center;
}
.cart-list{
  display: block;
  list-style: none;
  
}
.cart-list .chart-item{
  border-bottom: 1px solid red;
  margin: 5px 0;
  padding: 20px;
  text-align: left;
  
  color: #333;
}
.shop-link{
  
}
.numcount{
  display: flex; 
  flex-wrap: wrap;
  float: right;
  
}
.numcount .numbox{
  height: 30px;
  width: 30px;
  text-align: center;
  margin:  auto 0 ;
  
}


</style>