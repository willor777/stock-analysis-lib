package com.willor.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart

interface Strategy {
    val displayName: String
    val description: String

    fun analyzeChart(chart: StockChart): AnalysisResults

}