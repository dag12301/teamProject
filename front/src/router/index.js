import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";

const View = () => import("../views/viewBoard/View.vue");

const Login = () => import("../views/login/Login.vue");

const Join = () => import("../views/login/Join.vue");

const Cart = () => import("../views/cart/Cart.vue");

const Food = () => import("../views/food/Food.vue");

const AkinatorPage = () => import("../views/akinator/AkinatorPage.vue");

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",

    component: () => import("../views/About.vue"),
  },
  {
    path: "/view",
    name: "View",
    component: View,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/cart",
    name: "Cart",
    component: Cart,
  },
  {
    path: "/food",
    name: "Food",
    component: Food,
  },
  {
    path: "/akinator",
    name: "AkinatorPage",
    component: AkinatorPage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
