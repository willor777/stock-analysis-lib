package com.willor.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart

interface Strategy {
    val strategyName: StrategyName
    val displayName: String
    val description: String
    val requiredPeriodRange: String
    val requiredCandleInterval: String
    val requiredPrepost: Boolean


    fun analyzeChart(chart: StockChart): AnalysisResults

}