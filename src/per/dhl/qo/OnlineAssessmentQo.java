package per.dhl.qo;

import lombok.Data;
import per.dhl.pojo.OptionInfo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 题库管理Qo
 * @author: HongLi
 * @create: 2021-07-26 10:50
 */
@Data
public class OnlineAssessmentQo {
    private Integer fieldId;
    private String topicContent;
    private Integer topicId;
    private ArrayList<OptionInfo> answer;
}
