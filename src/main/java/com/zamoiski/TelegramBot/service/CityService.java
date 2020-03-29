package com.zamoiski.TelegramBot.service;

import com.zamoiski.TelegramBot.model.City;

import java.util.List;

public interface CityService {
    List<City> findAll();

    City findById(Long id);

    void save(City city);

    void update(City city);

    void deleteById(Long id);

    String findDescriptionByName(String name);
}
