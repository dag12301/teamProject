<template>
  <div class="notice col-12 h-50">
    <h2 class="mb-5 font-weight-bold">공지사항 </h2>
    
    <div class="p-1  bg-dark text-white"></div>
    <!-- 리스트 시작 -->
    <ul class="list-group list-group-flush">
      <li class="list-group-item " >
          <span class="position-absolute" style="left: 1vw;">1</span>
          <span class="position-absolute" style="left: 6vw;">마크</span>
          <span class="position-absolute" style="left: 10vw;">내용</span>
          <span class=" position-absolute" style="right: 12vw;">날짜</span>
          <span class=" position-absolute" style="right: 7vw;">
            조회수
          </span>
          <span class=" position-absolute" style="right: 1vw;">답변유무</span>
        
      </li>

      <li class="list-group-item " >
          <span class="position-absolute" style="left: 1vw;">1</span>
          <span class="position-absolute" style="left: 6vw;">마크</span>
          <span class="position-absolute" style="left: 10vw;">내용</span>
          <span class=" position-absolute" style="right: 12vw;">날짜</span>
          <span class=" position-absolute" style="right: 7vw;">
            조회수
          </span>
          <span class=" position-absolute" style="right: 1vw;">답변유무</span>
        
      </li>
      <hr>
    </ul>
     <!-- 끝 -->
    
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
export default {

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