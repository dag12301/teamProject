<template>
  <div id="nav">
    <Nav></Nav>
    <Burger></Burger>
    <Sidebar></Sidebar>

    <div class="middle col-10">
      <router-view />
    </div>

    <Footer></Footer>
  </div>
</template>

<script>
import Nav from "./components/common/Nav";
import Footer from "./components/common/Footer";
import Sidebar from "./components/common/Sidebar";
import Burger from "./components/common/Burger";

export default {
  components: {
    Nav,
    Footer,
    Sidebar,
    Burger,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  position: relative;
  width: 97.5vw;
  height: 100vh;
}
#nav .middle {
  min-height: 90vh;
  margin-bottom: 3vh;
  left: 2vw;
  right: 2vw;
  margin: 0 auto;
}
</style>
