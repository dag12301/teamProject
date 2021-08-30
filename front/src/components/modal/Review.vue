<template>
  <Modal>
    <template v-slot:cancel>
      <div style="width: 100%; text-align: right; padding: 10px;" @click="SET_MODAL_REVIEW(false)">
        <i class="far fa-window-close fa-2x"></i>
      </div>
    </template>
    <template v-slot:header > 
      <div style="width: 100%; text-align: center; font-size: 20px;"></div>
    </template>
    <template v-slot:body>
      <!-- alt 에 이미지 없을때 비상용 이미지 추가? -->
      <div>
        별점
      </div>
      <div>
        <textarea class="form-control" rows="3" placeholder="음식에 대한 솔직한 리뷰를 남겨주세요"></textarea>
      </div>
      <div>
        <div class="review-camera" @click="imageAddUpload"><i class="fas fa-camera" ></i><input type="file" id="file" ref="files" @change="imageAddUpload" multiple /></div>
      </div>

    </template>
    <template v-slot:footer>
      <div class="btn btn-success">
        리뷰 등록하기
      </div>
    </template>
  </Modal>
</template>

<script>
import Modal from "./Modal_review.vue";
import { mapMutations, mapGetters } from "vuex";

export default {
  props: ["data"],
  components: {
    Modal,
  },
  data() {
    return {
      counter: 1,
      files: [],
    };
  },
  mounted() {},
  computed: {
    ...mapGetters({ checkCart: "checkCart" }),
  },
  methods: {
    ...mapMutations({
      SET_MODAL_REVIEW: "SET_MODAL_REVIEW",
      addCart: "addCart",
    }),
    imageAddUpload() {
      this.files.push (
          //이미지 업로드
          {   //실제 파일
              file: this.$refs.files.files[0],
              //이미지 프리뷰
              name: URL.createObjectURL(this.$refs.files.files[0]),
              //삭제및 관리를 위한 number
          }
      )
    },
  },
};
</script>

<style scoped>
  .form-control {
    width: 95%;
  }
  .review-camera {
    margin-top: 5px;
    width: 60px;
    line-height: 60px;
    border: 1px solid lightgray;
  }
    .fa-camera {
      width: 23px;
      height: 23px;
    }
</style>
