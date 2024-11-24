package com.AnmolDaim.ExpenseTracker.Service.Expense;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.AnmolDaim.ExpenseTracker.DTO.ExpenseDTO;
import com.AnmolDaim.ExpenseTracker.Entity.Expense;
import com.AnmolDaim.ExpenseTracker.Repository.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    @Override
    public Expense postExpense(ExpenseDTO expenseDTO){
        return saveOrUpdateexpense(new Expense(), expenseDTO);
    }
    
    public Expense saveOrUpdateexpense(Expense expense, ExpenseDTO expenseDTO){
        expense.setTitle(expenseDTO.getTitle());
        expense.setDescription(expenseDTO.getDescription());
        expense.setDate(expenseDTO.getDate());
        expense.setCategory(expenseDTO.getCategory());
        expense.setAmount(expenseDTO.getAmount());

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(){
            return expenseRepository.findAll().stream()
            .sorted(Comparator.comparing(Expense::getDate).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public Expense getExpenseById(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        }else{
            throw new EntityNotFoundException("Expense is not present with id: "+ id);
        }
    }

    @Override
    public Expense updateExpenses(Long id, ExpenseDTO expenseDTO){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return saveOrUpdateexpense(optionalExpense.get(), expenseDTO);
        }else{
            throw new EntityNotFoundException("Expense is not present with id: "+ id);
        }
    }

    @Override
    public void deleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            expenseRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense is not present with id: "+ id);
        }
    }

   
    
}
