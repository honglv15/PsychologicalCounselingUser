package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.UserInfoMapper;
import per.dhl.pojo.UserInfo;
import per.dhl.qo.UserIncomeInfoQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 用户服务实现类
 * @author: HongLi
 * @create: 2021-07-09 10:31
 */
@Component
public class UserInfoServiceImpl implements UserInfoService {
    private Integer InsertCount;

    @Override
    public UserInfo selectUserByUserAccountAndUserPwd(String userAccount, String userPwd) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        UserInfo UserInfo = mapper.selectUserByUserAccountAndUserPwd(userAccount, userPwd);
        session.commit();
        session.close();
        return UserInfo;
    }

    @Override
    public Integer AddUserInfo(UserInfo userInfo) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        InsertCount = mapper.addUserInfo(userInfo);
        session.commit();
        session.close();
        return InsertCount;
    }

    @Override
    public ArrayList<UserIncomeInfoQo> initMyAccountTables(Integer userId, Integer limit, Integer offset) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        ArrayList<UserIncomeInfoQo> userIncomeInfoQos = mapper.initMyAccountTables(userId, limit, offset);
        session.commit();
        session.close();
        return userIncomeInfoQos;
    }

    @Override
    public Integer countTable(Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        Integer integer = mapper.countTable(userId);
        session.commit();
        session.close();
        return integer;
    }

    @Override
    public double getUserMoney(Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        double userMoney = mapper.getUserMoney(userId);
        session.commit();
        session.close();
        return userMoney;
    }

    @Override
    public Integer ReChangeUserMoney(double charge, Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        double userMoney = mapper.getUserMoney(userId);
        userMoney = userMoney + charge;
        Integer integer = mapper.ReChangeUserMoney(userId, userMoney);
        session.commit();
        session.close();
        return integer;
    }
}
