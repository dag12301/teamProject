export default {
  state: {
    bag: new Set(),
  },
  getters: {
    checkCart(state) {
      return state.bag instanceof Set ? state.bag : new Set();
    },
  },
  mutations: {
    addCart(state, foodId) {
      if (state.bag instanceof Set) {
        state.bag.add(foodId);
      } else {
        state.bag = new Set();
        state.bag.add(foodId);
      }
    },
  },
  actions: {},
};
