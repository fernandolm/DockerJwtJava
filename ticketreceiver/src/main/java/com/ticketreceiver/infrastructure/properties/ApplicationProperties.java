package com.ticketreceiver.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private Report report = new Report();

    @Getter
    @Setter
    public class Report {
        private Integer lastMinutes = 30;
    }
}
