<template>
  <div class="notice col-12 h-50">
    <h2 class="mb-5 font-weight-bold">공지사항 </h2>
    
    <div class="p-1  bg-dark text-white"></div>
    <!-- 리스트 시작 -->
    <ul class="list-group list-group-flush">
      <li class="list-group-item " @click="listPage(notice.articleId)"
        v-for=" notice in this.$store.state.serviceCenter.notices" :key="notice.id">
          <span class="position-absolute" style="left: 1vw;">[{{notice.articleId}}]</span>
          <span class="position-absolute" style="left: 6vw;">{{notice.title}}</span>
          <span class=" position-absolute" style="right: 6vw;">{{notice.regDate}}</span>
          <span class=" position-absolute" style="right: 4vw;">
            <i class="fas fa-eye"></i>
          </span>
          <span class=" position-absolute" style="right: 1vw;">{{notice.hit}}</span>
        
      </li>
      
    </ul>
     <!-- 끝 -->
    <div class="p-1  bg-dark text-white"></div>
    <!-- 순서 버튼 -->
    <nav aria-label="Page navigation example" class="mt-5 position-relative .center-block" >
      <ul class="pagination position-absolute" style="left: 30vw">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
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
import { mapGetters } from 'vuex'
export default {
computed: {
  ...mapGetters(['getCenterList'])
},
methods: {
  listPage(articleId) {
    console.log(articleId)
    location.href="/writeForm?articleId=" + articleId
  }
}
}
</script>

<style scoped>
.notice ul li {
  height: 7vh;
  padding-top:2vh; 
  padding-bottom:2vh; 
  font-size: 2vh;
}

</style>