package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.qo.ConsultInfoQo;
import per.dhl.service.AppointmentListService;
import per.dhl.service.MenuInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 查询咨询师
 * @author: HongLi
 * @create: 2021-07-19 09:50
 */
@WebServlet("/GetConsultNameServlet")
public class GetConsultNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer fieldId = Integer.valueOf(req.getParameter("fieldId"));

        System.out.println("得到咨询师领域Id" + fieldId);
        AppointmentListService appointmentListService = ServiceFactory.newInstance().getService(AppointmentListService.class);
        ArrayList<ConsultInfoQo> consultInfo = appointmentListService.getConsultInfo(fieldId);



        System.out.println( consultInfo);
        JsonDto jsonDto = new JsonDto();
        if (consultInfo.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("consultInfo", consultInfo);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("菜单加载错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);

    }
}
