package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.OnlineAssessmentMapper;
import per.dhl.pojo.OptionInfo;
import per.dhl.qo.OnlineAssessmentQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 在线评估实现类
 * @author: HongLi
 * @create: 2021-07-26 14:47
 */
@Component
public class OnlineAssessmentServiceImpl implements OnlineAssessmentService {

    @Override
    public ArrayList<OnlineAssessmentQo> GetAssessmentQuestions(Integer selectValue) {
        SqlSession session = SqlSessionUtil.getSession();
        OnlineAssessmentMapper mapper = session.getMapper(OnlineAssessmentMapper.class);
        ArrayList<OnlineAssessmentQo> onlineAssessmentQos = mapper.GetAssessmentQuestions(selectValue);

        for (OnlineAssessmentQo q : onlineAssessmentQos) {
            Integer topicId = q.getTopicId();
            ArrayList<OptionInfo> optionInfos = mapper.getOptionInfos(topicId);
            q.setAnswer(optionInfos);
        }

        session.commit();
        session.close();
        return onlineAssessmentQos;
    }

    @Override
    public Integer SubmitReport(Integer userId, Integer score) {
        SqlSession session = SqlSessionUtil.getSession();
        OnlineAssessmentMapper mapper = session.getMapper(OnlineAssessmentMapper.class);
        Integer integer = mapper.SubmitReport(userId, score);
        session.commit();
        session.close();
        return integer;
    }
}
