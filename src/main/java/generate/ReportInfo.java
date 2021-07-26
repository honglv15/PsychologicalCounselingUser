package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * report_info
 * @author 
 */
@Data
public class ReportInfo implements Serializable {
    private Integer reportId;

    private Integer userId;

    private Integer reportScore;

    private String reportCtime;

    private static final long serialVersionUID = 1L;
}