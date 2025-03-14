package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath()
        );
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String title = null;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("format.tags.title=")) {
                title = line.substring(line.indexOf("=\"") + 2, line.length() - 1);
                break;
            }
        }
        process.waitFor();

        return title;
    }
}
