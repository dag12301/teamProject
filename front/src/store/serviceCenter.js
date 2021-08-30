export default {
  namespaced: true,
  state: {
    pagingNotices: [

    ],
    pagingQueAn:[],
    pagingFQA:[],
    //게시물 저장
    notices: [
    ],
    queAn: [

    ],
    FQA: [] 

  },
  getters: {
    getnumNoticeSize (state) {                     //notice 버튼 숫자 리스트
      let list =[]
  
      for(var i =  state.pagingNotices.startPage; i <= state.pagingNotices.endPage; i++){
        list.push(i) 
      }
      return list
    },
    getNoticePaging (state) {                     //notice 페이지 정보
      return state.pagingNotices
    },
    getnumSize (state)  {                      //Q&A 버튼 숫자 리스트
      let list =[]
  
      for(var i =  state.pagingQueAn.startPage; i <= state.pagingQueAn.endPage; i++){
        list.push(i) 
      }
      return list
    },
    getQueAnPaging (state) {                     //Q&A 페이지 정보
      return state.pagingQueAn
    },
    getFQANumSize (state)  {                      //FQA 버튼 숫자 리스트
      let list =[]
      
      for(var i =  state.pagingFQA.startPage; i <= state.pagingFQA.endPage; i++){
        list.push(i) 
      }
      return list
    },
    getFQAPaging (state) {                     //FQA 페이지 정보
      return state.pagingFQA
    },
    getNoticeTotal(state){
      return state.notices.length
    },
    getQueAnTotal(state){
      return state.queAn.length
    },
    getFQATotal(state){
      return state.FQA.length
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
    setPagingNotices (state, payload) {                     //버튼 Notices페이징 저장 정보
      return  state.pagingNotices = payload
    },
    setPagingQueAn (state, payload) {                       //버튼 QueAn페징 저장 정보
      return  state.pagingQueAn = payload
    },
    setPagingFQA (state, payload) {                         //버튼 FQA페이징 저장 정보
      return  state.pagingFQA = payload
    },
    nullCenterNotices (state) {                             //notices페이지 삭제
      return state.notices = []
    },
    nullCenterQueAn(state) {                             //QueAn페이지 삭제
      return state.queAn = []
    },
    nullCenterFQA(state) {                              //FQA페이지 삭제
      return state.FQA = []
    },
    nullPagingpagingQueAn(state) {                      //Q&A페이징 삭제 
      return state.pagingQueAn = []
    },
    nullPagingNotice(state){                        //notice페이징 삭제
      console.log(state.pagingNotices)
      return state.pagingNotices = []
    },  
    nullPagingFQA(state){                           //pagingFQA페이징 삭제
      return state.pagingFQA = []
    }
  },
  actions: {
    
  }


}