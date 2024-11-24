package com.AnmolDaim.ExpenseTracker.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseDTO {

    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;

    
}
