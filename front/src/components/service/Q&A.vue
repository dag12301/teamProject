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
          <button type="button" class="btn btn-primary me-3" >글쓰기</button>
      </div>
      
      
      <!-- 리스트 -->
      <table class="table table-striped ">
        <tbody>
          <tr v-for=" (qn,index) in this.$store.state.serviceCenter.queAn" :key="index">
            <td class="col-1">{{index}}</td>
            <td class="col-1">{{qn.title}}</td>
            <td class="col-5 text-left">{{qn.body}}</td>
            <td class="col-2">{{qn.regDate}}</td>
            <td class="col-1">{{qn.hit}}</td>
            <td class="col-1">
              icon
            </td>
          </tr>

        </tbody>
      </table>

      <!-- 순서 버튼 -->
    <nav aria-label="Page navigation example" class="mt-5 position-relative .center-block" style="">
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
#Search{
    width: 35%;
    left: 35%;
}
button{
    width:100px;
    float: right;
}



</style>
