import { createRouter, createWebHistory } from "vue-router";

import store from "@/store/index";

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/viewBoard/TestPage"),
  },
  {
    path: "/view",
    name: "View",
    component: () => import("../views/viewBoard/View.vue"),
  },
  {
    path: "/cart",
    name: "Cart",
    component: () => import("../views/cart/Cart.vue"),
  },
  {
    path: "/food",
    name: "Food",
    component: () => import("../views/food/Food.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.afterEach(() => {
  // ${//these hooks do not get a next function and cannot affect the navigation}
  setTimeout(() => {
    store.commit("SET_SIDEBAR", false);
  }, 0);
  console.log("hello");
});

export default router;
