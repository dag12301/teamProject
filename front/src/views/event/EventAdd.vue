<template>
    <h2 class="event-h2" style="margin-top: 50px;">쿠폰 추가 페이지<h5>(관리자 전용)</h5></h2>
    <form action="" name="EventAdd" method="POST">
        <table>
            <tr>
                <td class="td1">제목:</td>
                <td class="td2"><input type="text" class="eventadd-input" name="title" v-model="title" placeholder="ex)롯데리아" id="title"/></td>
            </tr>
            <tr>
                <td class="td1">내용:</td>
                <td class="td2"><input type="text" class="eventadd-input" name="body" v-model="body" placeholder="ex)10%" id="body"/></td>
            </tr>
            <tr>
                <td class="td1">만료일:</td>
                <td class="td2"><input type="text" class="eventadd-input" name="dueDate" v-model="dueDate" placeholder="ex)yyyy-mm-dd" id="dueDate"/></td>
            </tr>
        
        </table>
        <input type="submit" class="eventadd-button" value="추가" @click="onClick"/>
        
    </form>
</template>

<style scoped>
table{
    margin-left:25%;
}
.td1 {
    padding-top: 20px;
    width: 100px;
}
.eventadd-input {
    margin-top: 20px;
    width: 250px;
    height: 40px;
}
.eventadd-button {
    margin-top: 40px;
}
</style>

<script>



</script>