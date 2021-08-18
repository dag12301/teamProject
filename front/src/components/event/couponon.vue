<template>
    <div class="card" style="background-color: lightblue;"  v-for="(coupononlist, index) in $store.state.couponon.coupons" :key="index">
        <div class="main">
          <div class="content">
            <h2>{{coupononlist.title}}</h2>
            <h1>10% <span>할인</span></h1>
            <p>~ {{coupononlist.date}}</p>
          </div>
          <div class="vertical"></div>
           <div class="copy-button">
          <button onclick="copyIt()" class="copybtn">저장</button>
        </div>
        </div>
      </div>
</template>

<style scoped>
    
    .card {
    width: 400px;
    height: 180px;
    border-radius: 5px;
    box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.2);
    background-color: #fff;
    padding: 10px 10px;
    float: left;
    margin: 10px 10px;
}
    .main {
    height: 100%;
}

.main,
.copy-button {
    display: flex;
    justify-content: space-between;
    padding: 0 10px;
    align-items: center;
}
.card::after {
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

.card::before {
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
    background-color: #dc143c;
    color: #fff;
    border: 1px solid transparent;
}
</style>