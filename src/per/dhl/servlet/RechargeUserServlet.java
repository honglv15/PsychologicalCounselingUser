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
import per.dhl.service.UserInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingUser
 * @description: 充值业务
 * @author: HongLi
 * @create: 2021-07-23 09:15
 */
@WebServlet("/RechargeUserServlet")
public class RechargeUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double charge = Double.parseDouble(req.getParameter("charge"));
        HttpSession session = req.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Integer userId = userInfo.getUserId();
        UserInfoService service = ServiceFactory.newInstance().getService(UserInfoService.class);
        Integer integer = service.ReChangeUserMoney(charge, userId);

        JsonDto jsonDto = new JsonDto();
        if (integer > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("充值成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("充值失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);

    }
}
