<template>
  <Modal>
    <template v-slot:header> {{ data.name }} </template>
    <template v-slot:body>
      <!-- alt 에 이미지 없을때 비상용 이미지 추가? -->
      <div>
        <img :src="data.fileUrl" alt="" style="width: 80px" />
      </div>
      {{ data.price }} 원<br />
      {{ data.description }}
      수량:
    </template>
    <template v-slot:footer>
      <div class="btn btn-success">장바구니</div>
      <div class="btn btn-danger" @click="SET_MODAL_ORDER(false)">취소</div>
    </template>
  </Modal>
</template>

<script>
import Modal from "./Modal_order.vue";
import { mapMutations } from "vuex";

export default {
  props: ["data"],
  components: {
    Modal,
  },
  mounted() {},
  methods: {
    ...mapMutations(["SET_MODAL_ORDER"]),
  },
};
</script>

<style scoped></style>
