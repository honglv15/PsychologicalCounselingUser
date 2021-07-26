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
import per.dhl.qo.ConsultInfoQo;
import per.dhl.qo.OrderTableQo;
import per.dhl.service.AppointmentListService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 查看咨询师
 * @author: HongLi
 * @create: 2021-07-24 23:05
 */
@WebServlet("/ViewConsultantServlet")
public class ViewConsultantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("查看咨询师");
        Integer adminId = Integer.valueOf(req.getParameter("adminId"));
        System.out.println(adminId + "咨询师Id");

        AppointmentListService service = ServiceFactory.newInstance().getService(AppointmentListService.class);
        ArrayList<ConsultInfoQo> consultInfoQos = service.ViewConsultantServlet(adminId);
        System.out.println(consultInfoQos);

        JsonDto jsonDto = new JsonDto();
        if (consultInfoQos.size() > 0) {
            jsonDto.getDatas().put("consultInfoQos", consultInfoQos);
            jsonDto.setMessage("查看咨询师成功");
        } else {
            jsonDto.setMessage("查看咨询师失败");
        }

        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
