package com.medical.medical.model;


import java.sql.Date;

public class RegisterRecord {
    private int id;
    private int departmentId;
    private Date scheduleDate;
    private int morning;
    private int afternoon;
    private int night;
    private int realSchedule;
    private String realScheduleString;
    private String realScheduleWeekString;

    public String getRealScheduleWeekString() {
        return realScheduleWeekString;
    }

    public void setRealScheduleWeekString(String realScheduleWeekString) {
        this.realScheduleWeekString = realScheduleWeekString;
    }

    public String getRealScheduleString() {
        return realScheduleString;
    }

    public void setRealScheduleString(String realScheduleString) {
        this.realScheduleString = realScheduleString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public int getMorning() {
        return morning;
    }

    public void setMorning(int morning) {
        this.morning = morning;
    }

    public int getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(int afternoon) {
        this.afternoon = afternoon;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }
    public int getRealSchedule() {
        return realSchedule;
    }

    public void setRealSchedule(int realSchedule) {
        this.realSchedule = realSchedule;
    }
}
