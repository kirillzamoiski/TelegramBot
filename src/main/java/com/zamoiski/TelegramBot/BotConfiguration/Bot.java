package com.zamoiski.TelegramBot.BotConfiguration;

import com.zamoiski.TelegramBot.model.City;
import com.zamoiski.TelegramBot.service.CityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegrambot.properties")
public class Bot extends TelegramLongPollingBot {

    @Value("${telegrambot.name}")
    private String username;
    @Value("${telegrambot.token}")
    private String token;
    @Value("${telegrambot.start}")
    private String start;
    private CityService cityService;

    public Bot(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().startsWith(start)) {
            start(update);
            return;
        }
        if (update.getMessage().getText().startsWith("1")) {
            listCities(update);
            return;
        }
        findDescriptionByName(update);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage().setChatId(chatId).setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private void start(Update update) {
        Long chatId = update.getMessage().getChat().getId();
        sendMessage(chatId, "Здравствуйте, " + update.getMessage().getFrom().getFirstName() + "! Какой город вас интерисует?");
    }

    private void findDescriptionByName(Update update) {
        Long chatId = update.getMessage().getChat().getId();
        sendMessage(chatId, cityService.findDescriptionByName(update.getMessage().getText()));
    }

    private void listCities(Update update) {
        Long chatId = update.getMessage().getChat().getId();
        for (City city : cityService.findAll()) {
            sendMessage(chatId, city.getName());
        }
    }
}
