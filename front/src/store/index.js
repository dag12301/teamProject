import { createStore } from "vuex";

import auth from "@/store/auth";
import error from "@/store/error";
import toggle from "@/store/toggle";

export default createStore({
  modules: {
    auth,
    error,
    toggle,
  },
});
