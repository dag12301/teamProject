import { createStore } from "vuex";

import auth from "@/store/auth";
import error from "@/store/error";

export default createStore({
  modules: {
    auth,
    error,
  },
});
