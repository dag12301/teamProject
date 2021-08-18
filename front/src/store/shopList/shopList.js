

export default {
  namespaced: true,
  state: {
    shops: [
      {
        shopName: '중국집',
        regDate: '3 days ago',
        content: '짜장면과 짬뽕을 판다.'
      },
      {
        shopName: '피자집',
        regDate: '2 days ago',
        content: '멕시코산 치즈를 곁들인 피자팜'
      },
      {
        shopName: '파전집',
        regDate: '3 days ago',
        content: '비오는데 파전이 먹고 싶다.'
      },
       {
        shopName: '치킨집',
        regDate: '0.5 days ago',
        content: '양손에 치킨이야'
      }
    ],
    notices: [
      {
        id: '1',
        status: '공지',
        content: '정기 점검 안내',
        regDate: '2021.08.11',
        cnt: '15'
      },
      {
        id: '2',
        status: '공지',
        content: '코롤라 이벤트 점검 안내',
        regDate: '2021.08.14',
        cnt: '1425'
      },
      {
        id: '3',
        status: '공지',
        content: '정기 먹방  안내',
        regDate: '2021.05.11',
        cnt: '125'
      }
    ],
    qns:[
      {
        status: '비밀',
        content: '아하이',
        regDate: '2021.08.14',
        cnt: '145'
      },
      {
        status: '공지',
        content: '아하이2',
        regDate: '2021.05.14',
        cnt: '5'
      },
      {
        status: '공지',
        content: '아하이3',
        regDate: '2021.04.14',
        cnt: '1'
      },
      {
        status: '비공개',
        content: '아하이4',
        regDate: '2021.01.14',
        cnt: '15'
      }
    ]

  },
  mutations: {
    
  },
  actions: {
    
  }


}