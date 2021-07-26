package generate;

import generate.UserMenu;

public interface UserMenuDao {
    int deleteByPrimaryKey(Integer menuId);

    int insert(UserMenu record);

    int insertSelective(UserMenu record);

    UserMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(UserMenu record);

    int updateByPrimaryKey(UserMenu record);
}