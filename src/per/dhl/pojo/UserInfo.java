package per.dhl.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * user_info
 *
 * @author
 */
@Data
@Component
public class UserInfo implements Serializable {
    private Integer userId;

    private String userAccount;

    private String userPwd;

    private String userName;

    private String userAge;

    private Integer userSex;

    private Integer userPhone;

    private String userAddress;

    private Integer userStatus;

    private Double userMoney;

    private Integer userIfDel;

    private String userCtime;

    private String userMtime;
}