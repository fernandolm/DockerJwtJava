package com.generator.creditcard.infrastructure.generator;

import com.generator.creditcard.domain.Ticket;
import com.generator.creditcard.domain.enumeration.AuthorizerType;
import com.generator.creditcard.infrastructure.properties.ApplicationProperties;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

@Component
public class CreditCard {
    private final Faker faker = new Faker(new Locale("${faker.locale}"));
    private ApplicationProperties applicationProperties;

    public CreditCard(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public Ticket getCreditCard(){
        if(faker.random().nextBoolean()) {
            //success case
            return Ticket.builder()
                    .date(LocalDateTime.now())
                    .authorizer(createAuthorizer())
                    .customer(faker.bothify(applicationProperties.getFaker().getClientMask(), true))
                    .value(BigDecimal.valueOf(faker.random().nextDouble() * 100))
                    .build();
        }

        //for validation tests
        final int NUMBER_OF_FIELDS_TEST = 3;
        final String CUSTOMER_ERROR_TEST = "CUSTOMER_ERROR_TEST";
        final BigDecimal NEGATIVE_VALUE_ERROR_TEST = BigDecimal.valueOf(-1);
        int randomInteger = faker.random().nextInt(NUMBER_OF_FIELDS_TEST);

        switch (randomInteger) {
            case 1:
                return Ticket.builder()
                        .date(LocalDateTime.now())
                        .authorizer(createAuthorizer())
                        .customer(CUSTOMER_ERROR_TEST)
                        .value(BigDecimal.valueOf(faker.random().nextDouble() * 100))
                        .build();
            case 2:
                return Ticket.builder()
                        .date(LocalDateTime.now())
                        .authorizer(createAuthorizer())
                        .customer(faker.bothify(applicationProperties.getFaker().getClientMask(), true))
                        .value(NEGATIVE_VALUE_ERROR_TEST)
                        .build();
            default:
                return Ticket.builder()
                        .date(LocalDateTime.now())
                        .authorizer(AuthorizerType.ERROR_TEST)
                        .customer(faker.bothify(applicationProperties.getFaker().getClientMask(), true))
                        .value(BigDecimal.valueOf(faker.random().nextDouble() * 100))
                        .build();
        }
    }

    private AuthorizerType createAuthorizer() {
        final int NUMBER_OF_AUTHORIZERS = 4;
        int randomInteger = faker.random().nextInt(NUMBER_OF_AUTHORIZERS);

        switch (randomInteger) {
            case 1:
                return AuthorizerType.MASTERCARD;
            case 2:
                return AuthorizerType.ELO;
            case 3:
                return AuthorizerType.AMERICANEXPRESS;
            default:
                return AuthorizerType.VISA;
        }
    }
}
