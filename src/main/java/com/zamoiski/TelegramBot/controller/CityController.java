package com.zamoiski.TelegramBot.controller;

import com.zamoiski.TelegramBot.model.City;
import com.zamoiski.TelegramBot.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telegrambot")
@AllArgsConstructor
public class CityController {
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/cities/{cityId}")
    public City findById(@PathVariable Long cityId) {
        return cityService.findById(cityId);
    }

    @GetMapping("/cities/find/{cityName}")
    public String findByName(@PathVariable String cityName) {
        return cityService.findDescriptionByName(cityName);
    }

    @PostMapping("/cities")
    public ResponseEntity<Object> addEmployee(@RequestBody City city) {
        cityService.save(city);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/cities")
    public ResponseEntity<Object> updateEmployee(@RequestBody City city) {
        cityService.update(city);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cities/{cityId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long cityId) {
        cityService.deleteById(cityId);
        return ResponseEntity.ok().build();
    }
}
