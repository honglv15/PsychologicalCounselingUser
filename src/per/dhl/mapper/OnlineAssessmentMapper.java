package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.OptionInfo;
import per.dhl.qo.OnlineAssessmentQo;

import java.util.ArrayList;

public interface OnlineAssessmentMapper {
    ArrayList<OnlineAssessmentQo> GetAssessmentQuestions(@Param("selectValue") Integer selectValue);

    ArrayList<OptionInfo> getOptionInfos(@Param("topicId") Integer topicId);
}
