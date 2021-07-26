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
            qArr = resq.datas.questionBankManagementQos;
            console.log(qArr)
            console.log(count)
            questionsManage.innerHTML = ``

            for (let i = 0; i < qArr.length; i++) {
                questionsManage.innerHTML += `<div class="col-md-12">
                <div class="col-md-5" style="margin-bottom: 10px"><h4>${qArr[i].topicContent}</h4></div>
                <div style="float: right">
                    <button type="button" class="btn btn-primary">修改</button>
                    <button type="button" class="btn btn-danger">删除</button>
                </div>
            </div>
            <div class="col-md-12">
                <div class="col-md-2">
                    <label class="radio-inline">
                        <input type="radio" name=${qArr[i].topicContent}> ${qArr[i].answer[0].optionContent}
                    </label>
                </div>
                <div class="col-md-2">
                      <label class="radio-inline">
                        <input type="radio" name=${qArr[i].topicContent}> ${qArr[i].answer[1].optionContent}
                      </label>
                </div>
                    <div class="col-md-2">
                    <label class="radio-inline">
                        <input type="radio" name=${qArr[i].topicContent}> ${qArr[i].answer[2].optionContent}
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