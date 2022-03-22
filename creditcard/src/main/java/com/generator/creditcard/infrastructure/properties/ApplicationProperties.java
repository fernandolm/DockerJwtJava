package com.generator.creditcard.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private Retrofit retrofit = new Retrofit();
    private Faker faker = new Faker();

    @Getter
    @Setter
    public class Faker {
        private String locale = "pt-BR";
        private String clientMask;
    }

    @Getter
    @Setter
    public class Retrofit {
        private String baseUrl;
        private HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BASIC;
        private Long readTimeout = 30L;
        private Long connectTimeout = 30L;
    }
}
