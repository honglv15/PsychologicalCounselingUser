package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.MenuInfoMapper;
import per.dhl.mapper.UserInfoMapper;
import per.dhl.pojo.UserMenu;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingUser
 * @description: 菜单服务
 * @author: HongLi
 * @create: 2021-07-16 14:05
 */
@Component
public class MenuInfoServiceImpl implements MenuInfoService{
    @Override
    public ArrayList<UserMenu> GetMenu() {
        SqlSession session = SqlSessionUtil.getSession();
        MenuInfoMapper mapper = session.getMapper(MenuInfoMapper.class);
        ArrayList<UserMenu> userMenus = mapper.getUserMenu();
        session.commit();
        session.close();
        return userMenus;
    }

}
