<template>
  <div class="cart">
    <nav id="navbar-example2" class="navbar navbar-light bg-light px-3">
      <div>장바구니</div>
    </nav>
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
 
  height: 700px;
  width: 800px;
  left: 300px;
  position: relative;
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