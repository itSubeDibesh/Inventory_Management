package com.itsubedibesh.walmart.controllers.configuration.FileUploader;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class FileUploader {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        copyFile(fileName, multipartFile, uploadPath);
    }

    public static void updateFile(String uploadDir, String oldFileName, String newFileName,
                                  MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        deleteFile(oldFileName);
        copyFile(newFileName, multipartFile, uploadPath);
    }

    private static void copyFile(String fileName, MultipartFile multipartFile, Path uploadPath) throws IOException {
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static void deleteFile(String filePath) throws IOException{
        Path path = Paths.get(filePath);
        Files.walkFileTree(path,new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void deleteDirectory(String deleteDir)throws IOException{
        Path deletePath = Paths.get(deleteDir);
        // Traverse the file tree in depth-first fashion and delete each file/directory.
        Files.walk(deletePath)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
