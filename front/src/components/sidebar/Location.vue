<template>
  <div
    @click="refreshLocation"
    class="link"
    :class="{ isCollapsed: !collapsed }"
  >
    <i class="icon" :class="icon" />
    <!-- 트랜지션 태그는 vuejs에서 제공하는 태그로, DOM이 조작될때 CSS 애니메이션을 넣을 수 있다. -->
    <transition name="fade">
      <span v-if="!collapsed && local" class="localText">
        {{ local.address_name }}
      </span>
    </transition>
  </div>
</template>

<script>
import { collapsed } from "./state";

export default {
  data() {
    return {
      latitude: null,
      longitude: null,
      local: null,
    };
  },
  props: {
    icon: { type: String, required: true },
  },
  setup() {
    return { collapsed };
  },
  mounted() {
    // 다 전역변수로 옮기고, 메인화면과 사이드바 동기화
  },
  methods: {
    refreshLocation() {
      return navigator.geolocation.getCurrentPosition((position) => {
        this.setLocation(position.coords.latitude, position.coords.longitude);
      });
    },
    setLocation(latitude, longitude) {
      this.latitude = latitude;
      this.longitude = longitude;
      let geocoder = new kakao.maps.services.Geocoder();

      let coord = new kakao.maps.LatLng(latitude, longitude);

      const addressSearch = (coord) => {
        return new Promise((resolve, reject) => {
          geocoder.coord2Address(
            coord.getLng(),
            coord.getLat(),
            function (result, status) {
              if (status === kakao.maps.services.Status.OK) {
                resolve(result);
              } else {
                reject(status);
              }
            }
          );
        });
      };
      const setLocal = (result) => {
        // vuex 전역변수로 뿌리고 메인화면수정
        this.local = result[0].address;
      };

      // async
      (async () => {
        try {
          const result = await addressSearch(coord);
          setLocal(result);
        } catch (e) {
          console.log(e);
        }
      })();
    },
  },
};
</script>

<style scoped>
.isCollapsed {
  left: 10%;
  background-color: tomato;
}
.link {
  display: flex;
  align-items: center;

  cursor: pointer;
  position: relative;
  font-weight: 400;
  user-select: none;
  width: 80%;
  margin: 0.2em 0;
  padding: 0.8em;
  border-radius: 0.25em;
  height: 2rem;
  color: white;
  text-decoration: none;
  font-size: 12px;
}

.link:hover {
  background-color: var(--sidebar-item-hover);
}

.link .icon {
  flex-shrink: 0;
  width: 25px;
  margin-right: 10px;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.1s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.localText {
  white-space: normal;
  display: -webkit-box;
  overflow: hidden;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
</style>
