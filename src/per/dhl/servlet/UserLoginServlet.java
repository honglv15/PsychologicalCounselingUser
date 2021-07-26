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
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 用户登录servlet
 * @author: HongLi
 * @create: 2021-07-09 10:06
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private UserInfo UserInfo;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String UnameText = req.getParameter("UnameText");
        String UpwdText = req.getParameter("UpwdText");
        String captchaCode = req.getParameter("captchaCode");

        System.out.println("用户登录doPost " + UnameText + "*" + UpwdText + "*" + captchaCode);

        HttpSession session = req.getSession();//本次请求的session对象
        String codeValue = (String) session.getAttribute("code");//得到存放在session中的验证码
        UserInfoService userInfoService = ServiceFactory.newInstance().getService(UserInfoService.class);
        UserInfo = userInfoService.selectUserByUserAccountAndUserPwd(UnameText, UpwdText);
        session.setAttribute("UserInfo", UserInfo);

        System.out.println("查询的登录用户信息 " + UserInfo);
        JsonDto jsonDto = new JsonDto();
        if (codeValue.equalsIgnoreCase(captchaCode)) {
            if (UserInfo != null) {
                jsonDto.setId(0);
                jsonDto.setMessage("success");
                jsonDto.setLocation("UserMain.html");
            } else {
                jsonDto.setId(1);
                jsonDto.setMessage("账号密码错误！");
            }
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("验证码错误！");
        }

        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
