package com.AnmolDaim.ExpenseTracker.Entity;

import java.time.LocalDate;

import com.AnmolDaim.ExpenseTracker.DTO.IncomeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Income {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;

    public IncomeDTO getIncomeDTO(){
        IncomeDTO incomeDTO=new IncomeDTO();
        incomeDTO.setAmount(amount);
        incomeDTO.setCategory(category);
        incomeDTO.setDate(date);
        incomeDTO.setDescription(description);
        incomeDTO.setTitle(title);

        return incomeDTO;
    }
}
