package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Var;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.UserInfo;
import per.dhl.pojo.UserMenu;
import per.dhl.qo.UserIncomeInfoQo;
import per.dhl.service.MenuInfoService;
import per.dhl.service.UserInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 初始化我的账户表格
 * @author: HongLi
 * @create: 2021-07-22 09:52
 */
@WebServlet("/initMyAccountTablesServlet")
public class InitMyAccountTablesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));

        HttpSession session = req.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Integer userId = userInfo.getUserId();

        System.out.println("我的账户表格初始化" + limit + offset);
        UserInfoService service = ServiceFactory.newInstance().getService(UserInfoService.class);
        ArrayList<UserIncomeInfoQo> userIncomeInfoQos = service.initMyAccountTables(userId,limit, offset);
        Integer countTable = service.countTable(userId);
        double userMoney = service.getUserMoney(userId);

        System.out.println(userIncomeInfoQos + "个数" + countTable+"用户资金:"+userMoney);

        JsonDto jsonDto = new JsonDto();
        if (userIncomeInfoQos.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("userIncomeInfoQos", userIncomeInfoQos);
            jsonDto.getDatas().put("countTable", countTable);
            jsonDto.getDatas().put("userMoney", userMoney);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("表格加载错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
