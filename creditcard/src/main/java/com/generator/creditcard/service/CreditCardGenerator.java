package com.generator.creditcard.service;

import com.generator.creditcard.domain.request.Log;
import com.generator.creditcard.infrastructure.generator.CreditCard;
import com.generator.creditcard.infrastructure.httpclient.LogCall;
import com.generator.creditcard.infrastructure.httpclient.RetrofitConfiguration;
import com.generator.creditcard.infrastructure.mapper.LogMapper;
import com.generator.creditcard.infrastructure.properties.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class CreditCardGenerator {
    private ApplicationProperties applicationProperties;
    private RetrofitConfiguration retrofit;
    private CreditCard creditCard;
    private LogMapper logMapper;

    @Scheduled(initialDelayString = "${app.scheduler.initial-delay}", fixedRateString = "${app.scheduler.fixed-rate}")
    public void start() {
        log.info("Job started - {}", LocalDateTime.now());
        runJob();
        log.info("Job finished - {}", LocalDateTime.now());
    }

    private void runJob() {
        Log logRequest = logMapper.map(creditCard.getCreditCard());

        var logCall = retrofit.create(LogCall.class, applicationProperties.getRetrofit().getBaseUrl());

        log.info("Sending - {}", logRequest.toString());

        try {
            logCall.send(logRequest).execute();
        } catch (IOException ioError) {
            log.error("ERROR SENDING LOG.", ioError);
        }
    }
}
