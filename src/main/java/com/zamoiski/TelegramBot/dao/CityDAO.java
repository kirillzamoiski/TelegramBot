package com.zamoiski.TelegramBot.dao;

import com.zamoiski.TelegramBot.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDAO extends CrudRepository<City, Long> {

    @Query("SELECT c.description from City c where c.name = :name")
    String findByName(@Param("name") String name);
}
