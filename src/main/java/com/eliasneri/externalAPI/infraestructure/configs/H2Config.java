package com.eliasneri.externalAPI.infraestructure.configs;

import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {
    public static String unaccent(String input) {
        return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

}
