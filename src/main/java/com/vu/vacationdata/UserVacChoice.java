package com.vu.vacationdata;


import java.util.Calendar;
import java.util.Date;


public class UserVacChoice {
    private String userName;
    private String continent;
    private String country;
    private Date vacationStart;
    private Date vacationEnd;


    public String getUserName() {
        return userName;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getVacationStart() {
        return vacationStart;
    }

    public Date getVacationEnd() {
        return vacationEnd;
    }

    private void setVacationStart(Date vacationStart) {
        this.vacationStart = vacationStart;
    }

    private void setVacationEnd(Date vacationEnd) {
        this.vacationEnd = vacationEnd;
    }

    public UserVacChoice(){
        this.userName = null;
        this.country = null;
        this.continent = null;
    }

    public UserVacChoice(String userName, String continent, String country){
        this.userName = userName;
        this.continent = continent;
        this.country = country;
    }

    public void generateVacationPeriod(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date minVacationStart = calendar.getTime();


        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date maxVacationStart = calendar.getTime();

        int vacationDuration = (int) (Math.random() * 363) + 3;
        int vacationStartOffset = (int) (Math.random() * 8) + 3;

        calendar.setTime(minVacationStart);
        calendar.add(Calendar.DAY_OF_YEAR, vacationStartOffset);
        vacationStart = calendar.getTime();

        calendar.setTime(vacationStart);
        calendar.add(Calendar.DAY_OF_YEAR, vacationDuration);
        vacationEnd = calendar.getTime();
    }
}
