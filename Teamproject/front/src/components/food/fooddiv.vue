<template>
    <div class="food-div" v-for="(food, index) in $store.state.fooddiv.foods" :key="index">
        <a class="food-a" href="#"><router-link class="nav-link" :to="{name: 'FoodList'}" exact>
            <div class="food-title">
                <p class="food-p">{{food.foodname}}</p>
                <img class="food-img" src="../../assets/pizza-3000285_1280.png" style="width: 300px; left:-10px; top: 35px;"/>
            </div>                  <!-- 이미지만 바꿔주면 됨 -->
        </router-link></a>
    </div>
</template>

<script>
export default {};
</script>

<style>
.food-div{
		color: black;
		text-decoration: none;
		padding: 15px;
		
		display: inline-block;
		
		width: 200px;
        height: 200px;
		
		margin: 5px;
        text-align: left;
        
        background-color: lightyellow;
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