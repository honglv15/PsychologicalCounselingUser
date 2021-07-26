package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.qo.OnlineAssessmentQo;
import per.dhl.service.OnlineAssessmentService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 得到相应的评估题目
 * @author: HongLi
 * @create: 2021-07-26 14:41
 */
@WebServlet("/GetAssessmentQuestionsServlet")
public class GetAssessmentQuestionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("评估的题目");
        Integer selectValue = Integer.valueOf(req.getParameter("selectValue"));
        System.out.println(selectValue);

        OnlineAssessmentService service = ServiceFactory.newInstance().getService(OnlineAssessmentService.class);
        ArrayList<OnlineAssessmentQo> onlineAssessmentQos = service.GetAssessmentQuestions(selectValue);

        JsonDto jsonDto = new JsonDto();
        if (onlineAssessmentQos.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("onlineAssessmentQos", onlineAssessmentQos);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("题目得到失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
