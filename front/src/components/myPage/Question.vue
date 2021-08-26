<template>
<div class="row">
  <div class="col Q">
    <div class="Q-title">
      <h4>제목</h4>
      <div>
        <router-link class="Review-link" to="/qna">
          <button type="button" class="btn btn-primary" style="margin-right:5px;">가기</button>
        </router-link>
        <button type="button" @Click="QDel()" class="btn btn-danger">삭제</button>
      </div>
    </div>
    <div class="Q-text">
      ㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴㅂㅈㄷㅂㅈㄷㅇㅁㄴㅇㄴ
    </div>
  </div>
</div>
<div class="row">
  <div class="col Q">
    <div class="Q-title">
      <h4>제목</h4>
      <div>
        <router-link class="Review-link" to="/qna">
          <button type="button" class="btn btn-primary" style="margin-right:5px;">가기</button>
        </router-link>        <button type="button" @Click="QDel()" class="btn btn-danger">삭제</button>
      </div>
    </div>
    <div class="Q-text">
      가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하
    </div>
  </div>
</div>
<div class="row">
  <div class="col Q">
    <div class="Q-title">
      <h4>제목</h4>
      <div>
        <router-link class="Review-link" to="/qna">
          <button type="button" class="btn btn-primary" style="margin-right:5px;">가기</button>
        </router-link>
        <button type="button" @Click="QDel()" class="btn btn-danger">삭제</button>
      </div>
    </div>
    <div class="Q-text">
      가나다라마바사아자차카타파하
    </div>
  </div>
</div>
</template>

<script>

export default {
  methods: {
    QDel() {
      alert('삭제되었습니다!')
    }
  }
}
</script>

<style>
.Q {
  border-bottom: 2px solid black;
}
.Q-title {
  border-bottom: 1px solid black;
  padding: 10px;
  display: flex; 
  flex-direction: row ; 
  justify-content: space-between;
  align-items: center;
}
.Q-text {
  padding: 10px;
  text-align: start;
  white-space: normal;
  display:-webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
  height: 80px;
}
</style>
