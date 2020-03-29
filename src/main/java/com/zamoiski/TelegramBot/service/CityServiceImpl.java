package com.zamoiski.TelegramBot.service;

import com.zamoiski.TelegramBot.dao.CityDAO;
import com.zamoiski.TelegramBot.model.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityDAO cityDAO;
    private static final String CITY_NOT_FOUND = "Извините, по данному городу информации не найдено(Для получения списка городов введите 1)";

    @Override
    public List<City> findAll() {
        return StreamSupport.stream(cityDAO.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public City findById(Long id) {
        return cityDAO.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(City city) {
        cityDAO.save(city);
    }

    @Override
    public void update(City city) {
        cityDAO.save(city);
    }

    @Override
    public void deleteById(Long id) {
        cityDAO.deleteById(id);
    }

    @Override
    public String findDescriptionByName(String name) {
        Optional<String> description = Optional.ofNullable(cityDAO.findByName(name));
        return description.orElse(CITY_NOT_FOUND);
    }
}
