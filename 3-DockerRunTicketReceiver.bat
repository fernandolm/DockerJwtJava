docker run -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/TicketDatabase --network=bridge -p 9098:9098 --name ticketreceiver ticketreceiver:latest
