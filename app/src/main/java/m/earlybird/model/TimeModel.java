package m.earlybird.model;

import com.orm.SugarRecord;

import java.util.ArrayList;

public class TimeModel extends SugarRecord{


    Integer hour;
    Integer minute;
    Boolean snooze;
    ArrayList<Boolean> repeat;

    public TimeModel(){}

    public TimeModel(Integer hour, Integer minute, Boolean snooze, ArrayList<Boolean> repeat){
        this.hour = hour;
        this.minute = minute;
        this.snooze = snooze;
        this.repeat = repeat;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public Boolean getSnooze() {
        return snooze;
    }

    public ArrayList<Boolean> getRepeat() {
        return repeat;
    }

    @Override
    public String toString(){
        return hour+" : "+minute;
    }
}
