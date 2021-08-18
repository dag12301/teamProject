<template>
    <div class="food-div" v-for="(food, index) in $store.state.foodDiv.foods" :key="index">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p"><router-link class="nav-link" :to="{name: 'Foodnum2'}" exact>{{food.foodname}}</router-link></p>
                <img class="food-img" src="http://t1.kakaocdn.net/kakaot/images/meta/thumb_driver_1440x720.png" alt="사진" style="width: 300px; left:-10px; top: 35px;"/>
            </div>
        </a>
    </div>
</template>

<script>
export default {};
</script>

<style>
.food-div{
		color: black;
		text-decoration: none;
		padding: 15px; /* 패딩: 안쪽여백, 마진: 밖여백 */
		
		/* 글자가 깨졌으니 밑의 display 추가하자 */
		display: inline-block; /* 인라인모드/블럭모드 이해 필요  */
		
		width: 200px;
        height: 200px;
		
		margin: 5px;
        text-align: left;
	} 
    .food-a {
        position: relative;
        padding: 0;
        margin: 5px 0;
        text-decoration: none;
    }
    .food-title {
        width: 100%;
        color: #333;
        font-size: 18px;
        font-weight: bold;
    }
    .food-p {
        text-align: left;
        margin-left: 8px;
    }
    .food-img {
        position: absolute;
    }
    .food-div {
        overflow: hidden;
        border: 2px solid lightblue;
    }
</style>
   