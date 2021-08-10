<template>
  <div class="sideBar">
    <!--사이드 바-->
    <div id="layoutSidenav">
      <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
          <div class="sb-sidenav-menu">
            <div class="nav">
              <!-- 사이드 바 메뉴 -->
              <div class="sb-sidenav-menu-heading">WhatFoo</div>
          
            
              <router-link class="nav-link" :to="{name: 'AkinatorPage'}" exact>아키네이처</router-link>

              <router-link class="nav-link" :to="{name: 'Cart'}" exact>장바구니/결제하기</router-link>

              <router-link class="nav-link" :to="{name: 'Food'}" exact>가게 찾기</router-link>

              <router-link class="nav-link" :to="{name: 'View'}" exact>View</router-link>

              <router-link class="nav-link" :to="{name: 'Home'}" exact>이벤트</router-link>


              <router-link class="nav-link" :to="{name: 'Login'}" exact>로그인</router-link>

              <router-link class="nav-link" :to="{name: 'Join'}" exact>회원가입</router-link>

              
              
              <a class="nav-link" href="/">
                고객센터
              </a>
            </div>
              </div>
              <div class="sb-sidenav-footer">
                <div class="small">Logged in as:</div>
                Start Bootstrap
          </div>
        </nav>
      </div>
    </div>
  </div>
</template>


<script>

</script>

<style>
@import "../../css/styles.css";

.sideBar{
  
  width: 20%;
  height: 95vh;
}

.sideBar #layoutSidenav{
  height: 95vh;
}

.sideBar #layoutSidenav #layoutSidenav_nav{

}

.sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion{
  
}

.sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion .sb-sidenav-menu{

}
.sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion .sb-sidenav-menu .nav{

}
</style>