<template>
  <transition
    name="expand"
    @enter="enter"
    @after-enter="afterEnter"
    @leave="leave"
  >
    <ul v-show="list.open" class="list-item">
      <li clas="sub-items" v-for="(item, index) in list.sublist" :key="index">
        <div class="item-box">
          <div class="item-text">
            <div class="menu-name">
              <!--가게 음식메뉴 이름-->
              {{ item }}
            </div>
            <div class="menu-price">9,000원</div>
          </div>
          <div class="item-img">이미지</div>
        </div>
      </li>
    </ul>
  </transition>
</template>

<script>
export default {
  props: ["list"],
  methods: {
    enter(el) {
      el.style.height = "auto";
      const height = getComputedStyle(el).height;
      el.style.height = 0;

      getComputedStyle(el);

      setTimeout(() => {
        el.style.height = height;
      });
    },
    afterEnter(el) {
      el.style.height = "auto";
    },
    leave(el) {
      el.style.height = getComputedStyle(el).height;
      getComputedStyle(el);
      setTimeout(() => {
        el.style.height = 0;
      });
    },
  },
};
</script>

<style scoped>
.list-item {
  list-style: none;
}
.list-item .sub-items {
  padding: 10px;
  text-indent: 20px;
  color: #fefefe;
  background-color: #333;
}

.expand-enter-active,
expand-leave-active {
  transition: height 0.5s ease-in-out;
  overflow: hidden;
}

.item-box {
  width: 100%;
  height: 100px;
  background-color: white;
  border-top: 1px solid whitesmoke;
}
.item-text {
  float: left;
  padding-top: 20px;
  padding-bottom: 20px;
  margin-left: 10px;
}
.item-img {
  float: right;
  border: 1px solid black;
  width: 90px;
  height: 90px;
  margin-top: 4px;
  margin-right: 10px;
}

.menu-name {
  font-size: 22px;
  font-weight: bold;
}
</style>
