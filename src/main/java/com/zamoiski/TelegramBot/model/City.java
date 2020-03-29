package com.zamoiski.TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    public City(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
