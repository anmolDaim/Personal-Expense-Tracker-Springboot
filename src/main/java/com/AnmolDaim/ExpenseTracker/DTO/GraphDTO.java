package com.AnmolDaim.ExpenseTracker.DTO;

import java.util.List;

import com.AnmolDaim.ExpenseTracker.Entity.Expense;
import com.AnmolDaim.ExpenseTracker.Entity.Income;

import lombok.Data;

@Data
public class GraphDTO {
    
    private List<Expense> expenseList;

    private List<Income> incomeList;
    
}
