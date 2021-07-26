package per.dhl.qo;

import lombok.Data;

@Data
public class OrderTableQo {
    private String orderTime;

    private String AdminAccount;

    private String userAccount;

    private String userId;

    private String fieldName;

    private String stateName;

    private String orderProblem;

    private Integer orderId;

    private String orderSuctime;

    private Integer consultId;

    private Integer fieldId;

    private Integer stateId;

    private String orderReply;

    private String replyTime;

    private String commentContent;

    private String commentTime;
}
