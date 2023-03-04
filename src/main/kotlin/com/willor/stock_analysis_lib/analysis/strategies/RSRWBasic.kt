package com.willor.stock_analysis_lib.analysis.strategies

import com.willor.stock_analysis_lib.analysis.AnalysisResults
import com.willor.stock_analysis_lib.analysis.Strategy
import com.willor.stock_analysis_lib.analysis.StrategyName
import com.willor.stock_analysis_lib.charts.StockChart

class RSRWBasic: Strategy() {

    override val strategyName: StrategyName
        get() = TODO("Not yet implemented")
    override val strategyDisplayName: String
        get() = TODO("Not yet implemented")
    override val strategyDescription: String
        get() = TODO("Not yet implemented")
    override val requiredPeriodRange: String
        get() = TODO("Not yet implemented")
    override val requiredCandleInterval: String
        get() = TODO("Not yet implemented")
    override val requiredPrepost: Boolean
        get() = TODO("Not yet implemented")

    override fun analyzeChart(chart: StockChart): AnalysisResults {
        TODO("Not yet implemented")
    }
}