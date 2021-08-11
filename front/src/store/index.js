import { createStore } from "vuex";



import user from "./user/user.js";
import shopList from "./shopList/shopList.js";
import menu from "./menu/menu.js";

export default createStore({
  
  actions: {
    tes () {
      console.log("2")
    }
    
    
  },
  modules: {
    user: user,
    shopList: shopList,
    menu: menu
  },
  
});
