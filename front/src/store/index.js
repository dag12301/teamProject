import { createStore } from "vuex";

import auth from "@/store/auth";
import error from "@/store/error";
import main from "@/store/main";

export default createStore({
  modules: {
    auth,
    error,
    main,
  },
});
