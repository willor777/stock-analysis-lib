package com.willor.stock_analysis_lib.analysis.strategies

import com.willor.stock_analysis_lib.analysis.AnalysisResults
import com.willor.stock_analysis_lib.analysis.Strategies
import com.willor.stock_analysis_lib.analysis.StrategyBase
import com.willor.stock_analysis_lib.analysis.StrategyReqData
import com.willor.stock_analysis_lib.charts.StockChart

class RSRWBasic: StrategyBase<StrategyReqData.RSRWBasicReqData>() {

    override val strategyInfo: Strategies
        get() = TODO("Not yet implemented")
    override val requiredPeriodRange: String
        get() = TODO("Not yet implemented")
    override val requiredCandleInterval: String
        get() = TODO("Not yet implemented")
    override val requiredPrepost: Boolean
        get() = TODO("Not yet implemented")

    override fun analyze(data: StrategyReqData.RSRWBasicReqData): List<AnalysisResults> {
        TODO("Not yet implemented")
    }
}