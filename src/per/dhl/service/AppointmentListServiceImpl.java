package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.AppointmentListMapper;
import per.dhl.mapper.MenuInfoMapper;
import per.dhl.pojo.CalendarInfo;
import per.dhl.pojo.UserMenu;
import per.dhl.qo.ConsultInfoQo;
import per.dhl.qo.OrderTableQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 预约列表服务实现类
 * @author: HongLi
 * @create: 2021-07-19 09:59
 */
@Component
public class AppointmentListServiceImpl implements AppointmentListService {

    @Override
    public ArrayList<ConsultInfoQo> getConsultInfo(Integer fieldId) {
        SqlSession session = SqlSessionUtil.getSession();
        AppointmentListMapper mapper = session.getMapper(AppointmentListMapper.class);
        ArrayList<ConsultInfoQo> consultInfo = mapper.getConsultInfo(fieldId);

        for (ConsultInfoQo c : consultInfo) {
            String consultId = c.getConsultId();
            ArrayList<String> fileds = mapper.getFileds(consultId);
            c.setFields(fileds);
        }

        session.commit();
        session.close();
        return consultInfo;
    }

    @Override
    public ArrayList<CalendarInfo> getConsultOrderTime(String consultTime, Integer consultId) {
        SqlSession session = SqlSessionUtil.getSession();
        AppointmentListMapper mapper = session.getMapper(AppointmentListMapper.class);
        ArrayList<CalendarInfo> consultOrderTime = mapper.getConsultOrderTime(consultTime, consultId);
        session.commit();
        session.close();
        return consultOrderTime;
    }

    @Override
    public ArrayList<OrderTableQo> GetDetailOrderInfo(String orderTime, Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        AppointmentListMapper mapper = session.getMapper(AppointmentListMapper.class);
        ArrayList<OrderTableQo> orderInfos = mapper.GetDetailOrderInfo(orderTime, userId);
        session.commit();
        session.close();
        return orderInfos;
    }

    @Override
    public ArrayList<ConsultInfoQo> ViewConsultantServlet(Integer adminId) {
        SqlSession session = SqlSessionUtil.getSession();
        AppointmentListMapper mapper = session.getMapper(AppointmentListMapper.class);
        ArrayList<ConsultInfoQo> consultInfoQos = mapper.ViewConsultantServlet(adminId);

        for (ConsultInfoQo c : consultInfoQos) {
            String consultId = c.getConsultId();
            ArrayList<String> fileds = mapper.getFileds(consultId);
            c.setFields(fileds);
        }

        session.commit();
        session.close();
        return consultInfoQos;
    }
}
