package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.UserInfo;
import per.dhl.service.UserInfoService;

import java.awt.*;
import java.io.IOException;

/**
 * @program: PsychologicalCounselingUser
 * @description: 用户注册Servlet
 * @author: HongLi
 * @create: 2021-07-09 15:54
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    private Integer InsetCount;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String RUAccountText = req.getParameter("RUAccountText");
        String RUpwdText = req.getParameter("RUpwdText");
        String RUpwdCheckText = req.getParameter("RUpwdCheckText");
        Integer sexVal = Integer.valueOf(req.getParameter("sexVal"));
        String RUageText = req.getParameter("RUageText");
        Integer RUphoneText = Integer.valueOf(req.getParameter("RUphoneText"));
        String RUAddressText = req.getParameter("RUAddressText");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount(RUAccountText);
        userInfo.setUserPwd(RUpwdText);
        userInfo.setUserSex(sexVal);
        userInfo.setUserAge(RUageText);
        userInfo.setUserPhone(RUphoneText);
        userInfo.setUserAddress(RUAddressText);

        UserInfoService userInfoService = ServiceFactory.newInstance().getService(UserInfoService.class);
        InsetCount = userInfoService.AddUserInfo(userInfo);
        System.out.println("插入的数据" + InsetCount);

        JsonDto jsonDto = new JsonDto();
        if (InsetCount > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("注册成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("注册失败哦");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);



    }
}
