<template>
  <div class="upper">
    <div class="nav-bar">
      <!-- 네비게이션 -->
      <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <div class="col-md-3">
          <!-- Navbar Brand-->
          <a class="navbar-brand ps-3" href="/">WHAT POO</a>
          <!-- Sidebar Toggle 햄버거바-->
          <button
            class="btn btn-link order-1 order-lg-0 me-4 me-lg-0"
            @click="toggleSideBar"
            href="#!"
          >
            ☰
          </button>
        </div>

        <!-- Navbar Search-->
        <div class="col-md-6">
          <div class="input-group input-group-sm w-50 m-5 col-3">
            <span class="input-group-text" id="inputGroup-sizing-sm"
              ><svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-compass-fill"
                viewBox="0 0 16 16"
              >
                <path
                  d="M15.5 8.516a7.5 7.5 0 1 1-9.462-7.24A1 1 0 0 1 7 0h2a1 1 0 0 1 .962 1.276 7.503 7.503 0 0 1 5.538 7.24zm-3.61-3.905L6.94 7.439 4.11 12.39l4.95-2.828 2.828-4.95z"
                /></svg
            ></span>
            <input
              type="text"
              class="form-control"
              aria-describedby="inputGroup-sizing-sm"
              value="     중구 을왕동 을왕리해수욕장"
            />
          </div>
        </div>

        <form
          class="
            d-none d-md-inline-block
            form-inline
            ms-auto
            me-5 me-md-3
            my-0 my-md-0
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
        <a
          href="#!"
          v-if="!this.$store.state.isAuthenticated"
          @click="SET_MODAL_LOGIN(true)"
          >로그인</a
        >
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
              <li>
                <a
                  class="dropdown-item"
                  href="#!"
                  v-if="!this.$store.state.isAuthenticated"
                  @click="SET_MODAL_LOGIN(true)"
                  >로그인</a
                >
              </li>
              <li><a class="dropdown-item" href="#!">설정</a></li>
              <li>
                <hr
                  class="dropdown-divider"
                  v-if="this.$store.state.isAuthenticated"
                />
              </li>
              <li>
                <a
                  class="dropdown-item"
                  href="#!"
                  v-if="this.$store.state.isAuthenticated"
                  >로그아웃</a
                >
              </li>
            </ul>
          </li>
        </ul>
      </nav>
      <!-- 모달 -->
      <LoginModal v-if="loginModal" />
      <RegisterModal v-if="registerModal" />
    </div>
  </div>
</template>

<script>
import LoginModal from "@/components/modal/Login";
import RegisterModal from "@/components/modal/Register";
import { mapState, mapMutations } from "vuex";
export default {
  components: {
    LoginModal,
    RegisterModal,
  },
  computed: {
    ...mapState(["loginModal", "registerModal"]),
  },
  methods: {
    ...mapMutations({
      toggleSideBar: "toggle/toggleSideBar",
      SET_MODAL_LOGIN: "SET_MODAL_LOGIN",
      SET_MODAL_REGISTER: "SET_MODAL_REGISTER",
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
/* input  */
.input-group {
}
input::placeholder {
  font-style: italic;
  font-weight: 700;
}
</style>
