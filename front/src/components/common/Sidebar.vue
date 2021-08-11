<template>
<<<<<<< HEAD
  <div class="sideBar">
    <!--사이드 바-->
    <div id="layoutSidenav">
      <div id="layoutSidenav_nav">
        <nav
          class="sb-sidenav accordion sb-sidenav-light"
          id="sidenavAccordion"
        >
          <div class="sb-sidenav-menu">
            <div class="nav">
              <!-- 사이드 바 메뉴 -->
              <div class="sb-sidenav-menu-heading">profile</div>
              <a class="nav-link" href="/"> 닉네임 </a>
              <a
                class="nav-link collapsed"
                href="#"
                data-bs-toggle="collapse"
                data-bs-target="#collapseLayouts"
                aria-expanded="false"
                aria-controls="collapseLayouts"
              >
                주문하기
              </a>
              <a
                class="nav-link collapsed"
                href="#"
                data-bs-toggle="collapse"
                data-bs-target="#collapsePages"
                aria-expanded="false"
                aria-controls="collapsePages"
              >
                이벤트
              </a>
              <a class="nav-link" href="/"> 아키네이터 </a>
              <a class="nav-link" href="$router.push({name: 'View'})">
                음식/가게
              </a>

              <router-link class="nav-link" :to="{ name: 'View' }" exact
                >메인메뉴</router-link
              >

              <router-link class="nav-link" :to="{ name: 'Home' }" exact
                >Home</router-link
              >

              <router-link class="nav-link" :to="{ name: 'About' }" exact
                >About</router-link
              >

              <router-link class="nav-link" :to="{ name: 'Login' }" exact
                >로그인</router-link
              >

              <router-link class="nav-link" :to="{ name: 'Join' }" exact
                >회원가입</router-link
              >

              <a class="nav-link" href="/"> 고객센터 </a>
            </div>
          </div>
          <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            Start Bootstrap
=======
  <div id="sideBar">
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
>>>>>>> 70e2a9ddbec3c2494902d29280771a5a99460194
          </div>
        </nav>
      </div>
    </div>
<<<<<<< HEAD
  </div>
</template>

<script></script>

<style>
@import "../../css/styles.css";
.sideBar {
  position: relative;
  width: 25%;
  height: 100%;
}
</style>
=======
    
  </div>
</template>


<script>

</script>

<style>
@import "../../css/styles.css";

#sideBar{
  
  width: 17vw;
  height: 95vh;
  float: left;
  top: 6vh;
}

#sideBar #layoutSidenav{
  

}

#sideBar #layoutSidenav #layoutSidenav_nav{
  position: fixed;
  top: 6vh;
  left: 0;
  height: 95vh;
  
}

.sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion{

}

.sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion .sb-sidenav-menu{
  
}
.sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion .sb-sidenav-menu .nav{

}
</style>
>>>>>>> 70e2a9ddbec3c2494902d29280771a5a99460194
