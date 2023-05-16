package ru.java.neostudyproject.service;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationCalculator {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final List<LocalDate> holidays = new ArrayList<>();


    static {
        addHoliday("2023-01-01");
        addHoliday("2023-01-02");
        addHoliday("2023-01-03");
        addHoliday("2023-01-04");
        addHoliday("2023-01-05");
        addHoliday("2023-01-06");
        addHoliday("2023-02-23");
        addHoliday("2023-02-24");
        addHoliday("2023-03-08");
        addHoliday("2023-05-01");
        addHoliday("2023-05-08");
        addHoliday("2023-05-09");
        addHoliday("2023-06-12");
        addHoliday("2023-11-06");
    }

    public static void addHoliday(String holidayDate) {
        LocalDate holiday = LocalDate.parse(holidayDate, formatter);
        holidays.add(holiday);
    }

    public double calculateVacationAllowance(double averageSalary, int vacationDays, String departureDate) {
        double vacationAllowance = 0;

        if (departureDate != null) {
            LocalDate departure = LocalDate.parse(departureDate, formatter);
            int workingDays = calculateWorkingDays(departure, vacationDays);
            vacationAllowance = averageSalary * workingDays;
        }

        return vacationAllowance;
    }

    private int calculateWorkingDays(LocalDate departure, int vacationDays) {
        int workingDays = 0;

        for (int i = 0; i < vacationDays; i++) {
            LocalDate date = departure.plusDays(i);
            if (isWorkingDay(date)) {
                workingDays++;
            }
        }

        return workingDays;
    }

    private boolean isWorkingDay(LocalDate date) {
        return !isWeekend(date) && !isHoliday(date);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
