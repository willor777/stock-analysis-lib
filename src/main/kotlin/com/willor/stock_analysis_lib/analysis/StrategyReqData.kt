package com.willor.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart

sealed interface StrategyReqData {

    data class PreMarketRangeBreakReqData(val charts: List<StockChart>): StrategyReqData
    data class RSRWBasicReqData(val comparedToChart: StockChart, val charts: List<StockChart>): StrategyReqData
    data class TestStrategy(val charts: List<StockChart>): StrategyReqData

}
