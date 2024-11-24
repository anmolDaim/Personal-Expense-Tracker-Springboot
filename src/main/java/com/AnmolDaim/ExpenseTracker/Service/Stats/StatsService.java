package com.AnmolDaim.ExpenseTracker.Service.Stats;

import com.AnmolDaim.ExpenseTracker.DTO.GraphDTO;
import com.AnmolDaim.ExpenseTracker.DTO.StatsDTO;

public interface StatsService {

    GraphDTO getChartData();

    StatsDTO getStats();
    
} 