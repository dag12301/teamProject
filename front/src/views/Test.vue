<template>
  <div>
    <div class="btn btn-primary" @click="toggleModal">눌러봐</div>
    <MyModal v-if="isModalOpen" @close="toggleModal()">
      <template v-slot:header
        ><div class="btn btn-primary">부트스트랩</div></template
      >
      <template v-slot:body> 회원정보 내역 </template>
      <template v-slot:footer> 내 페이지로 가기? </template>
    </MyModal>
  </div>
</template>

<script>
import MyModal from "@/components/MyModal.vue";

export default {
  data() {
    return {
      isModalOpen: false,
    };
  },
  methods: {
    toggleModal() {
      this.isModalOpen = !this.isModalOpen;
    },
  },
  components: {
    MyModal,
  },
};
</script>

<style></style>
