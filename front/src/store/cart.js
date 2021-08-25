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
    delCart(state, foodId) {
      if (state.bag.has(foodId)) {
        state.bag.delete(foodId);
      }
    },
    clearCart(state) {
      state.bag = null;
      state.bag = new Set();
    },
  },
  actions: {},
};
