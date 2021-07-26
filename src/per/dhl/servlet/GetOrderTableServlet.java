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
import per.dhl.qo.OrderConsultQo;
import per.dhl.service.OrderConsultInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 得到预约的表格
 * @author: HongLi
 * @create: 2021-07-20 16:26
 */
@WebServlet("/GetOrderTableServlet")
public class GetOrderTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Integer userId = userInfo.getUserId();
        OrderConsultInfoService service = ServiceFactory.newInstance().getService(OrderConsultInfoService.class);
        ArrayList<OrderConsultQo> orderConsultTable = service.getOrderConsultTable(userId);
        System.out.println(orderConsultTable);

        JsonDto jsonDto = new JsonDto();
        if (orderConsultTable.size()>0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("orderConsultTable",orderConsultTable);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("表格加载错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);



    }
}
