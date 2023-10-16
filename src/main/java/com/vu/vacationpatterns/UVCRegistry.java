package com.vu.vacationpatterns;

import com.vu.vacationdata.UserVacChoice;

import java.util.*;

public class UVCRegistry {
    private static final Map<String, UserVacChoice> registry = new HashMap<>();
    public static void addUVC(String key, UserVacChoice userChoice) {
        registry.put(key, userChoice);
    }
    public static UserVacChoice getUVC(String key) {
        return registry.get(key);
    }
}
