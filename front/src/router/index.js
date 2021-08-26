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
    path: "/shops",
    name: "Shops",
    component: () => import("../views/food/Food.vue"),
  },
  {
    path: "/foodlist",
    name: "FoodList",
    component: () => import("../views/food/FoodList.vue"),
  },
  {
    path: "/shopDetail",
    name: "ShopDetail",
    component: () => import("../views/food/FoodDetail.vue"),
    props: true,
  },
  {
    path: "/event",
    name: "Event",
    component: () => import("../views/event/Event.vue"),

    children: [
      {
        path: "/all",
        name: "coupon-all",
        component: () => import("../components/event/coupon.vue"),
      },
      {
        path: "/on",
        name: "coupon-on",
        component: () => import("../components/event/couponon.vue"),
      },
      {
        path: "/end",
        name: "coupon-end",
        component: () => import("../components/event/couponend.vue"),
      },
    ],
  },
  {
    path: "/eventadd",
    name: "EventAdd",
    component: () => import("../views/event/EventAdd.vue"),
  },
  {
    path: "/serviceCenter",
    name: "ServiceCenter",
    component: () => import("../views/serviceCenter/ServiceCenter.vue"),
    children: [
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
        path: "/writeForm",
        name: "WriteForm",
        component: () => import("../components/service/WriteForm.vue"),
      },
      {
        path: "/boardList",
        name: "BoardList",
        component: () => import("../components/service/BoardList.vue"),
      },
    ],
  },
  {
    path: "/myPage",
    name: "MyPage",
    component: () => import("../views/myPage/MyPage"),
    children: [
      {
        path: "/myProfile",
        name: "MyProfile",
        component: () => import("../components/myPage/MyProfile.vue"),
      },
    
      {
        path: "/edit",
        name: "Edit",
        component: () => import("../components/myPage/EditProfile.vue"),
      },
    
      {
        path: "/myCoupon",
        name: "MyCoupon",
        component: () => import("../components/myPage/MyCoupon.vue"),
      },
      
      {
        path: "/orderMenu",
        name: "OrderMenu",
        component: () => import("../components/myPage/OrderMenu.vue"),
      },
    
      {
        path: "/myReview",
        name: "MyReview",
        component: () => import("../components/myPage/MyReview.vue"),
      },
    
      {
        path: "/question",
        name: "Question",
        component: () => import("../components/myPage/Question.vue"),
      },
    ]
  },
  {
    path: "/test",
    name: "Test",
    component: () => import("../views/Test.vue"),
  },
  {
    path: "/storeregister",
    name: "StoreRegister",
    component: () => import("../views/store/StoreRegister.vue"),
  },
  {
    path: "/store",
    name: "StoreHome",
    component: () => import("../views/store/StoreHome.vue"),
    children: [
      {
        path: "/review",
        name: "Review",
        component: () => import("../components/adminComponent/Review.vue"),
      },
      {
        path: "/storemap",
        name: "StoreMap",
        component: () => import("../components/adminComponent/StoreMap.vue"),
      },
      {
        path: "/storemenu",
        name: "StoreMenu",
        component: () => import("../components/adminComponent/StoreMenu.vue"),
      },
    ],
  },
  {
    path: "/admin",
    name: "AdminHome",
    component: () => import("../views/admin/AdminHome.vue"),
  },
  {
    path: "/admincouponadd",
    name: "AdminCouponAdd",
    component: () => import("../views/admin/AdminCouponAdd.vue"),
  },
  {
    path: "/admincoupon",
    name: "AdminCoupon",
    component: () => import("../views/admin/AdminCoupon.vue"),
  },
  {
    path: "/storeMenus",
    name: "StoreMenus",
    component: () => import("../views/store/StoreMenus.vue"),
  },
  {
    path: "/addMenu",
    name: "addMenu",
    component: () => import("../views/store/StoreAddMenu.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.afterEach(() => {
  // ${//these hooks do not get a next function and cannot affect the navigation}
  // jwt 디코드후, 롤 확인
  setTimeout(() => {
    //
  }, 0);
  console.log("페이지를 이동합니다");
});

export default router;
