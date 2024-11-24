package com.AnmolDaim.ExpenseTracker.DTO;

import com.AnmolDaim.ExpenseTracker.Entity.Expense;
import com.AnmolDaim.ExpenseTracker.Entity.Income;

import lombok.Data;

@Data
public class StatsDTO {

    private Double income;
    private Double expense;
    private Income latestIncome;
    private Expense latestExpense;

    private Double balance;
    private Double minIncome;
    private Double minExpensse;
    private Double maxIncome;
    private Double maxExpense;
    
}
