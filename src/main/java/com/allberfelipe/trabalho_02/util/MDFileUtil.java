package com.allberfelipe.trabalho_02.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;

public class MDFileUtil {
    public static String readMarkdownFile(String fileName) {
        try {
            var resource = new ClassPathResource("mock-articles/" + fileName);
            var file = resource.getFile();
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo markdown: " + fileName, e);
        }
    }
}
