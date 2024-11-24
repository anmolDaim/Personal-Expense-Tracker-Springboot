package com.AnmolDaim.ExpenseTracker.Service.Income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.AnmolDaim.ExpenseTracker.DTO.IncomeDTO;
import com.AnmolDaim.ExpenseTracker.Entity.Income;
import com.AnmolDaim.ExpenseTracker.Repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImp implements IncomeService{

    private final IncomeRepository incomeRepository;

    @Override
    public Income postIncome(IncomeDTO incomeDTO){
        return saveAndUpdateIncome(new Income(), incomeDTO);
    }

    public Income saveAndUpdateIncome(Income income,IncomeDTO incomeDTO){
        income.setTitle(incomeDTO.getTitle());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDate(incomeDTO.getDate());
        income.setDescription(incomeDTO.getDescription());

        return incomeRepository.save(income);
    }

    @Override
    public List<IncomeDTO> getAllIncome(){
       return incomeRepository.findAll().stream()
        .sorted(Comparator.comparing(Income::getDate).reversed())
        .map(Income::getIncomeDTO)
        .collect(Collectors.toList());
    }

    @Override
    public Income updateIncome(Long id,IncomeDTO incomeDTO){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            return saveAndUpdateIncome(optionalIncome.get(), incomeDTO);
        }else{
            throw new EntityNotFoundException("Expense is not present with id: "+ id);
        }
    }

    @Override
    public IncomeDTO getIncomeByid(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDTO();
        }else{
            throw new EntityNotFoundException("Expense is not present with id: "+ id);
        }
    }

    @Override
    public void deleteIncome(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            incomeRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense is not present with id: "+ id);
        }
    }

    
}
