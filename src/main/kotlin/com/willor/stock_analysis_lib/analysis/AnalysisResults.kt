package com.willor.stock_analysis_lib.analysis

data class AnalysisResults(
    val ticker: String,
    val strategyName: Strategies,
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
