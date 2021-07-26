package generate;

import java.io.Serializable;
import lombok.Data;

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

    private static final long serialVersionUID = 1L;
}