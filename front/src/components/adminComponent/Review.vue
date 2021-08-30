<template>
  <div class="review">
    <div class="review-left">
      <span style="font-weight: 600; font-size: 35px;">4.9</span><span style="color: lightgray; font-size: 28px;"> / 5</span>
      <div style="font-size: 13px; margin-top: 0px">
        <i class="fas fa-star"></i><i class="fas fa-star"></i
        ><i class="fas fa-star"></i><i class="fas fa-star"></i
        ><i class="fas fa-star"></i>
      </div>
    </div>
  </div>
  <div class="review2">
    <div style="float: left; width: 70%;">
      <p class="review2-p">리뷰 <span style="font-weight: 600;">100</span>개 · 사장님 댓글 <span style="font-weight: 600;">48</span>개</p>
    </div>
    <div style="float: right; width: 20%; margin-top: 5px; margin-right: 20px;" @click="popReviewModal(food)">
      <div class="review2-div" style="height: 40px;">
        <div>
          <i class="fas fa-pencil-alt"></i>
          <span style="margin-left: 10px;">리뷰쓰기</span>
        </div>
      </div>
    </div>
  </div>
  <div class="review3">
    <div style="width: 100%;">
      <div class="review3-text">
        <span class="review3-span1">소희님</span>
        <span class="review3-span2">8시간 전</span>
      </div>
      <div class="review3-div">
        <a href="#" class="review3-a" >신고</a>
      </div>
    </div>
    <div class="review3-star" style="width: 200px;">
      <div class="star-1">
        <i class="fas fa-star"></i><i class="fas fa-star"></i
        ><i class="fas fa-star"></i><i class="fas fa-star"></i
        ><i class="fas fa-star"></i>&nbsp;&nbsp;<span>5.0</span>
      </div>
    </div>
    <div>
      <div
        class="review3-img"
        style="
          width: 200px;
          height: 200px;
          border: 1px solid red;
          margin-left: 10px;
        "
      >
        이미지 부분
      </div>
    </div>
    <div class="review3-menu">
      <p>메뉴 뭐시켰는지 시킨음식</p>
    </div>
    <div class="review3-view">
      <p>
        칼칼한 라멘국물은 처음 먹어 봤습니다 칼칼한 것도 맛있더군요 덮밥은 뭔가
        양념이 덜된것 같았는데 다 먹어갈때 쯤 밥 소스를 돈까스 밥이 아니라
        여기에 붓는 거란걸 알았습니다. 나중에 다시 한번 시켜먹겠습니다
      </p>
    </div>
  </div>
  <div class="review3">
    <div style="width: 100%;">
      <div class="review3-text">
        <span class="review3-span1">소희님</span>
        <span class="review3-span2">8시간 전</span>
      </div>
      <div class="review3-div">
        <a href="#" class="review3-a" >신고</a>
      </div>
    </div>
    <div class="review3-star" style="width: 200px;">
      <div class="star-1">
        <i class="fas fa-star"></i><i class="fas fa-star"></i
        ><i class="fas fa-star"></i><i class="fas fa-star"></i
        ><i class="fas fa-star"></i>&nbsp;&nbsp;<span>4.8</span>
      </div>
    </div>
    
    <div class="review3-menu">
      <p>미소라멘 X 1개, 가라아게 X 1개</p>
    </div>
    <div class="review3-view">
      <p>
        칼칼한 라멘국물은 처음 먹어 봤습니다 칼칼한 것도 맛있더군요 덮밥은 뭔가
        양념이 덜된것 같았는데 다 먹어갈때 쯤 밥 소스를 돈까스 밥이 아니라
        여기에 붓는 거란걸 알았습니다. 나중에 다시 한번 시켜먹겠습니다
      </p>
    </div>
  </div>
  <review-modal
    :data="reviewModalData"
    v-if="this.$store.state.reviewModal == true"
  ></review-modal>
</template>

<script>
import ReviewModal from "@/components/modal/Review.vue";

import { mapMutations } from "vuex";
export default {
  props: ["foodList"],
  components: {
    ReviewModal,
  },
  data() {
    return {
      reviewModalData: null,
    };
  },
  methods: {
    ...mapMutations(["SET_MODAL_REVIEW"]),
    popReviewModal(food) {
      this.reviewModalData = food;
      this.SET_MODAL_REVIEW(true);
    },
  },
};
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}
.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}



.review {
  width: 100%;
  border: 1px solid lightgray;
}
.review-left {
  font-size: 40px;
}

.review-2 {
  margin-left: 10px;
}

.review2 {
  width: 100%;
  text-align: left;
  background-color: lightgray;
  height: 50px;
  line-height: 50px;
  padding-left: 20px;
}
.review2-p {
  line-height: middle;
  
}

.review3 {
  width: 100%;
  border: 1px solid lightgray;
  text-align: left;
  padding: 15px;
  padding-left: 20px;
}
.review3-text {
  float: left;
  width: 80%;
}
.review3-span1 {
  font-size: 20px;
  font-weight: bold;
}
.review3-span2 {
  color: lightgray;
  margin-left: 10px;

}
.review3-a {
  text-decoration: none;
  color: gray;
  font-weight: bold;
}

.review3-img {
  width: 100%;
  background-color: yellow;
  text-align: center;
}
#img {
  width: 100%;
}

.review3-menu {
  color: burlywood;
  font-size: 14px;
}

.fa-star {
  color: burlywood;
}


.review3-div {
  text-align: right;
  padding-right: 40px;
  float: right;
  width: 20%;
}


.review2-div {
  border: 2px solid rgb(170, 170, 170);
  background-color: #FAFAFA;
  text-align: center;
  border-radius: 25px;
}
</style>
