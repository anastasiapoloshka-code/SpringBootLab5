package ru.pologova.SpringBootLab2.model;

import lombok.Data;
import ru.pologova.SpringBootLab2.enums.Codes;
import ru.pologova.SpringBootLab2.enums.ErrorCodes;
import ru.pologova.SpringBootLab2.enums.ErrorMessages;

@Data
public class Response {

    // Уникальный идентификатор сообщения
    private String uid;

    // Идентификатор операции
    private String operationUid;

    // Время формирования ответа
    private String systemTime;

    // Статус выполнения операции
    private Codes code;

    // Код ошибки
    private ErrorCodes errorCode;

    // Сообщение об ошибке
    private ErrorMessages errorMessage;

    // Рассчитанная годовая премия
    private Double annualBonus;

    // Рассчитанная квартальная премия
    private Double quarterlyBonus;
}