package com.kirky.covid.controllers;

import com.kirky.covid.models.LocationStats;
import com.kirky.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = covidDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int newCases = allStats.stream().mapToInt(stat -> stat.getDiff()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("newCases", newCases);
        return "home";
    }
}
