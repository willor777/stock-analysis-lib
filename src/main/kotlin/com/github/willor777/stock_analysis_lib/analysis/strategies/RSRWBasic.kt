package com.github.willor777.stock_analysis_lib.analysis.strategies

import com.github.willor777.stock_analysis_lib.analysis.AnalysisResults
import com.github.willor777.stock_analysis_lib.analysis.Strategies
import com.github.willor777.stock_analysis_lib.analysis.StrategyBase
import com.github.willor777.stock_analysis_lib.analysis.StrategyReqData
import com.willor.stock_analysis_lib.charts.StockChart

class RSRWBasic: com.github.willor777.stock_analysis_lib.analysis.StrategyBase<com.github.willor777.stock_analysis_lib.analysis.StrategyReqData.RSRWBasicReqData>() {

    override val strategyInfo: com.github.willor777.stock_analysis_lib.analysis.Strategies
        get() = TODO("Not yet implemented")
    override val requiredPeriodRange: String
        get() = TODO("Not yet implemented")
    override val requiredCandleInterval: String
        get() = TODO("Not yet implemented")
    override val requiredPrepost: Boolean
        get() = TODO("Not yet implemented")

    override fun analyze(data: com.github.willor777.stock_analysis_lib.analysis.StrategyReqData.RSRWBasicReqData): List<com.github.willor777.stock_analysis_lib.analysis.AnalysisResults> {
        TODO("Not yet implemented")
    }
}