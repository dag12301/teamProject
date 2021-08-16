<template>
<div id="event-wrapper">
    <div id="event-content">
        <div class="event-main-box">
            <section class="event-tex-box">
                <h2 class="event-h2">이벤트</h2>
                <ul class="event-ul">
                    <li class="event-li"><a href="" class="event-a">전체</a></li>
                    <li class="event-li"><a href="" class="event-a">진행중</a></li>
                    <li class="event-li"><a href="" class="event-a">종료</a></li>
                </ul>
            </section>
        </div>
        <div class="card">
            <div class="card-top">
                <div class="card-img">
                    <img src="../../assets/logo.png" alt=""/>
                </div>
                <div class="content">
                    <h2>가게이름</h2>
                    <h1>할인가ex)10%</h1>
                    <p>기한 ~ 2021-08-20</p>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<style scoped>
    #event-wrapper {
        display: flex; justify-content: center; align-items: center;
    }
    #event-content {
        width: 600px;
    }
    .event-main-box {display: flex; justify-content: center; align-items: center; width: 100%;}
    .event-li {list-style-type: none; float: left; margin-left: 20px;}
    .event-a {text-decoration: none; font-weight: bold; color: #b8b8b8;}
    .event-tex-box {width: 500px; margin-left: 50px;}
    .event-h2 {float: left; font-weight: bold; margin-top: 5px;}
    .event-ul {float: right; line-height: 30px; margin-top: 5px;}

/* ------------------------------------------------------------------------------------------------------- */

.card {
  width: 400px;
  height: 180px;
  border-radius: 10px;
  box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.2);
  background-color: #fff;
  padding: 10px 10px;
  position: relative;
  overflow: hidden;
}

.card::after {
  position: absolute;
  content: "";
  height: 40px;
  right: -20px;
  border-radius: 40px;
  z-index: 1;
  top: 70px;
  background-color: #dc143c;
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
  background-color: #dc143c;
  width: 40px;
}

img {
    width: 100px;
    height: 100px;
    float: left;
}

.content h1 {
  font-size: 35px;
  margin-left: -20px;
  color: #565656;
}
.content {
    float: right;
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

</style>