package ro.cmm.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {


    @Value("${local.files.dir}")
    private String localFilesDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ext-img/**")
                .addResourceLocations("file:" + localFilesDir)
                .setCachePeriod(0);
    }

}
