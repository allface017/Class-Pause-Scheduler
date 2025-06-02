package com.example.classpausescheduler.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClassPause {
    private long id;
    private long subjectId; // フィールド名をキャメルケースに修正
    private LocalDate date; // フィールド名を修正
    private int period;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate substitute_date;
    private int substitute_period;
    private String remarks;

}
