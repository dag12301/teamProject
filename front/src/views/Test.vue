<template>
  <div class="container">
    <input type="text" readonly v-model="result" /> 주소
    <div
      class="btn btn-primary"
      @click="SET_MODAL_MAP(true)"
      @close="SET_MODAL_MAP(false)"
    >
      지도검색
    </div>
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Id perspiciatis
    facilis quia consequuntur ullam, iure quaerat sequi quae nesciunt maxime
    natus blanditiis atque deserunt! Adipisci earum est impedit facilis eos.
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Ducimus dignissimos
    magni nulla quia! Ducimus expedita ratione, vitae adipisci, cum voluptas
    architecto, eos accusantium maxime laborum optio recusandae maiores nemo
    libero! Lorem ipsum dolor sit amet consectetur, adipisicing elit. Maiores,
    asperiores vel qui laborum suscipit cupiditate deleniti deserunt ea corrupti
    molestiae sint hic, itaque illum magni quam sequi! Ipsam, illo minima. Lorem
    ipsum dolor sit amet consectetur adipisicing elit. Adipisci porro ipsum
    excepturi perferendis non ad rerum optio consequuntur, assumenda omnis nam
    qui quas explicabo laborum voluptatem quaerat totam reprehenderit magnam!
    Lorem, ipsum dolor sit amet consectetur adipisicing elit. Velit, quos libero
    explicabo perferendis illo aliquam at ipsam doloribus porro dolorum, odit
    dolorem totam. Possimus est libero obcaecati accusantium! Cumque, itaque?
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsam ad adipisci
    voluptatum saepe eaque numquam quod magni exercitationem libero temporibus.
    Dolores nisi earum soluta consectetur assumenda, amet architecto accusamus
    id?
    <Map v-if="mapModal"></Map>
  </div>
</template>

<script>
import Map from "@/components/modal/Map.vue";
import { mapState, mapMutations } from "vuex";

export default {
  name: "Test",
  components: {
    Map,
  },
  data() {
    return {
      result: "결과",
    };
  },
  computed: {
    ...mapState(["mapModal"]),
  },
  methods: {
    ...mapMutations(["SET_MODAL_MAP"]),
  },
};
</script>

<style scoped></style>
