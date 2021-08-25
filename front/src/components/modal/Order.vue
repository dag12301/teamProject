<template>
  <Modal>
    <template v-slot:header> {{ data.name }} </template>
    <template v-slot:body>
      <!-- alt 에 이미지 없을때 비상용 이미지 추가? -->
      <div>
        <img :src="data.fileUrl" alt="" style="width: 80px" />
      </div>
      <span style="font-size: 20px;">{{ data.price }} 원</span><br />
      {{ data.description }}
      <hr style="margin-bottom: 0px;"/> 
      <div style="width: 100%; margin-bottom: 10px;">
        <div style="float: left; margin-top: 10px;">
          <span style="left: 0; font-size: 18px;">수량 &nbsp;&nbsp; {{ counter }}</span>
        </div>
        <div style="float: right;">
          <a v-if="counter !== 1" v-on:click="counter -= 1" style="font-size: 30px; margin-right: 20px;">-</a>
          <a  v-on:click="counter += 1" style="font-size: 30px;">+</a>
        </div>
      </div >
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
  data() {
    return {
      counter: 1
    };
  },
  mounted() {},
  methods: {
    ...mapMutations(["SET_MODAL_ORDER"]),
  },
};
</script>

<style scoped></style>
