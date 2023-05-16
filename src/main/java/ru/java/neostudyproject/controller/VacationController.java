package ru.java.neostudyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.java.neostudyproject.service.VacationCalculator;


@RestController
@RequiredArgsConstructor
public class VacationController {

    private final VacationCalculator vacationCalculator;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateVacationAllowance(@RequestParam double averageSalary, @RequestParam int vacationDays, @RequestParam String vacationDates) {

        double vacationAllowance = vacationCalculator.calculateVacationAllowance(averageSalary, vacationDays, vacationDates);

        return ResponseEntity.ok(vacationAllowance);
    }

}
