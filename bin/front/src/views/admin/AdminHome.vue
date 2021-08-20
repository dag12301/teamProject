<template>
  <div>
    <h1>가게관리페이지</h1>
    <!-- 노티피케이션 -->
    <notifications
      group="notifyApp"
      position="bottom right"
      style="margin-right: 30vh"
    />
    <!-- 테이블시작 -->
    <div id="wrapper" class="container-fluid mw-100">
      <table class="table table-bordered align-self-center table-striped">
        <thead>
          <tr class="bg-secondary">
            <th scope="col fw-bold">#</th>
            <th scope="col fw-bold">등록일</th>
            <th scope="col fw-bold">가게이름</th>
            <th scope="col fw-bold">상태</th>
            <th scope="col fw-bold">주소</th>
            <th scope="col fw-bold">신청자</th>
            <!-- 클릭시 자세하게 볼 수 있도록 -->
          </tr>
        </thead>
        <!-- 테이블 내용 -->
        <tbody>
          <tr v-if="totalCount === 0">
            <td class="table-danger" colspan="6">등록된 가게가 없습니다.</td>
          </tr>
          <tr v-for="(list, index) in storeList" :key="index">
            <td class="table-light">{{ startListNum + index + 1 }}</td>
            <td class="table-light text-wrap fw-light" style="width: 6rem">
              {{
                list.regDate[2] +
                "일 " +
                list.regDate[3] +
                ":" +
                list.regDate[4]
              }}
            </td>
            <td class="table-light">{{ list.storename }}</td>
            <td class="table-success" v-if="list.status == 'Y'">
              {{ list.status }}
            </td>
            <td class="table-warning" v-else-if="list.status == 'S'">
              {{ list.status }}
            </td>
            <td class="table-danger" v-else-if="list.status == 'N'">
              {{ list.status }}
            </td>
            <td class="table-dark" v-else>?</td>
            <td class="table-light">{{ list.address }}</td>
            <td class="table-light">{{ list.membername }}</td>
          </tr>
        </tbody>
      </table>
      <!-- 페이지네이션 -->
      <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
          <li class="page-item" :class="{ disabled: !hasPreviousPage }">
            <a
              class="page-link"
              aria-disabled="true"
              @click="requestPage(firstPageOfthisIndex - 1)"
              :class="{ 'available-link': hasPreviousPage }"
              >이전목록</a
            >
            <!-- 현재 단락의 가장 첫번째 페이지 -1을 요청해야함. -->
          </li>
          <!-- 페이지순번 -->
          <div v-for="index in maxIndex" :key="index">
            <div v-if="index + (currentIndex - 1) * maxIndex <= totalPages">
              <li class="page-item" v-if="index != currentPage">
                <a
                  class="page-link available-link"
                  @click="requestPage(index)"
                  >{{ index + (currentIndex - 1) * maxIndex }}</a
                >
              </li>
              <li class="page-item active" v-else>
                <span class="page-link">{{ index }}</span>
              </li>
            </div>
          </div>

          <li class="page-item" :class="{ disabled: !hasNextPage }">
            <a
              class="page-link"
              @click="requestPage(lastPageOfthisIndex + 1)"
              :class="{ 'available-link': hasNextPage }"
              >다음목록</a
            >
          </li>
        </ul>
      </nav>
      현재페이지 : {{ currentPage }} <br />현재 단락 : {{ currentIndex }}
      <br />최대 단락 :
      {{ totalIndex }}
    </div>
  </div>
</template>

<script>
import { normal, error, success } from "@/api/notification";
import axios from "axios";
import JWT from "@/api/jwt";

export default {
  data() {
    return {
      storeList: [],
      currentPage: 1, // 현재 페이지
      listPerPage: 2, // 한번에 보여줄 리스트숫자
      totalCount: 0, // 총 게시글 수
      showindex: 3, // 번호로 표시될 페이지 총 갯수
    };
  },
  computed: {
    startListNum() {
      return (this.currentPage - 1) * this.listPerPage;
    },
    hasNextPage() {
      return this.currentIndex < this.totalIndex;
    },
    hasPreviousPage() {
      return this.currentIndex > 1;
    },
    totalPages() {
      let remains = this.totalCount % this.listPerPage;
      // 나머지가 있으면 1을 더하고, 0으로 떨어지면 그냥 준다.
      if (remains == 0) {
        return parseInt(this.totalCount / this.listPerPage);
      }
      return parseInt(this.totalCount / this.listPerPage) + 1;
    },
    maxIndex() {
      // 최대 표시 번호 수
      if (this.totalPages < this.showindex) {
        return this.totalPages;
      }
      return this.showindex;
    },
    currentIndex() {
      // 현재있는 단락
      return this.currentPage % this.maxIndex == 0
        ? parseInt(this.currentPage / this.maxIndex)
        : parseInt(this.currentPage / this.maxIndex) + 1;
    },
    totalIndex() {
      // 최대 단락
      return this.totalPages % this.maxIndex == 0
        ? parseInt(this.totalPages / this.maxIndex)
        : parseInt(this.totalPages / this.maxIndex) + 1;
    },
    firstPageOfthisIndex() {
      return (this.currentIndex - 1) * this.maxIndex + 1;
    },
    lastPageOfthisIndex() {
      return this.currentIndex * this.maxIndex;
    },
  },
  mounted() {
    console.log("관리자로 로그인하였습니다.");
    normal("정보를 불러옵니다. 잠시만 기다려주세요..", this);
    // 사용자 검증을 위해 토큰정보를 싣는다.
    const token = JWT.getToken();
    const headers = {
      "content-type": "application/json",
      accesstoken: token,
    };
    // 가게 갯수를 확인한다.
    this.requestListCount();
    // 정보를 호출한다.
    this.requestPage(1);
  },
  methods: {
    requestPage(request) {
      console.log("요청페이지 : " + request);
      const data = {
        listPerPage: this.listPerPage,
        currentPage: request,
      };
      axios
        .post("http://localhost:8083/admin/getStoreList", data)
        .then((response) => {
          if (response.status === 200) {
            console.log(response.data);
            success("데이터 로딩 완료!", this);
            this.storeList = response.data;
            this.currentPage = request;
            console.log("현재페이지 : " + this.currentPage);
          }
        })
        .catch((error) => {
          console.log(error);
          error("오류가 발생했습니다. 다시 시도해주세요", this);
        });
    },
    requestListCount() {
      axios
        .get("http://localhost:8083/admin/getStoreListCount")
        .then((response) => {
          this.totalCount = response.data;
          console.log("등록된 가게의 총 갯수 : " + this.totalCount);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
* {
  align-content: center;
}
.available-link {
  cursor: pointer;
}
</style>
