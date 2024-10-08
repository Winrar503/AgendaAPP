package org.darwin.servicios.implementaciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/qr-codes/**")
                .addResourceLocations("file:///C:/Users/darwi/OneDrive/Escritorio/Universidad/Java/qr-codes/");

        registry.addResourceHandler("/fotos/**")
                .addResourceLocations("file:///C:/Users/darwi/OneDrive/Escritorio/Universidad/Java/AgendaApp/src/main/resources/static/fotos/");
    }

}