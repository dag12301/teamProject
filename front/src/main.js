import { createApp } from "vue";
import App from "./App.vue";
import router from "@/router";
import store from "@/store";
import vuex from "vuex";

// 부트스트랩 추가
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

createApp(App).use(store).use(router).use(vuex).mount("#app");
