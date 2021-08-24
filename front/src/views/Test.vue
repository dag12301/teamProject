<template>
  <div>
    <div class="btn btn-primary" @click="test">버튼</div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["GET_LOCAL"]),
  },
  methods: {
    test() {
      console.log(this.GET_LOCAL);
    },
  },
};
</script>

<style></style>
