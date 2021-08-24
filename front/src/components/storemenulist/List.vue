<template>
    <ul class="wrapper">
        <li v-for="(item, index) in list" :key="item+index">
            <div class="div-box" @click="item.open = !item.open">
            <div class="title div-left">
                <span>{{item.title}}</span>
            </div>
            <div class="div-right">
                 <i class="fas fa-sort-down" :class="{'open':item.open}"></i>
            </div>
            </div>
            <ListItem :list="item" />
        </li>
    </ul>
</template>

<script>
import ListItem from "./ListItem";

export default {
    data: () => {
        return {
            list: [
                {
                    title: "인기메뉴",
                    open: false,
                    sublist: [
                        "메뉴1", "메뉴2"
                    ]
                },
                {
                    title: "햄버거메뉴",
                    open: false,
                    sublist: [
                        "메뉴3", "메뉴4"
                    ]
                },
                {
                    title: "사이드메뉴",
                    open: false,
                    sublist: [
                        "메뉴5", "메뉴6"
                    ]
                }
            ]
        }
    },
    components: {
        ListItem
    }
}
</script>

<style scoped>
.wrapper {
    list-style: none;
    background-color: lightgray;
    width: 100%;
    height: 100%;
}

ul {
    list-style:none;
   padding-left:0px;
}
li {
    width: 100%;
    position: relative;
    cursor: pointer;
    list-style:none;
   padding-left:0px;
   font-weight: bold;
}

li .title {
    padding: 10px;
    text-indent: 20px;
    text-align: left;
}

i {
    position: absolute;
    top: 14px;
    transition: transform .5s ease-in-out;
}

i .open {
    transform: rotatez(180deg);
}

.div-box {
    width: 100%;
    height: 40px;
}

.div-left {
    float: left;
    width: 50%;
    height: 40px;
}
.div-right {
    float: right;
    width: 50%;
    height: 40px;
    padding: 10px;
    text-align: right;
    padding-right: 30px;
}


</style>
