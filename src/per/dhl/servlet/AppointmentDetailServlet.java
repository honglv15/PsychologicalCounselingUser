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

import per.dhl.qo.OrderTableQo;
import per.dhl.service.AppointmentListService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 预约详情
 * @author: HongLi
 * @create: 2021-07-23 14:52
 */
@WebServlet("/AppointmentDetailServlet")
public class AppointmentDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("预定列表详细信息");
        String orderTime = req.getParameter("orderTime");

        HttpSession session = req.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Integer userId = userInfo.getUserId();

        AppointmentListService service = ServiceFactory.newInstance().getService(AppointmentListService.class);
        ArrayList<OrderTableQo> orderInfos = service.GetDetailOrderInfo(orderTime, userId);
        System.out.println(orderInfos);
        System.out.println(userId + " " + orderTime + " ");
        JsonDto jsonDto = new JsonDto();
        if (orderInfos.size() > 0) {
            jsonDto.getDatas().put("orderInfos", orderInfos);
            jsonDto.setMessage("查看成功");
        } else {
            jsonDto.setMessage("查看成功");
        }

        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }


}
