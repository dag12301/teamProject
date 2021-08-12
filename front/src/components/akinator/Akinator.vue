<template>
  <div class="focus">
    <div class="chat_wrap">
      <div class="inner">
        <div class="item">
          <!-- 아킨이처 취소 -->
          <button @click="cancelAki">취소</button>

          <span class="item_name">WHAT POO</span>
          <div class="box">
            <p class="msg">{{ stats[questionNum].query }}</p>
          </div>
          <div class="item mymsg">
            <!-- 이거 답장임 -->
            <div class="box" v-for="(choose, i) of choosen" :key="i">
              <p class="msg">{{ choose.split("-")[1] }}</p>
            </div>
          </div>
        </div>
        <!-- 이거 화면픽스좀? -->
        <div class="button1">
          <input
            type="button"
            :value="answer"
            @click="addTochoosen(answer)"
            class="btn1"
            v-for="answer in stats[questionNum].answers"
            :key="answer.id"
            :id="answer.id"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapMutations, mapGetters } from "vuex";
export default {
  data() {
    return {
      choosen: [],
      questionNum: 0,
    };
  },
  computed: {
    ...mapGetters({
      stats: "akinator/getQuestion",
    }),
  },
  methods: {
    ...mapMutations({
      hideAki: "toggle/toggleAki",
    }), //만들어지면 데이터받아서 바인딩
    addTochoosen(data) {
      this.choosen.push(this.stats[this.questionNum].id + "-" + data);
      console.log(this.choosen);
      this.questionNum++;
      // 답장 받은대로 css그리기

      // 임시로 숨기기.
      // 작업시 데이터 비동기로 보내고, 모달로 로딩화면, 데이터오면 로딩종료, view보여주기
      if (this.questionNum == 3) {
        this.hideAki();
        let str = "";
        this.choosen.forEach((ans) => {
          str +=
            ans.split("-")[0] + "번째 문제의 답은, " + ans.split("-")[1] + "\n";
        });
        alert(str);

        location = "/view"; // 아키네이처 끝나고 이동 페이지 건우
      }
    },

    /** axios 아키네이션 취소 메서드
     * 작성자 : 김건우
     * 사용법 : toggle.js
     *  isAkiOn을 false 한다
     *  ...
     *
     *
     */
    //취소 메소드
    cancelAki() {
      this.hideAki();
      location = "/food"; // 아키네이처 끝나고 이동 페이지 건우
    },
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
