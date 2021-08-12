<template>
  <transition name="slide">
    <div id="sideBar" :class="{ menuDisplayed: sidebar }">
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

                <div class="sidenav-heading">WAPOO</div>

                <a class="nav-link" href="/view" @click="SET_AKINATOR(true)"
                  >아키네이터
                </a>

                <router-link class="nav-link" :to="{ name: 'Cart' }" exact
                  >장바구니/결제하기</router-link
                >

                <router-link class="nav-link" :to="{ name: 'Food' }" exact
                  >가게 찾기</router-link
                >

                <router-link class="nav-link" :to="{ name: 'View' }" exact
                  >View</router-link
                >

                <router-link class="nav-link" :to="{ name: 'Home' }" exact
                  >이벤트</router-link
                >

                <a
                  class="nav-link collapsed"
                  href="#"
                  data-bs-toggle="collapse"
                  data-bs-target="#collapseLayouts"
                  aria-expanded="false"
                  aria-controls="collapseLayouts"
                >
                  고객센터
                </a>
                <div
                  class="collapse"
                  id="collapseLayouts"
                  aria-labelledby="headingOne"
                  data-bs-parent="#sidenavAccordion"
                >
                  <nav class="sb-sidenav-menu-nested nav">
                    <router-link class="nav-link" :to="{ name: 'Home' }" exact
                      >공지사항</router-link
                    >
                  </nav>
                  <nav class="sb-sidenav-menu-nested nav">
                    <router-link class="nav-link" :to="{ name: 'Home' }" exact
                      >FAQ</router-link
                    >
                  </nav>
                  <nav class="sb-sidenav-menu-nested nav">
                    <router-link class="nav-link" :to="{ name: 'Home' }" exact
                      >Q&A</router-link
                    >
                  </nav>
                </div>
              </div>
            </div>
            <div class="footPoint">
              <div class="small">Logged in as:</div>
              Start Bootstrap
            </div>
          </nav>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  computed: {
    ...mapState(["akinator", "sidebar"]),
  },
  methods: {
    ...mapMutations(["SET_AKINATOR"]),
  },
  components: {},
};
</script>

<style scoped>
.menuDisplayed {
  display: none;
}

#sideBar {
  width: 17vw;
  height: 95vh;
  float: left;
  top: 6vh;
}

#sideBar #layoutSidenav #layoutSidenav_nav {
  position: fixed;
  top: 6vh;
  left: 0;
  height: 95vh;
  flex-basis: 225px;
  flex-shrink: 0;
  transition: transform 0.15s ease-in-out;
  z-index: 10;
}

#sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion {
  background-color: #f8f9fa;
  color: #212529;
  display: flex;
  flex-direction: column;
  height: 100%;
  flex-wrap: nowrap;
  flex-grow: 1;
  box-sizing: border-box;
}

#sideBar #layoutSidenav #layoutSidenav_nav #sidenavAccordion .sb-sidenav-menu {
  flex-grow: 1;
}
#layoutSidenav #layoutSidenav_nav #sidenavAccordion .sb-sidenav-menu .nav {
  display: flex;
  padding-left: 0;
  margin-bottom: 0;
  list-style: none;
  flex-direction: column;
  flex-wrap: nowrap;
}

#layoutSidenav
  #layoutSidenav_nav
  #sidenavAccordion
  .sb-sidenav-menu
  .nav
  .nav-link {
  display: flex;
  align-items: center;
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
  position: relative;
  color: #0d6efd;
}

.sidenav-heading {
  padding: 1.75rem 1rem 0.75rem;
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
  color: #adb5bd;
}

.footPoint {
  background-color: #e9ecef;
  padding: 0.75rem;
  flex-shrink: 0;
}
</style>
