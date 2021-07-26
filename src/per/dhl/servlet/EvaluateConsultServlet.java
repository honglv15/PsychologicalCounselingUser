package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.service.OrderConsultInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingUser
 * @description: 评价用户servlet
 * @author: HongLi
 * @create: 2021-07-24 11:30
 */
@WebServlet("/EvaluateConsultServlet")
public class EvaluateConsultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer consultId = Integer.valueOf(req.getParameter("consultId"));
        String evaluateTxt = req.getParameter("evaluateTxt");

        OrderConsultInfoService service = ServiceFactory.newInstance().getService(OrderConsultInfoService.class);
        Integer ifevaluate = service.evaluateConsult(consultId, evaluateTxt);

        JsonDto jsonDto = new JsonDto();
        if (ifevaluate>0) {
            jsonDto.setId(0);
            jsonDto.setMessage("评论成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("评论失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
        System.out.println("评价用户" + consultId + " " + evaluateTxt);
    }
}
