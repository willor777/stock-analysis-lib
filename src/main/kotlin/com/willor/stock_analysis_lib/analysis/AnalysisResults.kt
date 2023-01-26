package com.willor.stock_analysis_lib.analysis

data class AnalysisResults(
    val ticker: String,
    val trigger: Int,
    val strategy: StrategyName,
    val strategyDescription: String,
    val timestamp: Long = System.currentTimeMillis()
)
