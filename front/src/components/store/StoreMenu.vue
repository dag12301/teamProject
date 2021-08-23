<template>
  <div id="wrapper" class="mt-2 mb-2 p-2 container-fluid">
    <div class="row">
      <div class="col-sm-4">
        <img :src="foodInfo.filename" class="image d-block" />
      </div>
      <div class="col-sm-8">
        <span class="foodname">{{ foodInfo.foodname }} </span>
        <span class="badge bg-success m-1" v-if="foodInfo.status == 1"
          >판매중</span
        >
        <span class="badge bg-danger m-1" v-if="foodInfo.status == 2"
          >품절</span
        >
        <span class="badge bg-secondary m-1" v-if="foodInfo.status == 3"
          >판매중단</span
        >
        <span class="badge bg-warning m-1" v-if="isNew">New</span>
        <p class="price">{{ foodInfo.price }}</p>
        <div class="d-block">
          <div class="multiline-ellipsis">{{ foodInfo.description }}</div>
        </div>
        <!--  가격빼고 수정, 등록취소버튼? -->
        <div class="buttonWrapper mt-3 row">
          <div class="col-3"></div>
          <select
            name="foodStatus"
            id="foodStatus"
            class="form-select form-select-sm d-inline col"
          >
            <option value="1" selected>판매중</option>
            <option value="2">품절</option>
            <option value="3">판매중지</option>
          </select>
          <div class="col-1"></div>
          <div class="btn btn-primary d-inline m-1 col">수정</div>
          <div class="btn btn-danger d-inline m-1 col">삭제</div>
          <div class="col-1"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["foodInfo"],
  computed: {
    isNew() {
      return new Date().getDate() - this.foodInfo.regDate[2] < 3 ? true : false;
    },
  },
  methods: {
    check() {
      console.log();
    },
  },
};
</script>

<style scoped>
#wrapper {
  border-radius: 10px;
  border: 2px solid grey;
}
.image {
  width: 180px;
  height: 180px;
  border-radius: 10px;
}
.foodname {
  font-size: 25px;
  font-weight: bold;
}
.price {
  font-size: 14px;
  font-weight: lighter;
}
.multiline-ellipsis {
  width: 360px;
  white-space: normal;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
  font-size: 15px;
  color: grey;
  height: 68px;
}
.buttonWrapper {
  position: inherit;
}
</style>
