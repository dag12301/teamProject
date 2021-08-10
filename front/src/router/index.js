import { createRouter, createWebHashHistory } from "vue-router";
import store from "@/store";
const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/Home.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("../views/Register.vue"),
  },
  {
    path: "/logout",
    name: "Logout",
    component: () => import("../views/Logout.vue"),
    meta: { authRequired: true }, // 인증이 필요한 페이지
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: () => import("../views/MyPage.vue"),
    meta: { authRequired: true }, // 인증이 필요한 페이지
  },
  {
    path: "/test",
    name: "Test",
    component: () => import("../views/Test.vue"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
  base: process.env.BASE_URL,
});

router.beforeEach((to, from, next) => {
  // const publicPages = ["Login", "Register"];
  // const authRequired = !publicPages.includes(to.name);
  // const loggedIn = localStorage.getItem("user");

  // if (authRequired && !loggedIn) {
  //   router.push({ name: "Login", query: { to: to.path } });
  // } else {
  //   next();
  // }
  const isAuthenticated = store.getters["isAuthenticated"];
  if (
    to.matched.some(function (routeInfo) {
      return routeInfo.meta.authRequired;
    })
  ) {
    // 인증이 필요한 페이지라면 = meta '옵션'의 authRequired 가 true 라면
    if (isAuthenticated) {
      // 인증 상태라면
      next();
    } else {
      alert("로그인이 필요한 페이지입니다.");
      router.push({ name: "Login", query: { to: to.path } });
      // 로그인 화면으로 보낸다.
    }
  } else {
    next(); // 로그인이 필요하지 않은 페이지로 가고자 한다면 가게한다.
  }
});

export default router;
