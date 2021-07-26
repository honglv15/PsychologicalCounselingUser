package per.dhl.service;

import per.dhl.pojo.CalendarInfo;
import per.dhl.qo.ConsultInfoQo;
import per.dhl.qo.OrderTableQo;

import java.util.ArrayList;

public interface AppointmentListService {
    ArrayList<ConsultInfoQo> getConsultInfo(Integer fieldId);

    ArrayList<CalendarInfo> getConsultOrderTime(String consultTime, Integer consultId);

    ArrayList<OrderTableQo> GetDetailOrderInfo(String orderTime, Integer userId);

    ArrayList<ConsultInfoQo> ViewConsultantServlet(Integer adminId);
}
