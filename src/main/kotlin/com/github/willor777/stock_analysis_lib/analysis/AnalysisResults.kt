package com.github.willor777.stock_analysis_lib.analysis

data class AnalysisResults(
    val ticker: String,
    val strategyName: com.github.willor777.stock_analysis_lib.analysis.Strategies,
    val strategyDisplayName: String,
    val strategyDescription: String,
    val triggerValue: Int,
    val triggerStrengthPercentage: Double,

    val stockPriceAtTime: Double,
    val suggestedStop: Double,
    val suggestedTakeProfit: Double,
    val shouldCloseLong: Boolean,
    val shouldCloseShort: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)
