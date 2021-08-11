<template>
  <div class="upper">
    <div class="nav-bar">
      <!-- 네비게이션 -->
      <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <!-- Navbar Brand-->
        <a class="navbar-brand ps-3" href="/">WHAT POO</a>
        <!-- Sidebar Toggle 햄버거바-->
        <button
          class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
          @click="toggleSideBar"
          href="#!"
        >
          aa<i class="fas fa-bars"></i>
        </button>
        <!-- Navbar Search-->
        <div class="input-group input-group-sm">
          <span class="input-group-text" id="inputGroup-sizing-sm">위치</span>
          <input
            type="text"
            class="form-control"
            aria-label="Sizing example input"
            aria-describedby="inputGroup-sizing-sm"
          />
        </div>
        <form
          class="
            d-none d-md-inline-block
            form-inline
            ms-auto
            me-0 me-md-3
            my-2 my-md-0
          "
        >
          <div class="input-group">
            <img
              class="profile-img"
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtDH8-Dn4rgOsfQT3s7qTkjH_-JlayacHGZQ&usqp=CAU"
              alt="p"
            />
          </div>
        </form>
        <!-- Navbar-->
        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              id="navbarDropdown"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              ><i class="fas fa-user fa-fw"></i
            ></a>
            <ul
              class="dropdown-menu dropdown-menu-end"
              aria-labelledby="navbarDropdown"
            >
              <li><a class="dropdown-item" href="#!">설정</a></li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#!">로그아웃</a></li>
            </ul>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  methods: {
    ...mapMutations({
      toggleSideBar: "toggle/toggleSideBar",
    }),
  },
};
</script>

<style scoped>
.upper {
  height: 5vh;

  left: 0;
  top: 0;
  width: 100%;
  margin-bottom: 5vh;
}
.upper .nav-bar {
  height: 6vh;
  width: 98vw;
  z-index: 10;
}

.upper .nav-bar .navbar {
  height: 6vh;
  width: 100vw;
  position: fixed;
  z-index: 10;
}

.input-group {
  border-radius: 20px;
}
.profile-img {
  width: 30px;
  height: 15px;
  object-fit: contain;
}
</style>
