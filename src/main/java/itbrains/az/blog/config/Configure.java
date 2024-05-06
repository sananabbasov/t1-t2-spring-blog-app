package itbrains.az.blog.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
public class Configure {


    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
