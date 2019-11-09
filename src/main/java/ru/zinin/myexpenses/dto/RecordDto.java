package ru.zinin.myexpenses.dto;

import lombok.Data;
import ru.zinin.myexpenses.model.Record;

@Data
public class RecordDto {

    public static Record getRecord(RecordDto recordDto) {
        Record record = new Record();
        record.setDescription(recordDto.getDescription());
        record.setAmount(recordDto.getAmount());
        record.setIncome(recordDto.isIncome());
        return record;
    }

    private String currentlyCategory;
    private boolean income;
    private String description;
    private Double amount;
    private Long categoryId;

    public RecordDto() {
    }

    public RecordDto(String currentlyCategory, boolean income, String description, Double amount, Long categoryId) {
        this.currentlyCategory = currentlyCategory;
        this.income = income;
        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
    }
}
