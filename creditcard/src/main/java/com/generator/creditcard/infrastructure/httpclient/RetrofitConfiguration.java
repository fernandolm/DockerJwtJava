package com.generator.creditcard.infrastructure.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.creditcard.infrastructure.properties.ApplicationProperties;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfiguration {
    private final Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
    private ObjectMapper objectMapper;
    private ApplicationProperties applicationProperties;

    public RetrofitConfiguration(ObjectMapper objectMapper, ApplicationProperties applicationProperties) {
        this.objectMapper = objectMapper;
        this.applicationProperties = applicationProperties;
    }

    @PostConstruct
    private void init() {
        retrofitBuilder.client(getRetrofitConfiguration());
        retrofitBuilder.addConverterFactory(JacksonConverterFactory.create(objectMapper));
    }

    public <S> S create(Class<S> clazz, String url) {
        Retrofit retrofit = retrofitBuilder.baseUrl(url).build();
        return retrofit.create(clazz);
    }

    private OkHttpClient getRetrofitConfiguration() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(applicationProperties.getRetrofit().getLogLevel());

        return httpClientBuilder
                .addInterceptor(logging)
                .readTimeout(applicationProperties.getRetrofit().getReadTimeout(), TimeUnit.SECONDS)
                .connectTimeout(applicationProperties.getRetrofit().getConnectTimeout(), TimeUnit.SECONDS)
                .build();
    }
}
