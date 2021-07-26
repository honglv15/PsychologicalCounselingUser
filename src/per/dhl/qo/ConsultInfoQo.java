package per.dhl.qo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * consult_info
 *
 * @author
 */
@Data
public class ConsultInfoQo implements Serializable {
    private String userAccount;

    private String adminAccount;

    private String adminId;

    private String consultImg;

    private String consultId;

    private String consultSchool;

    private String fieldName;

    private String titleName;

    private String consultBackground;

    private String consultIntro;

    private Double consultFee;

    private String commentContent;

    private ArrayList<String> fields;
}