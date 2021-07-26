package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * user_menu
 * @author 
 */
@Data
public class UserMenu implements Serializable {
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer menuPid;

}