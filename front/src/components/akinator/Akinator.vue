<template>
  <div class="focus">
    <div class="chat_wrap">
      <div class="inner">
        <div class="item">
          <span class="item_name">WHAT POO</span>
          <div class="box">
            <p class="msg">매운게 좋아? 안매운게 좋아?</p>
          </div>
        </div>
        <div class="button1">
          <input
            type="button"
            value="매운 음식"
            @click="hideAki()"
            class="btn1"
            id="btn1-1"
          />
          <input
            type="button"
            value="안매운 음식"
            @click="hideAki()"
            class="btn1"
            id="btn2-1"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapMutations } from "vuex";
export default {
  methods: {
    ...mapMutations({
      hideAki: "toggle/toggleAki",
    }),
  },
};
</script>
<style scoped>
.focus {
  max-width: 100%;
  max-height: 100%;
  bottom: 0;
  left: 0;
  margin: auto;
  overflow: auto;
  position: fixed;
  right: 0;
  top: 0;
  background: rgba(0, 0, 0, 0.9);
  z-index: 100;
}
.chat_wrap {
  margin: 0px auto;
  width: 500px;
  padding: 5px;
  font-size: 13px;
  color: #333;
  height: 100vh;
}
.chat_wrap .inner {
  background-color: #acc2d2;
  border-radius: 5px;
  padding: 10px;
  height: 100%;
}
.chat_wrap .item {
  margin-top: 30px;
}
.chat_wrap .item:first-child {
  margin-top: 20px;
}
.chat_wrap .item .box {
  display: inline-block;
  width: 90%;
  position: relative;
}

.chat_wrap .item .box:before {
  content: "";
  position: absolute;
  left: -8px;
  top: 9px;
  border-top: 0px solid transparent;
  border-bottom: 8px solid transparent;
  border-right: 8px solid #fff;
} /* 채팅 상자 옆에 세모 */
.chat_wrap .item .box .msg {
  background: #fff;
  border-radius: 10px;
  padding: 8px;
  text-align: center;
  font-size: 20px;
  font-weight: bolder;
}

.chat_wrap .item.mymsg {
  text-align: right;
}
.chat_wrap .item.mymsg .box:before {
  left: auto;
  right: -8px;
  border-left: 8px solid #fee600;
  border-right: 0;
}

.chat_wrap .item .item_name {
  margin-left: 7%;
  font-weight: bolder;
  font-size: 15px;
}
.chat_wrap .item.mymsg .box .msg {
  background: #fee600;
}
.chat_wrap .item .box {
  transition: all 0.3s ease-out;
  margin: 10px 0 0 20px;
}
.chat_wrap .item.mymsg .box {
  margin: 0 20px 0 0;
}

.button1 {
  font-size: 10px;
  margin-top: 60%;
  text-align: center;
  font-weight: bolder;
}

.btn1 {
  border: 0;
  width: 60%;
  background: #ddd;
  border-radius: 5px;
  height: 30px;
  padding-left: 5px;
  box-sizing: border-box;
  margin-top: 5px;
  margin-bottom: 5%;
  font-size: 15px;
}
</style>
