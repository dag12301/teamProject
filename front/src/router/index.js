import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/Home.vue"),
  },
  {
    path: "/about",
    name: "About",

    component: () => import("../views/About.vue"),
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

export default router;
