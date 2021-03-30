package com.itsubedibesh.walmart.controllers.configuration.FileUploader;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ResourceHandler  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("src/User_Images", registry);
    }

    private void exposeDirectory(String directoryName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(directoryName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (directoryName.startsWith("../")) directoryName = directoryName.replace("../", "");

        registry.addResourceHandler("/" + directoryName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
}
