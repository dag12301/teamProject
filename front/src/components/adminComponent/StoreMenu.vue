<template>
  <ul class="wrapper">
    <li v-for="food in foodList" :key="food">
      <ListItem :list="food"></ListItem>
    </li>
  </ul>
</template>

<script>
import ListItem from "../storemenulist/ListItem.vue";

export default {
  props: ["foodList"],
  components: {
    ListItem,
  },
  methods: {},
};
</script>

<style scoped>
.wrapper {
  list-style: none;
  background-color: lightgray;
  width: 100%;
  height: 100%;
}

ul {
  list-style: none;
  padding-left: 0px;
}
li {
  width: 100%;
  position: relative;
  cursor: pointer;
  list-style: none;
  padding-left: 0px;
  font-weight: bold;
}

li .title {
  padding: 10px;
  text-indent: 20px;
  text-align: left;
}

i {
  position: absolute;
  top: 14px;
  transition: transform 0.5s ease-in-out;
}

i .open {
  transform: rotatez(180deg);
}

.div-box {
  width: 100%;
  height: 40px;
}

.div-left {
  float: left;
  width: 50%;
  height: 40px;
}
.div-right {
  float: right;
  width: 50%;
  height: 40px;
  padding: 10px;
  text-align: right;
  padding-right: 30px;
}
</style>
