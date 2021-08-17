<template>
  <div class="service-center">
    <div class="service-group mb-5">
      <router-link
        class="btn btn-success me-5 col-2"
        :to="{ name: 'Notice' }"
        @click="getCountNotice(1)"
        >공지사항</router-link
      >

      <router-link
        class="btn btn-success mx-5 col-2"
        :to="{ name: 'Q&A' }"
        @click.prevent="getCountQueAn(2)"
        >Q&A</router-link
      >

      <router-link
        class="btn btn-success ms-5 col-2"
        :to="{ name: 'FQA' }"
        @click="getCountFQA(3)"
        >FQA</router-link
      >

      <router-link
        class="btn btn-success ms-5 col-2"
        :to="{ name: 'WriteForm' }"
        @click="getCountFQA(3)"
        >WriteForm</router-link
      >
    </div>

    <router-view></router-view>
  </div>
</template>

<script>
import * as authAPI from "@/api/auth";
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      data: [{ boardId: 1 }, { boardId: 2 }, { boardId: 3 }],
      boardId: 1,
    };
  },
  computed: {},
  methods: {
    ...mapMutations([
      "serviceCenter/setCenterList",
      "SET_COUNT_NOTICE",
      "SET_COUNT_QUEAN",
      "SET_COUNT_FQA",
    ]),
    getCountNotice(boardId) {
      // notice 통신
      if (this.$store.state.countNotice) {
        return this.downAllList(boardId), this.SET_COUNT_NOTICE(false);
      } else {
        return console.log("else 일때" + this.$store.state.countNotice);
      }
    },
    getCountQueAn(boardId) {
      // Q&A 통신
      if (this.$store.state.countQueAn) {
        console.log("if 일때" + this.$store.state.countQueAn);
        return this.downAllList(boardId), this.SET_COUNT_QUEAN(false);
      } else {
        return console.log("Q&A");
      }
    },
    getCountFQA(boardId) {
      // FAQ 통신
      if (this.$store.state.countFAQ) {
        return this.downAllList(boardId), this.SET_COUNT_FQA(false);
      } else {
        return console.log("FAQ");
      }
    },

    downAllList(query) {
      //리스트 axios
      authAPI
        .allList(query)
        .then((res) => {
          res.data.forEach((el) => {
            if (query == 1) {
              this.$store.commit("serviceCenter/setCenternotices", el);
            } else if (query == 2) {
              this.$store.commit("serviceCenter/setCenterQueAn", el);
            } else if (query == 3) {
              this.$store.commit("serviceCenter/setCenterFQA", el);
            }
          });
        })
        .catch((err) => {
          console.log(err);
        })
        .then(() => {
          console.log("test");
        });
    },
    test() {
      authAPI
        .test()
        .then((res) => {
          console.log(res, "성공");
        })
        .catch((err) => {
          console.log(err);
        })
        .then(() => {
          console.log("test");
        });
    },
  },
};
</script>

<style scoped>
/* .service-group button{
    height: 4vh;
    width: 10vw;
  } */
</style>
