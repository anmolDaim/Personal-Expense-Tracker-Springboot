package com.AnmolDaim.ExpenseTracker.Service.Income;

import java.util.List;

import com.AnmolDaim.ExpenseTracker.DTO.IncomeDTO;
import com.AnmolDaim.ExpenseTracker.Entity.Income;

public interface IncomeService {
    
    Income postIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getAllIncome();

    Income updateIncome(Long id,IncomeDTO incomeDTO);

    IncomeDTO getIncomeByid(Long id);

    void deleteIncome(Long id);
}
