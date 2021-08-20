package com.xupt.community;

import java.time.LocalDate;
import java.util.List;

public class WeekData{
    //111
    // 一周的开始时间
    private LocalDate start;
    // 一周的结束时间
    private LocalDate end;

    public WeekData(){
    }
    public WeekData(List<LocalDate> localDates) {
        this.start = localDates.get(0);
        this.end = localDates.get(localDates.size()-1);
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}