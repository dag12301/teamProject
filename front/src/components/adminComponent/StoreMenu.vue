<template>
    <div class="Menu">
        <div class="Menu-box">
            <div class="Menu-float">
                <div class="Menu-left" style="width: 90px;height: 90px; border: 1px solid red; margin-left: 10px;">이미지부분</div>
                <div class="Menu-right">
                    <p>음식이름</p>
                    <span class="food">음식 소개</span><br/>
                    <span class="price">가격</span>
                </div>
            </div>
        </div>
    </div>
    <div class="Menu">
        <div class="Menu-box">
            <div class="Menu-float">
                <div class="Menu-left" style="width: 90px;height: 90px; border: 1px solid red; margin-left: 10px;">이미지부분</div>
                <div class="Menu-right" style="text-align: right;">
                    <p>순대국</p>
                    <span class="food">담백한 육수에 푸짐한 고기와 순대가 가득한 큰맘의 대표메뉴 순대국 도시락</span><br/>
                    <span class="price">8,000원</span>
                </div>
            </div>
        </div>
    </div>
    <div class="Menu">
        <div class="Menu-box">
            <div class="Menu-float">
                <div class="Menu-left" style="text-align: left;">
                    <p>순대국</p>
                    <span class="food">담백한 육수에 푸짐한 고기와 순대가 가득한 큰맘의 대표메뉴 순대국 도시락</span><br/>
                    <span class="price">8,000원</span>
                </div>
                <div class="Menu-right" style="width: 90px;height: 90px; border: 1px solid red; margin-right: 10px;">
                    이미지
                </div>
            </div>
        </div>
    </div>
</template>



<style scoped>
.Menu {
    width: 100%;
}
.Menu-box {
    width: 100%;
    border: 1px solid black;
    height: 100%;
    padding-top: 10px;
}
.Menu-left {
    float: left;
}
.Menu-right {
    float: right;
}
.Menu-right p {
    font-size: 22px;
    font-weight: bold;
}
.Menu-left p {
    font-size: 22px;
    font-weight: bold;
}
.food {
    color: gray;
}
.price {
    font-size: 18px;
}
</style>