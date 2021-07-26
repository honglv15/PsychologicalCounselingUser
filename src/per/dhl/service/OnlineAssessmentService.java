package per.dhl.service;

import per.dhl.qo.OnlineAssessmentQo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 在线评估服务
 * @author: HongLi
 * @create: 2021-07-26 14:46
 */
public interface OnlineAssessmentService {
    ArrayList<OnlineAssessmentQo> GetAssessmentQuestions(Integer selectValue);

    Integer SubmitReport(Integer userId, Integer score);
}
