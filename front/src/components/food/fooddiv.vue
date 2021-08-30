<template>
  <div
    class="food-div"
    v-for="(food, index) in $store.state.fooddiv.fooddivs"
    :key="index"
  >
    <a class="food-a" href="#"
      ><router-link class="nav-link" :to="{ name: 'FoodList' }" exact>
        <div class="food-title">
          <p class="food-p">{{ food.foodname }}</p>
          <img
            class="food-img" :class="[ index === 0 ? 'first' : '', index !== 0 ? 'first'+index : '']"
            :src="food.foodimg"
          />
        </div>
        <!-- 이미지만 바꿔주면 됨 -->
      </router-link></a
    >
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.food-div {
  color: black;
  text-decoration: none;
  padding-top: 10px;

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
.first {
  width: 220px;
  left: 10px;
}
.first1 {
  width: 220px;
  left: -5px;
}
.first2 {
  width: 220px;
  left: -10px;
}
.first3 {
  width: 230px;
  left: -20px;
}
.first4 {
  width: 300px; 
  left: -10px; 
  top: 35px;
}
.first5 {
  width: 225px;
  left: -7px;
}
.first6 {
  width:210px;
  left: -8px;
}
.first7 {
  width: 220px;
  left: -6px;
}
.first8 {
  width: 197px;
  left: -1px;
}
.first9 {
  width: 220px;
  left: -7px;
}
.first10 {
  width: 220px;
  left: -5px;
}
.first11 {
  width: 220px;
  left: -7px;
}
</style>
