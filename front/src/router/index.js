import { createRouter, createWebHistory } from "vue-router";
const routes = [
  {
    path: "/test",
    name: "Test",
    component: () => import("../views/Test.vue"),
  },
  {
    path: "/",
    name: "Home",
    component: () => import("../views/Home.vue"),
  },
  {
    path: "/cart",
    name: "Cart",
    component: () => import("../views/cart/Cart.vue"),
  },
  {
    path: "/food",
    name: "Food",
    component: () => import("../views/food/FoodList.vue"),
  },
  

  {
    path: "/test",
    name: "Test",
    component: () => import("../views/Test.vue"),
  },
  {
    path: "/serviceCenter",
    name: "ServiceCenter",
    component: () => import("../views/serviceCenter/ServiceCenter.vue"),
    children: 
    [
      {
        path: "/notice",
        name: "Notice",
        component: () => import("../components/service/Notice.vue"),

      },
      {
        path: "/qna",
        name: "Q&A",
        component: () => import("../components/service/Q&A.vue"),

      },
      {
        path: "/fqa",
        name: "FQA",
        component: () => import("../components/service/FQA.vue"),
      },
      {
        path: "/boardList",
        name: "BoardList",
        component: () => import("../components/service/BoardList.vue")
      },
      {
        path: "/writeForm",
        name: "WriteForm",
        component: () => import("../components/service/WriteForm.vue")
      }
    ] 
      
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.afterEach(() => {
  // ${//these hooks do not get a next function and cannot affect the navigation}
  setTimeout(() => {
    //
  }, 0);
  console.log("hello");
});

export default router;
