package com.AnmolDaim.ExpenseTracker.Service.Stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.AnmolDaim.ExpenseTracker.DTO.GraphDTO;
import com.AnmolDaim.ExpenseTracker.DTO.StatsDTO;
import com.AnmolDaim.ExpenseTracker.Entity.Expense;
import com.AnmolDaim.ExpenseTracker.Entity.Income;
import com.AnmolDaim.ExpenseTracker.Repository.ExpenseRepository;
import com.AnmolDaim.ExpenseTracker.Repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{
    
    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

    @Override
    public GraphDTO getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate starDate = endDate.minusDays(27);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByDateBetween(starDate, endDate));
        graphDTO.setIncomeList(incomeRepository.findByDateBetween(starDate, endDate));

        return graphDTO;
    }

    @Override
    public StatsDTO getStats(){
        Double totalIncome = incomeRepository.sumAllAmounts();
        Double totalExpense = expenseRepository.sumAllAmounts();

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDTO::setLatestIncome );
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(totalIncome-totalExpense);

        List<Income> listIncome = incomeRepository.findAll();
        List<Expense> listExpense = expenseRepository.findAll();

        OptionalDouble minIncome = listIncome.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble minExpense = listExpense.stream().mapToDouble(Expense::getAmount).min();

        OptionalDouble maxIncome = listIncome.stream().mapToDouble(Income::getAmount).max();
        OptionalDouble maxExpense = listExpense.stream().mapToDouble(Expense::getAmount).max();

        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble():null);
        statsDTO.setMinExpensse(minExpense.isPresent() ? minExpense.getAsDouble():null);
        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble():null);
        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble():null);

        return statsDTO;
    }


}
