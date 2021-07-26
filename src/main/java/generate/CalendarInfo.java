package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * calendar_info
 * @author 
 */
@Data
public class CalendarInfo implements Serializable {
    private Integer calendarId;

    private Integer consultId;

    private Integer calendarState;

    private String calendarTime;

    private static final long serialVersionUID = 1L;
}