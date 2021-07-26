package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.weaver.ast.Var;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.CalendarInfo;
import per.dhl.qo.ConsultInfoQo;
import per.dhl.service.AppointmentListService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 得到咨询师的预约时间
 * @author: HongLi
 * @create: 2021-07-19 20:31
 */
@WebServlet("/GetConsultTimeServlet")
public class GetConsultTimeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String consultTime = req.getParameter("consultTime");
        Integer consultId = Integer.valueOf(req.getParameter("consultId"));
        System.out.println("咨询师预约时间" + consultTime + " " + consultId);

        AppointmentListService appointmentListService = ServiceFactory.newInstance().getService(AppointmentListService.class);
        ArrayList<CalendarInfo> consultOrderTime = appointmentListService.getConsultOrderTime(consultTime, consultId);
        System.out.println("咨询师预约日期:" + consultOrderTime);

        JsonDto jsonDto = new JsonDto();
        if (consultOrderTime.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("consultOrderTime", consultOrderTime);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("预定时间错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);

    }
}
