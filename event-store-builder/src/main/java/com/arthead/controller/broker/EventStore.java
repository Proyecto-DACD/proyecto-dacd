package com.arthead.controller.broker;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventStore {
    private static final String BASE_DIR = "eventstore";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final Gson gson = new Gson();

    public void saveEvent(String topic, String json) {
        try {
            JsonObject obj = gson.fromJson(json, JsonObject.class);
            String ss = obj.get("ss").getAsString();
            String ts = obj.get("ts").getAsString();

            LocalDate date = LocalDate.parse(ts.substring(0, 10), DateTimeFormatter.ISO_DATE);

            String dirPath = String.format("%s/%s/%s", BASE_DIR, topic, ss);
            String fileName = String.format("%s.events", date.format(DATE_FORMATTER));

            File directory = new File(dirPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, fileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(json);
                writer.newLine();
            }
        } catch (Exception e) {
            System.err.println("Error guardando evento: " + e.getMessage());
        }
    }
}


