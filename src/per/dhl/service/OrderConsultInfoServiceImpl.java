package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.OrderConsultInfoMapper;
import per.dhl.qo.ConsultInfoQo;
import per.dhl.qo.OrderConsultQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 预约咨询师实现类
 * @author: HongLi
 * @create: 2021-07-20 14:05
 */
@Component
public class OrderConsultInfoServiceImpl implements OrderConsultInfoService {

    @Override
    public Integer addOrder(Integer userId, Integer fieldId, Integer consultId, String radioVal, String problemTxt) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderConsultInfoMapper mapper = session.getMapper(OrderConsultInfoMapper.class);
        Integer ifOrderCount = mapper.addOrder(userId, fieldId, consultId, radioVal, problemTxt);
        Integer UpdateOrder = mapper.updateOrder(radioVal);

        session.commit();
        session.close();
        return ifOrderCount;

    }

    @Override
    public ArrayList<OrderConsultQo> getOrderConsultTable(Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderConsultInfoMapper mapper = session.getMapper(OrderConsultInfoMapper.class);
        ArrayList<OrderConsultQo> orderConsultTable = mapper.getOrderConsultTable(userId);
        return orderConsultTable;
    }


    @Override
    public ArrayList<ConsultInfoQo> GetConsultInfo(Integer adminId) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderConsultInfoMapper mapper = session.getMapper(OrderConsultInfoMapper.class);
        ArrayList<ConsultInfoQo> consultInfoQos = mapper.GetConsultInfo(adminId);

        for (ConsultInfoQo c : consultInfoQos) {
            String consultId = c.getConsultId();
            ArrayList<String> fileds = mapper.getFileds(consultId);
            c.setFields(fileds);
        }


        return consultInfoQos;
    }

    @Override
    public Integer evaluateConsult(Integer consultId, String evaluateTxt) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderConsultInfoMapper mapper = session.getMapper(OrderConsultInfoMapper.class);
        Integer ifEvaluateConsult = mapper.evaluateConsult(consultId, evaluateTxt);
        //改变状态
        mapper.Evaluated(consultId);
        session.commit();
        session.close();
        return ifEvaluateConsult;
    }
}
