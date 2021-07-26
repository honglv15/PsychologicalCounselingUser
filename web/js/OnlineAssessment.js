$("#StartEvaluation").click(function () {
    var selectValue = $('#FieldSelection option:selected').val();
    $.ajax({
        url: 'GetAssessmentQuestionsServlet',
        type: 'post',
        data: {
            'selectValue': selectValue,
        },
        async: true,
        dataType: 'JSON',
        success: function (resq) {
            $('#questionsPanel').removeAttr("style");
            $('#questionsPanelHead').attr("style", "display: none");
            $('#questionsOnlinePanel').removeAttr("style");
            $('#questionsFooterPanel').removeAttr("style");
            qArr = resq.datas.onlineAssessmentQos;
            console.log(qArr)
            questionsManage.innerHTML = ``
            for (let i = 0; i < qArr.length; i++) {
                questionsManage.innerHTML += `<div class="col-md-12">
                <div class="col-md-5" style="margin-bottom: 10px"><h4>${qArr[i].topicContent}</h4></div>
            </div>
            <div class="col-md-12">
                <div class="col-md-2">
                    <label class="radio-inline">
                        <input type="radio" class="radioItem" score="${qArr[i].answer[0].optionScore}" name=${qArr[i].topicContent}> ${qArr[i].answer[0].optionContent}
                    </label>
                </div>
                <div class="col-md-2">
                      <label class="radio-inline">
                        <input type="radio" class="radioItem" score="${qArr[i].answer[1].optionScore}" name=${qArr[i].topicContent}> ${qArr[i].answer[1].optionContent}
                      </label>
                </div>
                    <div class="col-md-2">
                    <label class="radio-inline">
                        <input type="radio" class="radioItem" score="${qArr[i].answer[2].optionScore}" name=${qArr[i].topicContent}> ${qArr[i].answer[2].optionContent}
                    </label>
                </div>
            </div>`
            }
        },
        error: function (resq) {
            layer.msg("联系管理员")
        }
    })
})
$("#subBtn").click(function () {
    console.log("提交")
    var eles = document.getElementsByClassName("radioItem");
    var score = 0
    for (let i = 0; i < eles.length; i++) {
        if (eles[i].checked) {
            score += parseInt(eles[i].getAttribute("score"))
            console.log(eles[i])
        }
    }
    console.log(score)
    layer.confirm('是否提交评估报告', {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            url: 'SubmitReportServlet',
            type: 'post',
            data: {
                'score': score
            },
            async: true,
            dataType: 'JSON',
            success: function (resq) {

                layer.msg(resq.message)
            },
            error: function () {
                layer.msg("联系管理员")
            }
        })
    }, function () {
        layer.msg("没有提交报告")
    });


})
