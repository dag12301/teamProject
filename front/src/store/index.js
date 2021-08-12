import { createStore } from "vuex";

import auth from "@/store/auth";
import error from "@/store/error";
import toggle from "@/store/toggle";
import akinator from "@/store/akinator";

import shopList from "./shopList/shopList.js";
import menu from "./menu/menu.js";

export default createStore({
  // poo: poo 처럼 앞뒤가 같을때는 그냥 묶어서 한번에적어도됨
  modules: {
    auth,
    error,
    toggle,
    akinator,

    shopList,
    menu,
  },
});
