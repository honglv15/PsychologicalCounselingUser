package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.UserInfo;
import per.dhl.qo.UserIncomeInfoQo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 用户信息mapper
 * @author: HongLi
 * @create: 2021-07-09 11:24
 */
public interface UserInfoMapper {
    UserInfo selectUserByUserAccountAndUserPwd(@Param("userAccount") String userAccount, @Param("userPwd") String userPwd);

    Integer addUserInfo(@Param("U") UserInfo userInfo);

    ArrayList<UserIncomeInfoQo> initMyAccountTables(@Param("userId") Integer userId, @Param("limit") Integer limit, @Param("offset") Integer offset);

    Integer countTable(@Param("userId")Integer userId);

    double getUserMoney(@Param("userId")Integer userId);

    Integer ReChangeUserMoney(@Param("userId")Integer userId, @Param("userMoney") double userMoney);
}
