<template>
  <div>
    <header class="py-3 mb-3 border-bottom">
      <div
        class="container-fluid d-grid gap-3 align-items-center"
        style="grid-template-columns: 1fr 2fr"
      >
        <!-- 네이게이션 바 앞부분 -->
        <div
          href="#"
          class="
            d-flex
            align-items-center
            col-lg-4
            mb-2 mb-lg-0
            link-dark
            text-decoration-none
          "
          aria-expanded="false"
        >
          <!-- 로고 -->
          <img
            src="../../assets/logo.png"
            width="50"
            height="50"
            @click="toggleSideBar()"
          />
          <a class="navbar-brand ps-3" href="/">WHAT POO</a>
        </div>
        <!-- 검색창 -->
        <div class="d-flex align-items-center">
          <form class="w-100 me-3">
            <input
              type="search"
              class="form-control"
              placeholder="수원시 팔달구"
              aria-label="Search"
              readonly
            />
          </form>

          <div class="flex-shrink-0 dropdown">
            <a
              href="#"
              class="d-block link-dark text-decoration-none dropdown-toggle"
              id="dropdownUser2"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <img
                src="https://github.com/mdo.png"
                alt="mdo"
                width="32"
                height="32"
                class="rounded-circle"
              />
            </a>
            <ul
              class="dropdown-menu text-small shadow"
              aria-labelledby="dropdownUser2"
            >
              <li><a class="dropdown-item" href="#">New project...</a></li>
              <li><a class="dropdown-item" href="#">Settings</a></li>
              <li><a class="dropdown-item" href="#">Profile</a></li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#">Sign out</a></li>
            </ul>
          </div>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      logoURL: require("../../assets/logo.png"),
    };
  },
  methods: {
    ...mapMutations({
      toggleSideBar: "main/toggleSideBar",
    }),
  },
};
</script>

<style></style>
