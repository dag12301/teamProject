<template>
<div class="row side-row">
  <router-link class="profilelink" to="/myProfile">
    <div class="item">
      내정보
    </div>
  </router-link>
</div> 
<div class="row side-row">
  <router-link class="profilelink" to="/myCoupon">
    <div class="item">
      쿠폰
    </div>
  </router-link>
</div> 
<div class="row side-row">
  <router-link class="profilelink" to="/orderMenu">
    <div class="item">
      주문내역
    </div>
  </router-link>
</div> 
<div class="row side-row">
  <router-link class="profilelink" to="/myReview">
    <div class="item">
      리뷰관리
    </div>
  </router-link>
</div> 
<div class="row side-row">
  <router-link class="profilelink" to="/question">
    <div class="item">
      질문
    </div>
  </router-link>
</div> 
<div class="row">
  <router-link class="profilelink" to="/edit">
    <div class="item">
      정보수정
    </div>
  </router-link>
</div>
</template>
<script>
export default {
}
</script>

<style>
.side-row {
  border-bottom: 1px solid black;
}
.item {
  padding:20px;
}
.profilelink:link,
.profilelink:visited,
.profilelink:hover {
  text-decoration:none;
  color: black;
}
.profilelink {
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
}
.profilelink {
  line-height: 42px;
  padding: 0;
  border: none;
}
.profilelink:hover {
  background: transparent;
  box-shadow:none;
  background: rgb(151, 151, 151);
}
.profilelink:before,
.profilelink:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  box-shadow:
   -1px -1px 5px 0px black,
   7px 7px 20px 0px #0003,
   4px 4px 5px 0px #0002;
  transition:400ms ease all;
}
.profilelink:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.profilelink:hover:before,
.profilelink:hover:after{
  width:100%;
  transition:800ms ease all;
}
</style>

