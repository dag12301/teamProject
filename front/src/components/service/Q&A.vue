<template>
  <div>

    <h2>Q&A</h2>
      <div  id="Search" class="input-group mb-3">
        <input type="text" class="form-control" placeholder="여기다 글쓰고 검색하셈" aria-label="Recipient's username" aria-describedby="button-addon2">
        <button class="btn btn-outline-secondary" type="button" id="button-addon2">검색</button>
      </div>
      <!-- 글쓰기 -->
      <div class="">
          <button type="button" class="btn btn-primary ">내글조회</button>
          <router-link class="btn btn-primary" :to="{ name: 'WriteForm' }">글쓰기</router-link>
      </div>
      
      <br>
      <!-- 리스트 -->
      <table class="table table-striped ">
        <tbody>
          <tr v-for=" (qn,index) in this.$store.state.serviceCenter.queAn" :key="index"  @click="listPage(qn.articleId, qn.status)">
            <td class="col-1">{{index}}</td>
            <td class="col-1">{{qn.title}}</td>
            <td class="col-5 text-left">{{qn.body}}</td>
            <td class="col-2">{{qn.regDate}}</td>
            <td class="col-1">{{qn.hit}}</td>
            <td class="col-1" v-if="privateToggle(qn.status)">
              <i class="fas fa-lock-open"></i>
            </td>
            <td class="col-1" v-else>
              <i class="fas fa-lock"></i>
            </td>
          </tr>

        </tbody>
      </table>

      <!-- 순서 버튼 -->
    <nav aria-label="Page navigation example" class="mt-5 position-relative .center-block" style="">
      <ul class="pagination position-absolute" style="left: 30vw" >
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item" v-for="num in pageCnt" :key="num">
          <a class="page-link" href="#" >{{num}}</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">{{paging}}</a></li>
        
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
      
    </nav>
    <!-- 끝 -->
</div>
</template>

<script>
//import * as authAPI from "@/api/article.js";

export default {
  data() {
    return {
      paging: {
      },
      pageCnt:[]
    }
  },
  computed: {
    
  },
  methods: {
    pageNumber (pageCnt) {
      const list= []
      for(var i = 1; i <= pageCnt; i++){
        list.push(i)
      }
      return list
    },
    listPage(articleId, status) {     //페이지 이동
      if(this.privateToggle(status)){
        console.log(articleId)
        return location.href="/boardList?board=qna&articleId=" + articleId
      }
      return alert("비공개 입니다")
    },
    privateToggle (status) {    //공개 비공개 검사
      if(status === 'b'){
        return true
      }
    },
    // numPage() {           //페이지 번호로 이동 /qna getBoardList
    //   authAPI
    //   .getBoardList(2, page, range)
    //   .then(res => {
    //     this.$store.commit('serviceCenter/pagingQueAn', null)
    //   })
      
    // }
  },
  created () {
    this.paging = this.$store.state.serviceCenter.pagingQueAn
    this.pageCnt = this.pageNumber(this.paging.pageCnt)
    
  }
}
</script>
  
<style scoped>
#Search{
    width: 35%;
    left: 35%;
}
button{
    width:100px;
    float: right;
}



</style>
