package com.example.task02;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));


    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        if (!Files.exists(rootDir)) {
            throw new IllegalArgumentException("Directory does not exist: " + rootDir);
        }

        if (!Files.isDirectory(rootDir)) {
            throw new IllegalArgumentException("Path is not a directory: " + rootDir);
        }

        List<Path> fileList = new ArrayList<>();
        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (!Files.isDirectory(file)) {
                    fileList.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                // Log error but continue traversing
                System.err.println("Failed to access file: " + file + " - " + exc.getMessage());
                return FileVisitResult.CONTINUE;
            }
        });

        return fileList;
    }
}
