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
      <div class="btn btn-primary" @click="check">확인</div>
    </template>
    <template v-slot:footer>
      <div class="btn btn-success" @click="addToCart(data.foodId)">
        주문표에 추가하기
      </div>
      <div class="btn btn-danger" @click="SET_MODAL_ORDER(false)">취소</div>
    </template>
  </Modal>
</template>

<script>
import Modal from "./Modal_order.vue";
import { mapMutations, mapGetters } from "vuex";

export default {
  props: ["data"],
  components: {
    Modal,
  },
  computed: {
    ...mapGetters({ checkCart: "checkCart" }),
  },
  methods: {
    ...mapMutations({
      SET_MODAL_ORDER: "SET_MODAL_ORDER",
      addCart: "addCart",
    }),
    check() {
      console.log(this.checkCart);
    },
    addToCart(foodId) {
      this.addCart(foodId);
      this.SET_MODAL_ORDER(false);
    },
  },
};
</script>

<style scoped></style>
