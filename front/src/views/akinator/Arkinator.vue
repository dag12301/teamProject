<template>
  <div class="row wrapper m-4">
    <div class="col-md-8 left">
      <!-- 채팅화면탭 -->
      <div class="question">
        {{ question }}
      </div>
      <div class="chatWrapper m-1 p-2" id="log">
        <!-- 답변들 -->
        <div
          class="answer p-1 m-1"
          v-for="(answer, index) of answers"
          :key="index"
        >
          {{ answer }}
        </div>
      </div>
      <div class="alternativesWrapper m-1 p-1">
        <!-- 답변목록탭 -->
        <div
          class="alternative p-1 m-1 btn btn-outline-primary"
          v-for="(alternative, index) of alternatives"
          :key="index"
        >
          {{ alternative }}
        </div>
      </div>
    </div>
    <div class="col-md-4 col-sm-12 right row-cols-1 p-2">
      <div class="filterWrapper p-2">
        <!-- 검색탭 -->
        <div class="input-group mb-3">
          <input
            type="text"
            class="form-control"
            placeholder="검색"
            aria-describedby="food-filter"
          />
          <span
            class="input-group-text btn btn-outline-primary"
            id="food-filter"
            >검색</span
          >
        </div>
      </div>
      <div class="foodListWrapper p-2">
        <!-- 음식목록탭 -->
        <div
          class="foodListContainer m-1"
          v-for="(food, index) of foodList"
          :key="index"
        >
          {{ food }}
        </div>
      </div>
      <div class="choicesWrapper">
        <!-- 버튼탭 -->
        <div class="btn btn-outline-success" @click="test">선택</div>
        <div class="btn btn-outline-danger" @click="clear">나가기</div>
      </div>
      <div class="noMoreAkinator p-2">
        <div class="form-check">
          <input
            class="form-check-input"
            type="checkbox"
            id="flexCheckDefault"
          />
          <label class="form-check-label" for="flexCheckDefault">
            오늘 아키네이터 하지않기
          </label>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    test() {
      this.answers.push("table-layout");
      this.alternatives.push("table-layout");
      this.foodList.push("table-layout");
    },
    clear() {
      this.answers = [];
      this.alternatives = [];
      this.foodList = [];
    },
  },
  data() {
    return {
      alternatives: [],
      foodList: [],
      answers: [],
      question: "무슨음식이좋아요?",
    };
  },
};
</script>

<style scoped>
.wrapper {
  height: 100vh;
  /* 전체 아키네이터의 최소크기설정 */
  min-width: 300px;
}
.left {
  /* 가운데 중심으로 왼쪽탭들 */
  background: rgb(255, 226, 220);
  border: 1px solid grey;
  display: inline-flex;
  flex-flow: row wrap;
  height: 100%;
}
.right {
  /* 가운데 중심으로 오른쪽 탭들 */
  background: rgb(213, 255, 255);
  border: 1px solid grey;
  /* 오른쪽 탭의 최소크기 설정 */
  min-width: 100px;
  /* 스크롤에 화면 안밀리게 */
  height: 100%;
}

/* 채팅화면 설정 */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
#log > div {
  position: relative;
  margin-top: 20px;
  /* 애니메이션속성 */
  animation: fadeInUp 0.5s ease forwards;
}

#log > div.deleted {
  visibility: hidden;
}

.chatWrapper {
  /* 채팅화면탭 */
  background: tan;
  width: 100%;
  height: 70%;
  display: flex;
  /* 정렬방식 */
  flex-flow: column-reverse nowrap;
  align-items: flex-end;
  align-content: flex-end;
  overflow: hidden;
}
.question {
  position: absolute;
  width: 35%;
  left: 300px;
  min-height: 120px;
  top: 50px;
  padding: 5px;
  background: #ffffff;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  border: #7f7f7f solid 1px;
}

.question:after {
  content: "";
  position: absolute;
  border-style: solid;
  border-width: 15px 19px 15px 0;
  border-color: transparent #ffffff;
  display: block;
  width: 0;
  z-index: 1;
  left: -19px;
  top: 27px;
}

.question:before {
  content: "";
  position: absolute;
  border-style: solid;
  border-width: 15px 19px 15px 0;
  border-color: transparent #7f7f7f;
  display: block;
  width: 0;
  z-index: 0;
  left: -20px;
  top: 27px;
}
.answer {
  position: relative;
  background: #e7fdff;
  border-radius: 0.4em;
  right: 5%;
}

.answer:after {
  content: "";
  position: absolute;
  right: 0;
  top: 50%;
  width: 0;
  height: 0;
  border: 1em solid transparent;
  border-left-color: #e7fdff;
  border-right: 0;
  border-bottom: 0;
  margin-top: -0.5em;
  margin-right: -1em;
}
.answer:hover {
  border: 2px solid tomato;
  transition: 0.2s;
  cursor: pointer;
}
.alternativesWrapper {
  /* 답변목록탭 */
  background: thistle;
  width: 100%;
  height: 25%;
  /* 정렬방식 */
  justify-content: flex-start;
  align-items: flex-start;
  flex-direction: column;
  flex-wrap: wrap;
  overflow: auto;
}
.alternative {
  font-size: 18px;
  background: lightgray;
  display: inline-block;
}
.filterWrapper {
  /* 검색탭 */
  background: rgb(255, 232, 228);
  width: 100%;
  height: 10%;
  display: flex;
  /* 정렬방식 */
  align-items: center;
}
.foodListWrapper {
  /* 음식목록탭 */
  background: rgb(203, 255, 192);
  width: 100%;
  height: 75%;
  display: flex;
  /* 정렬방식 */
  flex-direction: column;
  overflow-y: scroll;
}
.choicesWrapper {
  /* 버튼탭 */
  background: rgb(255, 227, 175);
  width: 100%;
  height: 10%;
  display: flex;
  /* 정렬방식 */
  justify-content: space-evenly;
  align-items: center;
}
.noMoreAkinator {
  display: flex;
  background: rgb(185, 164, 160);
  height: 5%;
  align-items: center;
}

.foodListContainer {
  background: tomato;
  display: table-column;
}
/* 스크롤바 */
.foodListWrapper::-webkit-scrollbar {
  width: 1rem;
}
.foodListWrapper::-webkit-scrollbar-thumb {
  background-color: #a9c4ff;
  border-radius: 10px;
}
.foodListWrapper::-webkit-scrollbar-thumb:hover {
  background-color: #5c79f8;
}
.foodListWrapper::-webkit-scrollbar-track {
  background-color: rgb(221, 221, 221);
  border-radius: 10px;
}
</style>
