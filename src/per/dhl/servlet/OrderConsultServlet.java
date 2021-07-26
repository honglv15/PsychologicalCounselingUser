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
import per.dhl.service.OrderConsultInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 预约咨询师
 * @author: HongLi
 * @create: 2021-07-20 09:55
 */
@WebServlet("/OrderConsultServlet")
public class OrderConsultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("预定咨询师");
        HttpSession session = req.getSession();//本次请求的session对象
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Integer userId = userInfo.getUserId();//用户ID
        Integer fieldId = Integer.valueOf(req.getParameter("fieldId")); //领域ID
        Integer consultId = Integer.valueOf(req.getParameter("consultId"));//咨询师ID
        String radioVal = req.getParameter("radioVal");//时间选择
        String ProblemTxt = req.getParameter("ProblemTxt");//问题描述
        System.out.println(userId + " " + fieldId + " " + consultId + " " + radioVal + " " + ProblemTxt);
        System.out.println(userInfo);

        OrderConsultInfoService service = ServiceFactory.newInstance().getService(OrderConsultInfoService.class);
        Integer ifOrder = service.addOrder(userId, fieldId, consultId, radioVal, ProblemTxt);

        JsonDto jsonDto = new JsonDto();
        if (ifOrder > 0) {
            jsonDto.setMessage("预约成功");
        } else {
            jsonDto.setMessage("预约失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
