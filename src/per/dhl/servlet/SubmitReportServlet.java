package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.UserInfo;
import per.dhl.service.OnlineAssessmentService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingUser
 * @description: 提交报告
 * @author: HongLi
 * @create: 2021-07-26 16:31
 */
@WebServlet("/SubmitReportServlet")
public class SubmitReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("提交报告");
        Integer score = Integer.valueOf(req.getParameter("score"));
        System.out.println(score);
        HttpSession session = req.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Integer userId = userInfo.getUserId();

        OnlineAssessmentService service = ServiceFactory.newInstance().getService(OnlineAssessmentService.class);
        Integer integer = service.SubmitReport(userId, score);

        JsonDto jsonDto = new JsonDto();
        if (integer > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("提交成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("提交失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);

    }
}
