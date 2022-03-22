package com.ticketreceiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TicketReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketReceiverApplication.class, args);
	}
}
