package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * option_info
 * @author 
 */
@Data
public class OptionInfo implements Serializable {

    private Integer optionId;

    private Integer topicId;

    private String optionContent;

    private Integer optionScore;

    private static final long serialVersionUID = 1L;
}