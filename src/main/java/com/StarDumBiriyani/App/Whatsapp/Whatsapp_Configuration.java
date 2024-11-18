package com.StarDumBiriyani.App.Whatsapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Whatsapp_Configuration {

    public static void sendMsg(String msg) throws JsonProcessingException {

        String apiURL = "https://7103.api.greenapi.com";
        String idInstance = "7103150622";
        String apiTokenInstance = "38da9cba0b7740ff9e1c5a4da8912ae570b6f2161f6a41e5b8";

        var restTemplate = new RestTemplate();
        var requestUrl = new StringBuilder();
        requestUrl.append(apiURL)
                .append("/waInstance").append(idInstance)
                .append("/sendMessage/")
                .append(apiTokenInstance);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var objectMapper = new ObjectMapper();

        Map<String, String> payload = new HashMap<>();
        payload.put("chatId", "919066351136@c.us");
        payload.put("message", msg);

        var jsonBody = objectMapper.writeValueAsString(payload);

//		var jsonBody = "{\r\n\t\"chatId\": \"919066351136@c.us\","
//				+ "\r\n\t\"message\": \"Kodipalya Report on 24-09-2024 \\n Total Sale : 18000 \\n Total Expense : 45000\"\r\n}";

        var requestEntity = new HttpEntity<>(jsonBody, headers);

        var response = restTemplate.exchange(requestUrl.toString(), HttpMethod.POST, requestEntity, String.class);
        System.out.println(response);

        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Success");
        }else {
            System.out.println("Not success");
        }
    }

    public static void sendMsg() throws JsonProcessingException {

        String apiURL = "https://7103.api.greenapi.com";
        String idInstance = "7103150622";
        String apiTokenInstance = "38da9cba0b7740ff9e1c5a4da8912ae570b6f2161f6a41e5b8";

        var restTemplate = new RestTemplate();
        var requestUrl = new StringBuilder();
        requestUrl.append(apiURL)
                .append("/waInstance").append(idInstance)
                .append("/sendMessage/")
                .append(apiTokenInstance);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var objectMapper = new ObjectMapper();

        Map<String, String> payload = new HashMap<>();
        payload.put("chatId", "919066351136@c.us");
        payload.put("message", "Daily Report\nReport from Kodipalya on 24/09/2024\nTotal Sale : 45,000.00\nTotal Expense : 34,000.00");

        var jsonBody = objectMapper.writeValueAsString(payload);

//		var jsonBody = "{\r\n\t\"chatId\": \"919066351136@c.us\","
//				+ "\r\n\t\"message\": \"Kodipalya Report on 24-09-2024 \\n Total Sale : 18000 \\n Total Expense : 45000\"\r\n}";

        var requestEntity = new HttpEntity<>(jsonBody, headers);

        var response = restTemplate.exchange(requestUrl.toString(), HttpMethod.POST, requestEntity, String.class);
        System.out.println(response);

        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Success");
        }else {
            System.out.println("Not success");
        }
    }
}
