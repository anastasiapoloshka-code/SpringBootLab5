package ru.pologova.SpringBootLab2.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.pologova.SpringBootLab2.enums.Codes;
import ru.pologova.SpringBootLab2.enums.ErrorCodes;
import ru.pologova.SpringBootLab2.enums.ErrorMessages;
import ru.pologova.SpringBootLab2.exception.UnsupportedCodeException;
import ru.pologova.SpringBootLab2.model.Request;
import ru.pologova.SpringBootLab2.model.Response;
import ru.pologova.SpringBootLab2.service.BonusCalculationService;
import ru.pologova.SpringBootLab2.service.ModifyRequestService;
import ru.pologova.SpringBootLab2.service.ModifyResponseService;
import ru.pologova.SpringBootLab2.service.RequestSenderService;
import ru.pologova.SpringBootLab2.service.ValidationService;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseServiceUid;
    private final ModifyResponseService modifyResponseServiceDate;
    private final ModifyRequestService modifyRequestService;
    private final RequestSenderService requestSenderService;
    private final BonusCalculationService bonusCalculationService;

    public MyController(
            ValidationService validationService,
            @Qualifier("ModifyResponseServiceUid")
            ModifyResponseService modifyResponseServiceUid,
            @Qualifier("ModifyResponseServiceDate")
            ModifyResponseService modifyResponseServiceDate,
            ModifyRequestService modifyRequestService,
            RequestSenderService requestSenderService,
            BonusCalculationService bonusCalculationService) {

        this.validationService = validationService;
        this.modifyResponseServiceUid = modifyResponseServiceUid;
        this.modifyResponseServiceDate = modifyResponseServiceDate;
        this.modifyRequestService = modifyRequestService;
        this.requestSenderService = requestSenderService;
        this.bonusCalculationService = bonusCalculationService;
    }

    @PostMapping("/feedback")
    public Response feedback(
            @Valid @RequestBody Request request,
            BindingResult bindingResult) {

        long startTime = System.currentTimeMillis();

        log.info("Получен Request: {}", request);

        validationService.validate(bindingResult);

        if ("123".equals(request.getUid())) {
            log.error("UnsupportedCodeException: uid = 123");
            throw new UnsupportedCodeException(
                    "Не поддерживаемый код"
            );
        }

        Response response = new Response();

        response.setUid(request.getUid());
        response.setOperationUid(request.getOperationUid());
        response.setSystemTime(request.getSystemTime());
        response.setCode(Codes.SUCCESS);
        response.setErrorCode(ErrorCodes.EMPTY);
        response.setErrorMessage(ErrorMessages.EMPTY);

        bonusCalculationService.calculateBonuses(request, response);

        response = modifyResponseServiceUid.modify(response);
        response = modifyResponseServiceDate.modify(response);

        log.info("Response с рассчитанными премиями: {}", response);

        request = modifyRequestService.modify(request);

        requestSenderService.send(request, startTime);

        log.info("Request успешно передан Сервису 2");

        return response;
    }
}