<template>
  <div style="border: solid; width: 50%" class="container">
    <h2 style="text-align: center">음식등록</h2>
    <br /><br />
    <form class="validation-form" action="" method="POST">
      <div style="text-align: -webkit-center">
        <div style="width: 70%" class="row">
          <div class="col-md-6 mb-3">
            <label for="storeName">상호명</label>
            <input
              type="text"
              class="form-control"
              id="storeName"
              placeholder=""
              value=""
              required
            />
            <div class="invalid-feedback">상호명을 입력하세요.</div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="onner">성함</label>
            <input
              type="text"
              class="form-control"
              id="onner"
              placeholder=""
              value=""
              required
            />
            <div class="invalid-feedback">이름을 입력하세요.</div>
          </div>
        </div>
        <div class="col-md-12 mb-3">
          <label for="" required>업종카테고리</label>
          <div class="margin-bottom: inherit;">
            <div></div>
            <div
              class="form-check form-check-inline"
              v-for="(item, index) in category"
              :key="index"
            >
              <input
                class="form-check-input"
                type="radio"
                name="inlineRadioOptions"
                :id="item"
                value="option1"
                @click="selectCategory(item)"
              />
              <label class="form-check-label" :for="item">{{ item }}</label>
            </div>
          </div>
        </div>
        <div class="input-group">
          <label class="input-group-text" required>등록음식</label>
          <select style="flex: auto" name="kor" id="choice">
            <option
              v-for="(item, index) in foodOptions[selectedCategory]"
              :key="index"
              :value="item"
            >
              {{ item }}
            </option>
          </select>
        </div>
        <div class="input-group">
          <label class="input-group-text" for="price">음식가격</label>
          <input type="text" class="form-control" id="price" required />
        </div>
        <div>
          <div class="input-group">
            <label for="Explanation" class="input-group-text">메뉴설명</label>
            <textarea class="form-control" id="Explanation" rows="5"></textarea>
          </div>
        </div>
        <button id="btn" type="submit" class="btn btn-success">등록</button>
        <button id="btn" type="reset" class="btn btn-danger">취소</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedCategory: "한식",
      category: ["한식", "중식", "양식"],
      foodOptions: {
        한식: ["김치찌개", "된장찌개", "부대찌개", "비빔밥"],
        중식: ["꿔바로우", "만두"],
        양식: [],
      },
      selectedFood: "",
    };
  },
  methods: {
    selectCategory(value) {
      this.selectedCategory = value;
    },
    selectFood() {
      this.selectedFood = document.getElementById("choice").value;
    },
    whatisitem(item) {
      console.log(item);
    },
  },
};
</script>

<style>
textarea {
  resize: none;
}
#btn {
  margin: 10px;
}
.input-group {
  margin: 5px;
  width: 100%;
}
</style>
