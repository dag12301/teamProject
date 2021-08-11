<template>
  <div id="wrapper" :class="{ menuDisplayed: isOn }">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
      <ul class="sidebar-nav">
        <li>
          <router-link
            class="nav-link"
            :to="{ name: 'Home' }"
            @click="toggleAki"
            exact
            >아키네이터</router-link
          >
        </li>

        <li>
          <router-link class="nav-link" :to="{ name: 'Cart' }" exact
            >장바구니/결제하기</router-link
          >
        </li>

        <li>
          <router-link class="nav-link" :to="{ name: 'Food' }" exact
            >가게 찾기</router-link
          >
        </li>

        <li>
          <router-link class="nav-link" :to="{ name: 'View' }" exact
            >View</router-link
          >
        </li>

        <li>
          <router-link class="nav-link" :to="{ name: 'Home' }" exact
            >이벤트</router-link
          >
        </li>

        <li>
          <router-link class="nav-link" :to="{ name: 'Login' }" exact
            >로그인</router-link
          >
        </li>

        <li>
          <router-link class="nav-link" :to="{ name: 'Join' }" exact
            >회원가입</router-link
          >
        </li>
      </ul>
    </div>
  </div>
  <a href="#" class="btn" id="menu-toggle"
    ><span class="glyphicon glyphicon-menu-hamburger"></span
  ></a>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
export default {
  computed: {
    ...mapGetters({
      isOn: "toggle/pop",
    }),
  },
  methods: {
    ...mapMutations({
      toggleAki: "toggle/toggleAki",
    }),
  },
};
</script>

<style scoped>
/* Sidebar */
#sidebar-wrapper {
  z-index: 1;
  position: absolute;
  width: 0;
  height: 100%;
  overflow-y: hidden;
  background: #666666;
  opacity: 0.9;
  transition: all 0.5s;
  display: flex;
  align-items: center;
}

/* Main Content */
#page-content-wrapper {
  width: 100%;
  position: absolute;
  padding: 15px;
  transition: all 0.5s;
}

#menu-toggle {
  transition: all 0.3s;
  font-size: 2em;
}
/* Change the width of the sidebar to display it*/
#wrapper.menuDisplayed #sidebar-wrapper {
  width: 250px;
}

#wrapper.menuDisplayed #page-content-wrapper {
  padding-left: 250px;
}

/* Sidebar styling */
.sidebar-nav {
  padding: 0;
  list-style: none;
  transition: all 0.5s;
  width: 100%;
  text-align: center;
}

.sidebar-nav li {
  line-height: 40px;
  width: 100%;
  transition: all 0.3s;
  padding: 10px;
}

.sidebar-nav li a {
  display: block;
  text-decoration: none;
  color: #ddd;
}

.sidebar-nav li:hover {
  background: #846bab;
}
</style>
