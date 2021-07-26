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
import per.dhl.service.OrderConsultInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 得到咨询师信息
 * @author: HongLi
 * @create: 2021-07-23 16:15
 */
@WebServlet("/GetEvaluateConsultInfoServlet")
public class GetEvaluateConsultInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("评价咨询师");
        Integer adminId = Integer.valueOf(req.getParameter("adminId"));
        System.out.println(adminId);

        OrderConsultInfoService service = ServiceFactory.newInstance().getService(OrderConsultInfoService.class);
        ArrayList<ConsultInfoQo> consultInfoQos = service.GetConsultInfo(adminId);
        System.out.println(consultInfoQos);

        JsonDto jsonDto = new JsonDto();
        if (consultInfoQos.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("consultInfoQos", consultInfoQos);
            jsonDto.setMessage("咨询师信息加载成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("咨询师信息加载错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);

    }
}
