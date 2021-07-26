package per.dhl.service;

import per.dhl.pojo.UserInfo;
import per.dhl.qo.UserIncomeInfoQo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 用户服务接口类
 * @author: HongLi
 * @create: 2021-07-09 10:31
 */
public interface UserInfoService {
    UserInfo selectUserByUserAccountAndUserPwd(String userAccount, String userPwd);

    Integer AddUserInfo(UserInfo userInfo);

    ArrayList<UserIncomeInfoQo> initMyAccountTables(Integer userId, Integer limit, Integer offset);

    Integer countTable(Integer userId);

    double getUserMoney(Integer userId);

    Integer ReChangeUserMoney(double charge, Integer userId);
}
