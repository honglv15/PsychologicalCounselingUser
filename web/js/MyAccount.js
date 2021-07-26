limit = 0
offset = 5; // 每页显示多少条
page = 5;//查询的数据条数
count = 0; // 一共多少条数据
currPage = 1; // 当前第几页
totalPage = 0; // 一共多少页

window.onload = initMyAccountTable()

function initMyAccountTable() {

    $.ajax({
        url: 'initMyAccountTablesServlet',
        type: 'post',
        data: {
            'limit': limit,
            'offset': offset
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            console.log(resp);
            count = resp.datas.countTable;

            totalPage = count % page == 0 ? count / page : parseInt(count / page + 1);
            var tablepage = document.getElementById('tablepage');
            tablepage.innerText = currPage + "/" + totalPage
            console.log(currPage + "/" + totalPage)
            console.log(tablepage.innerText)

            arr = resp.datas.userIncomeInfoQos;
            var mybody = document.getElementById('mybody');
            mybody.innerText = '';
            console.log(arr)
            console.log(mybody + "表格")
            for (var i = 0; i < arr.length; i++) {
                mybody.innerHTML += `<tr align="center">
                        <td>${arr[i].incomeTime}</td>
            <td>${arr[i].incomeContent}</td>
<td>${arr[i].userAccount}</td>
<td>${arr[i].incomeOperation}</td>
        <td>${arr[i].incomeMoney}</td>
    </tr>`
            }

            var userMoney = resp.datas.userMoney
            console.log("用户余额:" + userMoney);
            var money = document.getElementById("money");
            money.innerHTML = userMoney;

        },
        error: function () {
            layer.msg("请联系管理员")
        }
    })
}

$("#recharge").click(function (event) {
    $('#myModal').modal({
        keyboard: false,
        show: true
    })
})


$("#chargeBtn").click(function (event) {
    var charge = $("#charge").val();
    $.ajax({
        url: 'RechargeUserServlet',
        type: 'post',
        data: {
            'charge': charge
        },
        async: true,
        dataType: 'JSON',
        success: function (data) {
            initMyAccountTable()
            layer.msg(data.message)

        },
        error: function (data) {
            layer.msg("联系管理员")
        }
    });

})
