<template>
<div class="food-list">
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">전체보기</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">1인분 주문</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">프랜차이즈</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">치킨</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">피자/양식</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">중국집</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">한식</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">일식/돈까스</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">족발/보쌈</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">야식</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">분식</p>
            </div>
        </a>
    </div>
    <div class="food-div">
        <a class="food-a" href="#">
            <div class="food-title">
                <p class="food-p">카페/디저트</p>
            </div>
        </a>
    </div>
</div>
</template>

<style>
    .food-list {
        padding: 5px;
        max-width: 1020px;
        margin: 0 auto;
        background-color: red;
    }
    .food-div{
		color: black;
		text-decoration: none;
		background-color: #f44366;
		padding: 15px; /* 패딩: 안쪽여백, 마진: 밖여백 */
		
		/* 글자가 깨졌으니 밑의 display 추가하자 */
		display: inline-block; /* 인라인모드/블럭모드 이해 필요  */
		
		width: 200px;
        height: 200px;
		
		margin: 5px;
        text-align: left;
	} 
    .food-a {
        position: relative;
        padding: 0;
        margin: 5px 0;
        text-decoration: none;
    }
    .food-title {
        width: 100%;
        color: #333;
        font-size: 18px;
        font-weight: bold;
    }
    .food-p {
        text-align: left;
        margin-left: 8px;
}
</style>