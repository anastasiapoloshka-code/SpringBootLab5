package ru.pologova.SpringBootLab2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pologova.SpringBootLab2.model.Request;

@Slf4j
@Service
public class ModifyRequestServiceImpl
        implements ModifyRequestService {

    @Override
    public Request modify(Request request) {

        log.info("Request до изменения: {}", request);

        request.setSource("Service1");

        log.info("Request после изменения: {}", request);

        return request;
    }
}