export default {
  namespaced: true,
  state: {
    pagingNotices: [

    ],
    pagingQueAn:[],
    pagingFQA:[],
    notices: [
    ],
    queAn: [

    ],
    FQA: [],
    originRange:[]  

  },
  getters: {
    
    
    AllCenterList (state) {
      return state.notices.length
    },
    
    getnumSize (state)  {                      //Q&A 버튼 숫자 리스트
      let list =[]
      if( state.pagingQueAn.Range < 2 ||state.pagingQueAn.startPage < 1){
        console.log("-------------------신호-------------------- ")
        state.pagingQueAn.startPage = 1
        state.pagingQueAn.Range = state.originRange[1]
        console.log("Range: " + state.pagingQueAn.range)
      }
      console.log("--------------------------------------- ")
      console.log("startPage: " + state.pagingQueAn.startPage)
      console.log("endPage: " + state.pagingQueAn.endPage)
      console.log("Range: " + state.pagingQueAn.range)
      console.log("originRange: " + state.originRange[1])
      console.log("--------------------------------------- ")
      for(var i =  state.pagingQueAn.startPage; i <= state.pagingQueAn.endPage; i++)
      {
        list.push(i) 
      }
      return list
    },
    getQueAnPaging (state) {                     //Q&A 페이지 정보
      let page = {
        rangeSize: state.pagingQueAn.rangeSize,
        
        range: state.pagingQueAn.range,
        prev: state.pagingQueAn.prev,
        next: state.pagingQueAn.next
      }
      console.log(state.pagingQueAn.endPage + "aaaaaaaaaaaaaaaaaaaaaa")
      return page
    }
  },
  mutations: {
    setCenternotices (state, payload) {                   //Notices페이지 저장
      return  state.notices.push(payload)
    },
    setCenterQueAn(state, payload) {                      //QueAn 페이지 저장
      return  state.queAn.push(payload)
    },
    setCenterFQA (state, payload) {                       //FQA 페이지 저장
      return  state.FQA.push(payload)
    },
    setPagingNotices (state, payload) {                     //Notices페이징 저장 정보
      return  state.pagingNotices = payload
    },
    setPagingQueAn (state, payload) {                       //QueAn페징 저장 정보
      return  state.pagingQueAn = payload
    },
    setPagingFQA (state, payload) {                         //FQA페이징 저장 정보
      return  state.pagingFQA = payload
    },
    nullCenterQueAn(state) {                             //QueAn페이지 삭제
      return state.queAn = []
    },
    nullPagingpagingQueAn(state) {                      //FQA페이징 삭제 
      return state.pagingQueAn = []
    }
  },
  actions: {
    
  }


}