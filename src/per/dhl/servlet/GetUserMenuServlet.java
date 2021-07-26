package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.UserMenu;
import per.dhl.service.MenuInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 得到用户的菜单
 * @author: HongLi
 * @create: 2021-07-16 13:50
 */
@WebServlet("/GetUserMenuServlet")
public class GetUserMenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("得到用户菜单");
        MenuInfoService menuInfoService = ServiceFactory.newInstance().getService(MenuInfoService.class);
        ArrayList<UserMenu> menuInfos = menuInfoService.GetMenu();
        System.out.println(menuInfos);
        JsonDto jsonDto = new JsonDto();
        if (menuInfos.size()>0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("menuInfos",menuInfos);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("菜单加载错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
