<template>
    <div class="container">
        <div class="container-fluid px-4">
        <div class="row">
            <nav>
                <ul class="foodnum-ul" style="position: sticky;">
                    <li><a href="#" class="foodnum-a">전체보기</a></li>
                    <li><a href="#" class="foodnum-a">1인분 주문</a></li>
                    <li><a href="#" class="foodnum-a">프랜차이즈</a></li>
                    <li><a href="#" class="foodnum-a">치킨</a></li>
                    <li><a href="#" class="foodnum-a">피자/양식</a></li>
                    <li><a href="#" class="foodnum-a">중국집</a></li>
                    <li><a href="#" class="foodnum-a">한식</a></li>
                    <li><a href="#" class="foodnum-a">일식/돈가스</a></li>
                    <li><a href="#" class="foodnum-a">족발/보쌈</a></li>
                    <li><a href="#" class="foodnum-a">야식</a></li>
                    <li><a href="#" class="foodnum-a">분식</a></li>
                    <li><a href="#" class="foodnum-a">카페/디저트</a></li>
                </ul>   
            </nav>   
            


            <div>
                Level 1: .col-sm-9
                <div class="row">
                    <foodlist></foodlist>
                </div>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
import foodlist from  '../../components/food/foodlist.vue';

export default ({
  components: {
      foodlist
  }
})
</script>


<style scoped>
    /* 기본 설정*/
    .foodnum-a{text-decoration:none; color:#000000;}         
    .foodnum-a:hover{background-color:lightblue;}                    
         
    /* nav tag */

    .foodnum-ul{padding-top:10px; font-style: bold; font-size: 15px; top: 4px;}  

    .foodnum-ul li {
        display:inline;   
        border-left:1px solid #999;             /* 메뉴의 왼쪽에 "|" 표시 */                
        padding:0 10px;                         /* 각 메뉴 간격 */
    }
    .foodnum-ul li:first-child{border-left:none;}     /* 메뉴 분류중 제일 왼쪽의 "|"는 삭제  */

    .row div div a{margin-top: 10px;}

</style>

