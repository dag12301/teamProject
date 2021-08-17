<template>
  <div class="container">
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <input
        type="text"
        name="title"
        v-model="data.board.title"
        id="title"
        placeholder="입력해주세요."
      />
    </div>
    <div class="mb-3">
      <label for="title" class="form-label">내용</label>
      <textarea
        class="form-control"
        name="contents"
        v-model="data.board.contents"
        id="contents"
        placeholder="입력해주세요."
      />
    </div>
    <div class="mb-3">
      <label for="uploadFile" class="form-label">파일첨부</label>
      <input
        type="file"
        name="uploadFile"
        class="form-control"
        id="uploadFile"
        @change="changeFile"
        multiple
      />
    </div>
    <div class="d-gird gap-2 d-md-flex justify-content-md end">
      <button class="btn btn-primary me-md-2" @click="save">저장</button>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive } from "vue";

export default {
  name: "BoardForm",
  components: {},
  setup() {
    // 기본 데이터셋 초기화
    const data = reactive({
      board: {
        title: null,
        contents: null,
        uploadFiles: null,
        boardList: null,
      },
    });
    // 이벤트가 발생할때마다 타겟의 0번째 파일값을 uploadFile에 대입함.
    const changeFile = (e) => {
      console.log(e.target.files);
      data.board.uploadFiles = e.target.files;
    };
    // 새로운 이벤트
    const save = () => {
      let formData = new FormData(); // 멀티파츠를 보낼려면 이걸써야함.
      // valid 체크 해줘야하는 부분들, 지금은 file만 체크함.
      formData.append("title", data.board.title);
      formData.append("contents", data.board.contents);
      for (let i = 0; i < data.board.uploadFiles.length; i++) {
        formData.append("fileList", data.board.uploadFiles[i]);
      }
      fetch("http://localhost:8083/store/addstore", {
        method: "Put",
        body: formData,
      }).then((response) => console.log(response));
    };
    // 초기화될때 이벤트
    onMounted(() => {});
    // 연결시켜주기
    return {
      data: data,
      changeFile: changeFile,
      save: save,
    };
  },
};
</script>

<style></style>
