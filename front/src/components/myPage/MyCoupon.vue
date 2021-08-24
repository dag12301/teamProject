<template>
<div class="row" style="overflow:auto; height:660px;">
  <div class="col coupon">
    <div class="card coupon-card">
        <div class="coupon-main">
          <div class="content">
            <h2>쿠폰 이름</h2>
            <h1>쿠폰 내용 <span>Coupon</span></h1>
            <p>~ 2021.08.21</p>
          </div>
          <div class="vertical"></div>
           <div class="copy-button">
          <button onclick="copyIt()" class="copybtn">종료</button>
        </div>
        </div>
    </div>
  </div>
  <div class="col coupon">
    <div class="card coupon-card">
        <div class="coupon-main">
          <div class="content">
            <h2>쿠폰 이름</h2>
            <h1>쿠폰 내용 <span>Coupon</span></h1>
            <p>~ 2021.08.21</p>
          </div>
          <div class="vertical"></div>
           <div class="copy-button">
          <button onclick="copyIt()" class="copybtn">종료</button>
        </div>
        </div>
    </div>
  </div>
  <div class="col coupon">
    <div class="card coupon-card">
        <div class="coupon-main">
          <div class="content">
            <h2>쿠폰 이름</h2>
            <h1>쿠폰 내용 <span>Coupon</span></h1>
            <p>~ 2021.08.21</p>
          </div>
          <div class="vertical"></div>
           <div class="copy-button">
          <button onclick="copyIt()" class="copybtn">종료</button>
        </div>
        </div>
    </div>
  </div>
  <div class="col coupon">
    <div class="card coupon-card">
        <div class="coupon-main">
          <div class="content">
            <h2>쿠폰 이름</h2>
            <h1>쿠폰 내용 <span>Coupon</span></h1>
            <p>~ 2021.08.21</p>
          </div>
          <div class="vertical"></div>
           <div class="copy-button">
          <button onclick="copyIt()" class="copybtn">종료</button>
        </div>
        </div>
    </div>
  </div>
</div>
</template>

<style scoped>
.coupon {
  display: flex; 
  flex-direction: row ; 
  justify-content: center;
  padding: 20px;
  height:200px;
}
.coupon-card {

    width: 400px;
    height: 180px;
    border-radius: 5px;
    box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.1);
    background-color: #fff;
    padding: 10px 10px;
    float: left;
    margin: 10px 10px;
    background-color:rgba(0, 0, 0, 0.5);
}
.coupon-main {
  height: 100%;

}

.coupon-main,
.copy-button {

    display: flex;
    justify-content: space-between;
    padding: 0 10px;
    align-items: center;
}
.coupon-card::after {
    position: absolute;
    content: "";
    height: 40px;
    right: -20px;
    border-radius: 40px;
    z-index: 1;
    top: 70px;
    background-color: white;
    width: 40px;
}

.coupon-card::before {
    position: absolute;
    content: "";
    height: 40px;
    left: -20px;
    border-radius: 40px;
    z-index: 1;
    top: 70px;
    background-color: white;
    width: 40px;
}

.vertical {
    border-left: 2px Dashed darkgray;
    height: 180px;
    position: absolute;
    left: 65%;
}

.content {
    width: 65%;
}

.content h1 {
    font-size: 35px;
    margin-left: -20px;
    color: #565656;
}

.content h1 span {
    font-size: 18px;
}
.content h2 {
    font-size: 18px;
    margin-left: -20px;
    color: #565656;
    text-transform: uppercase;
}

.content p {
    font-size: 16px;
    color: #696969;
    margin-left: -20px;
}

.copy-button {
    width: 30%;
}

.copy-button button {
    padding: 5px 20px;
    background-color: darkgray;
    color: gray;
    border: 1px solid transparent;
}
.title {
    font-size: 23px;
    font-weight: bold;
    text-align: left;
    margin-left: 90px;
    height: 50px;
    padding-top: 30px;
}
</style>