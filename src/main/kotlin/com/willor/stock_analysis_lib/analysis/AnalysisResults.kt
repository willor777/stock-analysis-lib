package com.willor.stock_analysis_lib.analysis

data class AnalysisResults(
    val ticker: String,
    val trigger: Int,
    val strategyName: StrategyName,
    val strategyDisplayName: String,
    val strategyDescription: String,
    val stockPriceAtTime: Double,
    val triggerStrengthPercentage: Double,
    val suggestedStop: Double,
    val suggestedTakeProfit: Double,
    val shouldCloseLongPositions: Boolean,
    val shouldCloseShortPositions: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

//
//@ColumnInfo val ticker: String,
//@ColumnInfo val triggerAnalysisName: String,
//@ColumnInfo val triggerAnalysisDesc: String,
//@ColumnInfo val triggerValue: Int,
//@ColumnInfo val stockPriceAtTime: Double,
//@ColumnInfo val pctGainAtTime: Double,
//@ColumnInfo val dollarGainAtTime: Double,
//@ColumnInfo val timestamp: Long,
//@ColumnInfo val curVolAvgVolRatio: Double,