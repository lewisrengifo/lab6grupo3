package sw2.lab6.teletok.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/fotos/**")
                //Aqui coloca la carpeta donde estaran las imagenes
                .addResourceLocations("file:C:/Users/Eduardo/Desktop/EduardoImportante/SW/fotos");
    }
}
