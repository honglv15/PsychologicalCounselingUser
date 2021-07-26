package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.CalendarInfo;
import per.dhl.qo.ConsultInfoQo;
import per.dhl.qo.OrderTableQo;

import java.util.ArrayList;

public interface AppointmentListMapper {
    ArrayList<ConsultInfoQo> getConsultInfo(@Param("fieldId") Integer fieldId);

    ArrayList<String> getFileds(@Param("consultId") String consultId);

    ArrayList<CalendarInfo> getConsultOrderTime(@Param("consultTime") String consultTime, @Param("consultId") Integer consultId);

    ArrayList<OrderTableQo> GetDetailOrderInfo(@Param("orderTime") String orderTime,@Param("userId") Integer userId);

    ArrayList<ConsultInfoQo> ViewConsultantServlet(@Param("adminId")Integer adminId);
}
