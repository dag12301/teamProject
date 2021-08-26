<template>
  <div class="row">
    <div class="col reviewCount">
      <h4>내가 쓴 리뷰 수 : 3</h4>
    </div>
  </div>
  <div class="row profile-scroll">
    <div class="col-12">
      <div class="row">
        <div class="col-6 myReview">
          <router-link to="/food">
            <img
              class="myReview-img"
              src="https://www.newiki.net/w/images/thumb/d/d9/Jjajangmyeon.jpg/450px-Jjajangmyeon.jpg"
            />
          </router-link>
        </div>
        <div class="col-6 myReview-text">
          <div class="myReview-Contents">
            <router-link class="Review-link" to="/food"
              ><h4>짜장면집</h4></router-link
            >
            <div class="RatingStar">
              <i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i>
            </div>
            <div class="myReview-lining">여기 정말 맛있어요</div>
          </div>
          <div class="review-del">
            <button type="button" @Click="reviewDel()" class="btn btn-danger">
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="col-12">
      <div class="row">
        <div class="col-6 myReview">
          <router-link to="/food">
            <img
              class="myReview-img"
              src="https://www.newiki.net/w/images/thumb/d/d9/Jjajangmyeon.jpg/450px-Jjajangmyeon.jpg"
            />
          </router-link>
        </div>
        <div class="col-6 myReview-text">
          <div class="myReview-Contents">
            <router-link class="Review-link" to="/food"
              ><h4>짜장면집</h4></router-link
            >
            <div class="RatingStar">
              <i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i>
            </div>
            <div class="myReview-lining">여기 정말 맛있어요</div>
          </div>
          <div class="review-del">
            <button type="button" @Click="reviewDel()" class="btn btn-danger">
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="col-12">
      <div class="row">
        <div class="col-6 myReview">
          <router-link to="/food">
            <img
              class="myReview-img"
              src="https://www.newiki.net/w/images/thumb/d/d9/Jjajangmyeon.jpg/450px-Jjajangmyeon.jpg"
            />
          </router-link>
        </div>
        <div class="col-6 myReview-text">
          <div class="myReview-Contents">
            <router-link class="Review-link" to="/food"
              ><h4>짜장면집</h4></router-link
            >
            <div class="RatingStar">
              <i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i>
            </div>
            <div class="myReview-lining">여기 정말 맛있어요</div>
          </div>
          <div class="review-del">
            <button type="button" @Click="reviewDel()" class="btn btn-danger">
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    reviewDel() {
      alert("삭제되었습니다!");
    },
  },
};
</script>

<style>
.profile-scroll {
  overflow-x: hidden;
  overflow-y: auto;
  height: 600px;
}
.reviewCount {
  height: 50px;
  border-bottom: 1px solid black;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.myReview {
  height: 220px;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  border-right: 1px solid black;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.myReview-img {
  width: 250px;
  height: 200px;
}
.myReview-text {
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.myReview-Contents {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.fa-star {
  color: rgb(196, 196, 36);
}
.Review-link:link,
.Review-link:visited,
.Review-link:hover {
  text-decoration: none;
  color: black;
}
.myReview-lining {
  text-align: start;
  white-space: normal;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
