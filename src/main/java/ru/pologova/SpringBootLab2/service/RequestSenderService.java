package ru.pologova.SpringBootLab2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.pologova.SpringBootLab2.model.Request;

@Slf4j
@Service
public class RequestSenderService {

    private final RestClient restClient = RestClient.create();

    public void send(Request request, long startTime) {

        log.info("Отправка Request в Сервис 2: {}", request);

        restClient.post()
                .uri("http://localhost:8084/receive")
                .header("X-Request-Start", String.valueOf(startTime))
                .body(request)
                .retrieve()
                .toBodilessEntity();

        log.info("Request успешно отправлен в Сервис 2");
    }
}