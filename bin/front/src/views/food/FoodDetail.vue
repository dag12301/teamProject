<template>
<table class="tg">
<thead>
  <tr>
    <th class="tg-9fjy" colspan="2">가게 상세정보</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-b420">주소</td>
    <td class="tg-b420">인천광역시 미추홀구 학익동</td>
  </tr>
  <tr>
    <td class="tg-b420">전화번호</td>
    <td class="tg-b420">032-000-0000</td>
  </tr>
  <tr>
    <td class="tg-b420">영업시간</td>
    <td class="tg-b420">08:30 ~ 16:50</td>
  </tr>
  <tr>
    <td class="tg-zv4m" colspan="2">우리 가게는 국내산만 취급합니다. <br>가족들이 먹는다는 생각으로 음식을 만듭니다.</td>
  </tr>
</tbody>
</table>

--------------------------------메뉴-----------------------------------

<table class="tg">
<thead>
  <tr>
    <th class="tg-kjb5" colspan="2">음식메뉴</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-xs9c">짜장면</td>
    <td class="tg-xs9c">4,500 원</td>
  </tr>
  <tr>
    <td class="tg-xs9c">짬뽕</td>
    <td class="tg-xs9c">5,000 원</td>
  </tr>
  <tr>
    <td class="tg-xs9c">탕수육</td>
    <td class="tg-xs9c">12,000 원</td>
  </tr>
  <tr>
    <td class="tg-xs9c">세트메뉴1<br>(짜장+탕수육)</td>
    <td class="tg-xs9c">15,000 원</td>
  </tr>
</tbody>
</table>


--------------------------------리뷰-----------------------------------


<table class="tg">
<thead>
  <tr>
    <th class="tg-0q9k" colspan="3">리뷰</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-v0mg" rowspan="3">4.9<br>★★★★☆</td>
    <td class="tg-8jgo">맛</td>
    <td class="tg-8jgo">★★★★☆</td>
  </tr>
  <tr>
    <td class="tg-8jgo">양</td>
    <td class="tg-8jgo">★★★★☆</td>
  </tr>
  <tr>
    <td class="tg-8jgo">배달</td>
    <td class="tg-8jgo">★★★★☆</td>
  </tr>
  <tr>
    <td class="tg-zv4m">리뷰 0개</td>
    <td class="tg-zv4m" colspan="2">사장님댓글 0개</td>
  </tr>
</tbody>
</table>
<ul id="review" class="list-group review-list">
<li class="list-group-item ng-hide" ng-show="restaurant.reviews.length<1">
<p class="review-empty clearfix ng-binding">주문 후 첫번째 리뷰를 남겨보세요.</p>
</li>
<li class="list-group-item star-point ng-scope" ng-repeat="review in restaurant.reviews" on-finish-render="scrollCartArea()">
<div>
<span ng-show="review.phone" class="review-id ng-binding">#{userId)</span>
<span ng-bind="review.time|since" class="review-time ng-binding">#{sysdate}</span>
<a href class="btn-report" ng-click="report_reply(review.id, 'user', review)">신고</a>
</div>
<div>
<div class="star-point">
<span class="total">
<span class="full ng-scope" ng-repeat="i in review.rating|number_to_array track by $index">★</span>
<span class="full ng-scope" ng-repeat="i in review.rating|number_to_array track by $index">★</span>
<span class="full ng-scope" ng-repeat="i in review.rating|number_to_array track by $index">★</span>
<span class="full ng-scope" ng-repeat="i in review.rating|number_to_array track by $index">★</span>
<span class="full ng-scope" ng-repeat="i in review.rating|number_to_array track by $index">★</span>
</span>
<span class="category" ng-show="review.rating_taste > 0 || review.rating_quantity > 0 || review.rating_delivery > 0">
                <span class="category-tit" ng-show="review.rating_taste > 0">맛</span>
                <span class="full" ng-show="review.rating_taste > 0">★</span>
                <span class="points ng-binding" ng-show="review.rating_taste > 0">5</span>
                <span class="category-tit" ng-show="review.rating_quantity > 0">양</span>
                <span class="full" ng-show="review.rating_quantity > 0">★</span>
                <span class="points ng-binding" ng-show="review.rating_quantity > 0">5</span>
                <span class="category-tit" ng-show="review.rating_delivery > 0">배달</span>
                <span class="full" ng-show="review.rating_delivery > 0">★</span>
                <span class="points ng-binding" ng-show="review.rating_delivery > 0">5</span>
              </span>
</div>
</div>
<div class="order-items default ng-binding" ng-click="show_review_menu($event)">
            #{시킨음식}<!-- 짜장면1, 짬뽕1 -->
          </div>
<p ng-show="review.comment" ng-bind-html="review.comment|strip_html" class="ng-binding">#{작성글}<!-- 맛있게 잘먹었어요~ --></p>
</li>
</ul>

------------------------------밑엔 표로 만든 리뷰---------------------------------------------

<table class="tg">
<thead>
  <tr>
    <th class="tg-4z9b">#{유저아이디}</th>
    <th class="tg-4z9b">#{작성시간}</th>
    <th class="tg-4z9b">신고</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-4z9b">★★★★★</td>
    <td class="tg-4z9b" colspan="2">맛5 양5 배달5</td>
  </tr>
  <tr>
    <td class="tg-4z9b" colspan="3">#{시킨음식메뉴} </td>
  </tr>
  <tr>
    <td class="tg-4z9b" colspan="3">#{작성글내용}</td>
  </tr>
</tbody>
</table>
</template>

<style scoped>
    table
{
	position: relative;
    border: 1px solid #555555;
    background: #fff;
    padding: 0 8px;
    margin-bottom: 10px;
    height: 108px;
    width: 350px;
}
ul
{
	list-style:none;
}

.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-zv4m{border-color:#ffffff;text-align:left;vertical-align:top}
.tg .tg-9fjy{border-color:#ffffff;font-family:Impact, Charcoal, sans-serif !important;;font-size:24px;font-weight:bold;
  text-align:center;vertical-align:top}
.tg .tg-b420{border-color:#ffffff;font-size:18px;text-align:center;vertical-align:top}

.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-kjb5{border-color:#efefef;font-size:24px;font-weight:bold;text-align:center;vertical-align:top}
.tg .tg-xs9c{border-color:#efefef;font-size:13px;text-align:center;vertical-align:top}

.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-zv4m{border-color:#ffffff;text-align:left;vertical-align:top}
.tg .tg-8jgo{border-color:#ffffff;text-align:center;vertical-align:top}
.tg .tg-0q9k{border-color:#ffffff;font-size:28px;font-weight:bold;text-align:center;vertical-align:top}
.tg .tg-v0mg{border-color:#ffffff;text-align:center;vertical-align:middle}

.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-4z9b{border-color:#efefef;font-size:16px;text-align:left;vertical-align:top}
</style>