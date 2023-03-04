package com.willor.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart

abstract class Strategy {
    abstract val strategyName: StrategyName
    abstract val strategyDisplayName: String
    abstract val strategyDescription: String
    abstract val requiredPeriodRange: String
    abstract val requiredCandleInterval: String
    abstract val requiredPrepost: Boolean


    abstract fun analyzeChart(chart: StockChart): AnalysisResults

    internal fun buildNeutralAnalysisResults(chart: StockChart): AnalysisResults {
        return AnalysisResults(
            chart.ticker, strategyName, strategyDisplayName, strategyDescription, 0, 0.0,
            chart.getCloseAtIndex(-1), 0.0, 0.0, false, false
        )
    }

}