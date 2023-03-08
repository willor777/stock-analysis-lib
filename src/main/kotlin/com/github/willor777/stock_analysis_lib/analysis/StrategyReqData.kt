package com.github.willor777.stock_analysis_lib.analysis

import com.github.willor777.stock_analysis_lib.charts.StockChart


sealed interface StrategyReqData {

    data class PreMarketRangeBreakReqData(val charts: List<StockChart>):
        StrategyReqData
    data class RSRWBasicReqData(val comparedToChart: StockChart, val charts: List<StockChart>):
        StrategyReqData
    data class TestStrategyReqData(val charts: List<StockChart>):
        StrategyReqData

}
