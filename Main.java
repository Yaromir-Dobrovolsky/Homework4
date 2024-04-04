package org.example;

import org.example.service.DoctorSaveService;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создание объекта класса DoctorSaveService.Doctor для сохранения информации о докторе
        DoctorSaveService.Doctor doctor = new DoctorSaveService.Doctor();

        // Ввод данных о докторе с клавиатуры
        System.out.println("Добровольский Я.Б. РИБО-02-22 \n");
        System.out.println("Введите имя врача:");
        doctor.name = scanner.nextLine();
        System.out.println("Введите специализацию врача:");
        doctor.specialization = scanner.nextLine();

        // Ввод опыта работы врача (годы)
        boolean isYearValid = false;
        while (!isYearValid) {
            try {
                System.out.println("Введите опыт врача:");
                doctor.experience = Integer.parseInt(scanner.nextLine());
                isYearValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректный опыт в годах.");
            }
        }

        // Ввод роста врача
        boolean isPriceValid = false;
        while (!isPriceValid) {
            try {
                System.out.println("Введите рост врача:");
                doctor.height = Double.parseDouble(scanner.nextLine());
                isPriceValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректный рост.");
            }
        }

        // Ввод информации о новом сотруднике
        boolean isNewValid = false;
        while (!isNewValid) {
            System.out.println("Это новый сотрудник? (true/false):");
            String isNewInput = scanner.nextLine();
            if (isNewInput.equalsIgnoreCase("true")) {
                doctor.isNew = true;
                isNewValid = true;
            } else if (isNewInput.equalsIgnoreCase("false")) {
                doctor.isNew = false;
                isNewValid = true;
            } else {
                System.out.println("Ошибка: Введите 'true' или 'false'.");
            }
        }

        // Ввод пути к файлу для сохранения объекта
        boolean isPathValid = false;
        String filePath = "";
        while (!isPathValid) {
            System.out.println("Введите путь к файлу для сохранения:");
            filePath = scanner.nextLine();
            File file = new File(filePath);
            if (file.isAbsolute()) {
                isPathValid = true;
            } else {
                System.out.println("Ошибка: Введите абсолютный путь к файлу.");
            }
        }

        try {
            String finalFilePath = filePath;
            Thread serializeThread = new Thread(() -> {
                try (FileWriter fileWriter = new FileWriter(finalFilePath)) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Experience", doctor.getExperience());
                    jsonObject.put("Height", doctor.getHeight());
                    jsonObject.put("name", doctor.getName());
                    jsonObject.put("Specialization", doctor.getSpecialization());
                    jsonObject.put("isNew", doctor.isNew());

                    String json = jsonObject.toString();

                    fileWriter.write(json);
                    System.out.println("Данные успешно сохранены в файл: " + finalFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            serializeThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
