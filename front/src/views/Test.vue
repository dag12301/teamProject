<template>
  <div style="border: solid; width: 30%" class="container">
    <h2 style="text-align: center">음식등록</h2>
    <br /><br />
    <form class="validation-form" action="" method="POST">
      <div style="text-align: -webkit-center">
        <div style="width: 80%" class="row">
          <div class="col-md-6 mb-3">
            <label for="storeName">상호명</label>
            <input
              type="text"
              class="form-control"
              id="storeName"
              placeholder="상호명을 입력하세요."
              value=""
              required
            />
          </div>
          <div class="col-md-6 mb-3">
            <label for="onner">성함</label>
            <input
              type="text"
              class="form-control"
              id="onner"
              placeholder="성함을 입력하세요."
              value=""
              required
            />
          </div>
        </div>
        <div class="col-md-12 mb-3">
          <label for="" required>업종카테고리</label>
          <div class="margin-bottom: inherit;">
            <div
              class="form-check form-check-inline"
              v-for="item in category"
              :key="item"
            >
              <input
                class="form-check-input"
                type="radio"
                name="reg"
                @click="selectCategory(item)"
              />{{ item }}
            </div>
          </div>
        </div>

        <div class="input-group">
          <label class="input-group-text" required>등록음식</label>
          <select style="flex: auto" name="kor">
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
          <input
            type="text"
            class="form-control"
            id="price"
            placeholder="음식의 가격을 입력하세요"
            required
          />
        </div>
        <div class="input-group">
          <label class="input-group-text" for="price">음식사진</label>
          <!-- 이미지 -->
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
      selectedCategory: "",
      category: ["한식", "일식", "중식", "아시아", "양식"],
      foodOptions: {
        한식: ["삼겹살", "김치찌개"],
        일식: ["초밥", "우동"],
        중식: ["짜장면", "짬뽕"],
        아시아: ["케밥", "쌀국수"],
        양식: ["피자", "햄버거"],
      },
    };
  },
  methods: {
    selectCategory(Value) {
      this.selectedCategory = Value;
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
#imging {
  width: 250px;
}
</style>
