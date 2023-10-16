package com.vu.vacationpatterns;

import com.vu.vacationdata.UserVacChoice;

public class UVCSingleton {
    private static UserVacChoice instance;
    private UVCSingleton(){

    }

    public static UserVacChoice getInstance(){
        if(instance == null){
            instance = new UserVacChoice();
        }
        return instance;
    }

    public static void setUserVacChoice(String name, String continent, String country){
        UserVacChoice UVC = getInstance();
        UVC.setUserName(name);
        UVC.setContinent(continent);
        UVC.setCountry(country);
    }
}
