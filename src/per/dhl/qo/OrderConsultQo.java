package per.dhl.qo;

import lombok.Data;

/**
 * @program: PsychologicalCounselingUser
 * @description: 预约列表的查询对象
 * @author: HongLi
 * @create: 2021-07-20 16:41
 */
@Data
public class OrderConsultQo {
    private String orderTime;
    private String fieldName;
    private String stateName;
    private String adminAccount;
    private String adminId;
}
