

export default {
  namespaced: true,
  state: {
    data: null,
    id: 'test',
    pwd: 1234
  },
  mutations: {
    loginProc(state) 
    {
      console.log(state.id)
      
    }
  },
  actions: {
    testing  (context)
    {
      context.commit('loginProc')
    }
  }


}