package ru.pologova.SpringBootLab2.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.pologova.SpringBootLab2.enums.Positions;
import ru.pologova.SpringBootLab2.enums.Systems;

@Data
public class Request {

    @NotBlank(message = "uid не должен быть пустым")
    @Size(max = 32, message = "uid должен содержать не более 32 символов")
    private String uid;

    @NotBlank(message = "operationUid не должен быть пустым")
    @Size(max = 32, message = "operationUid должен содержать не более 32 символов")
    private String operationUid;

    private Systems systemName;

    @NotBlank(message = "systemTime не должен быть пустым")
    private String systemTime;

    private String source;

    @Min(value = 1, message = "communicationId должен быть не меньше 1")
    @Max(value = 100000, message = "communicationId должен быть не больше 100000")
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;

    // Должность сотрудника
    @NotNull(message = "Должность обязательна")
    private Positions position;

    // Заработная плата
    @NotNull(message = "Зарплата обязательна")
    @PositiveOrZero(message = "Зарплата не может быть отрицательной")
    private Double salary;

    // Коэффициент премии
    @NotNull(message = "Коэффициент премии обязателен")
    @PositiveOrZero(message = "Коэффициент премии не может быть отрицательным")
    private Double bonus;

    // Количество рабочих дней
    @NotNull(message = "Количество рабочих дней обязательно")
    @Min(value = 1, message = "Количество рабочих дней должно быть больше 0")
    @Max(value = 366, message = "Количество рабочих дней не может превышать 366")
    private Integer workDays;

    @Override
    public String toString() {
        return "Request{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName=" + systemName +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                ", position=" + position +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                '}';
    }
}