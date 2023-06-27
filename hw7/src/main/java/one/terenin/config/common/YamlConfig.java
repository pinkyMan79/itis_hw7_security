package one.terenin.config.common;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Objects;

@Configuration
public class YamlConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(
            YamlPropertiesFactoryBean yamlPropertiesFactoryBean) {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
                new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(Objects
                .requireNonNull(yamlPropertiesFactoryBean.getObject()));
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public YamlPropertiesFactoryBean yamlPropertiesFactoryBean(){
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("app.yaml"));
        return factoryBean;
    }

}
