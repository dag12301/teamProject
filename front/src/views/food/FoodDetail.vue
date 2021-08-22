<template>
<body>
    <div style="border: solid;width: 100%;height:300%;" class="container">
    <div id="app" style="text-align:center;">
    <h1 style="text-align: center">가게이름</h1>
    
    <p style="text-align: center">분류</p>
    </div>
    
    <div class="_3wP2G JJjn3 _1mBhq">

    <a href="https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile10.uf.tistory.com%2Fimage%2F99E28E335A2B9D983BADE1" role="tab" class="_2RG_o _tab-menu" aria-selected="false" title="" id=""
    style="width:80px; height:80px; float:center; margin-left:800px;">이미지메뉴</a>


    <a href="./Review.vue" role="tab" class="_2RG_o _tab-menu" aria-selected="false" title="" id=""
    style="width:80px; height:80px; float:center; margin-left:100px;">리뷰쓰러가기</a>
    </div>

    <br><br><br><br><br>
    <div>
        <h1 style="text-align: center;">가게정보</h1>

        <div style="width:200px; height:150px; float:left; margin-left:650px;">
                <img src="https://t1.daumcdn.net/cfile/tistory/27285A4C595CF2F540">
        
        </div>
        <div style="width:200px; height:150px; float:right; margin-right:650px">
                <img src="https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/03/a0003581/img/basic/a0003581_main.jpg?20191025114924&q=80&rw=750&rh=536">

        </div>

    <br><br><br><br><br><br><br><br><br>
    <div id="app" style="text-align: center">
    <font-awesome-icon icon="fa-solid fa-phone" />
        <p>032-123-4567</p>
     <br>       
    <font-awesome-icon icon="fa-solid fa-location-dot" />
    <p>인천광역시 미추홀구 학익동</p>
        </div>
    </div>


    <div>
        <h2 style="text-align: center;">메뉴</h2>
    <div id="app" style="text-align: center">
    <font-awesome-icon icon="fa-solid fa-phone" />
        <label>짜장면</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>짬뽕</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>볶음밥</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>양장피</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>탕수육</label>
    </div>
    </div>
  
    <div class="card mb-2">
	<div class="card-header bg-light">
	        <h2 style="text-align: left; margin-left:300px;">리뷰</h2> 
	</div>
	<div class="card-body">
		<ul class="list-group list-group-flush">
		    <li class="list-group-item">
			<div class="form-inline mb-2">
				<label for="replyId"><i class="fa fa-user-circle-o fa-2x"></i></label>
				<input type="text" class="form-control ml-2" placeholder="Enter yourId" id="replyId">
				<label for="replyPassword" class="ml-4"><i class="fa fa-unlock-alt fa-2x"></i></label>
				<input type="password" class="form-control ml-2" placeholder="Enter password" id="replyPassword">
			</div>
			<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
			<button type="button" class="btn btn-dark mt-3" onClick="javascript:addReply();">리뷰작성</button>
		    </li>
		</ul>
	</div>
</div>

    </div>
</body>
    </template>

    


<style scoped>

h2{
    text-align: center;
    font-weight:bolder;
    margin-right:6px;
    font-size:3rem;
    font-weight:800;
    color:#000;
    color:rgba(var(--place-color-text1), 1)}
img{
    width:200px;
    height:150px;
    float:left;
    display : block;
    margin : auto;
    }    
p
{
    display:inline-block;
    font-size:30;
    color:#8f8f8f;
    text-align: center;
    padding-bottom: 14px;
}
li {
    margin: 0 0 0 0;
    padding: 0 0 0 0;
    border : 0;
    float: left;
    list-style:none;
}

</style>