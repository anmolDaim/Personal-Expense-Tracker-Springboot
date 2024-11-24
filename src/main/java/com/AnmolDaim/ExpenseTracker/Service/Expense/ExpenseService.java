package com.AnmolDaim.ExpenseTracker.Service.Expense;

import java.util.List;

import com.AnmolDaim.ExpenseTracker.DTO.ExpenseDTO;
import com.AnmolDaim.ExpenseTracker.Entity.Expense;

public interface ExpenseService {

    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpenses(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);
    
} 

