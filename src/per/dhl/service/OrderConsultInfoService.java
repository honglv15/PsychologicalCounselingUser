package per.dhl.service;

import per.dhl.qo.ConsultInfoQo;
import per.dhl.qo.OrderConsultQo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description:预约咨询师接口
 * @author: HongLi
 * @create: 2021-07-20 14:04
 */
public interface OrderConsultInfoService {
    Integer addOrder(Integer userId, Integer fieldId, Integer consultId, String radioVal, String problemTxt);
    ArrayList<OrderConsultQo> getOrderConsultTable(Integer userId);
    ArrayList<ConsultInfoQo> GetConsultInfo(Integer adminId);

    Integer evaluateConsult(Integer consultId, String evaluateTxt);
}
