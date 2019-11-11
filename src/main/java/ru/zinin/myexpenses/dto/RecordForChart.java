package ru.zinin.myexpenses.dto;

import lombok.Data;
import ru.zinin.myexpenses.model.Record;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecordForChart {

    public static RecordForChart getRecordForChart(Record record) {
        RecordForChart recordForChart = new RecordForChart(
              record.getId(),
              record.getDescription(),
              record.getAmount(),
              record.isIncome(),
              record.getCreationDate(),
              record.getCategory().getName()
        );
        return recordForChart;
    }

    public static List<RecordForChart> getListRecordForChart(List<Record> records) {
        return records.stream()
                .map(rec -> RecordForChart.getRecordForChart(rec))
                .collect(Collectors.toList());
    }

    private Long id;
    private String description;
    private Double amount;
    private boolean income;
    private Long creationDate;
    private String category;

    public RecordForChart() {
    }

    public RecordForChart(Long id, String description, Double amount, boolean income, Long creationDate, String category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.income = income;
        this.creationDate = creationDate;
        this.category = category;
    }
}
