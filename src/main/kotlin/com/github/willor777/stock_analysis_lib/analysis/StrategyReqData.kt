package com.github.willor777.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart

sealed interface StrategyReqData {

    data class PreMarketRangeBreakReqData(val charts: List<StockChart>):
        com.github.willor777.stock_analysis_lib.analysis.StrategyReqData
    data class RSRWBasicReqData(val comparedToChart: StockChart, val charts: List<StockChart>):
        com.github.willor777.stock_analysis_lib.analysis.StrategyReqData
    data class TestStrategy(val charts: List<StockChart>):
        com.github.willor777.stock_analysis_lib.analysis.StrategyReqData

}
