package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;

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

}