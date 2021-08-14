import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Home",
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
  {
      path: "/serviceCenter",
      name: "ServiceCenter",
      component: () => import("../views/serviceCenter/ServiceCenter.vue"),
      children: [
          {
            path: "notice",
            name: "Notice",
            component: () => import("../components/service/Notice.vue"),

          },
          {
            path: "qna",
            name: "Q&A",
            component: () => import("../components/service/Q&A.vue"),

          },
          {
            path: "fqa",
            name: "FQA",
            component: () => import("../components/service/FQA.vue"),

          }
        ] 
      
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
