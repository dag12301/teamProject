<template>
  <div class="sidebar" :style="{ width: sidebarWidth }">
    <span v-if="collapsed">
      <h1>
        <div>와</div>
        <div>푸</div>
      </h1>
      <span @click="toggleSidebar">
        <i class="fas fa-user-circle"></i>
      </span>
    </span>

    <span v-else>
      <h2>WAPOO</h2>
      <!-- 펼쳤을때 -->
      <Profile class="profile" />
    </span>

    <SidebarLink to="/" icon="fas fa-home">Home</SidebarLink>
    <SidebarLink to="/faq" icon="fas fa-question-circle">[경태]FAQ</SidebarLink>
    <SidebarLink to="/qna" icon="fas fa-question">[경태]QNA</SidebarLink>
    <SidebarLink to="/food" icon="fas fa-question">소희1</SidebarLink>
    <SidebarLink to="/foodlist" icon="fas fa-question">소희2</SidebarLink>
    <SidebarLink to="/test" icon="fas fa-image">Test</SidebarLink>

    <span class="burger" @click="toggleSidebar">
      <BurgerButton />
    </span>
  </div>
</template>

<script>
import { collapsed, toggleSidebar, sidebarWidth } from "./state";
import SidebarLink from "./SidebarLink.vue";
import Profile from "./Profile.vue";
import BurgerButton from "./Burger.vue";

export default {
  props: {},
  components: { SidebarLink, BurgerButton, Profile },
  setup() {
    return { collapsed, toggleSidebar, sidebarWidth };
  },
};
</script>

<style>
/* 기본 테마 정의 - 참고: https://blog.thereis.xyz/136 */
:root {
  --sidebar-bg-color: #2f855a;
  --sidebar-item-hover: #38a169;
  --sidebar-item-active: #276749;
}
</style>

<style scoped>
.sidebar {
  color: white;
  background-color: var(--sidebar-bg-color);

  float: left;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  bottom: 0;
  padding: 0.5em;

  transition: 0.3s ease;

  display: flex;
  flex-direction: column;
}
.profile {
  transition: 0.5s ease;
}

.burger {
  position: absolute;
  bottom: 0;
  right: 0.2em;
  padding: 0.75em;

  color: gba(255, 255, 255);
}
</style>
