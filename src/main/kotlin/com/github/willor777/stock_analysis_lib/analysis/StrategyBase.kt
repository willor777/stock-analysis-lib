package com.github.willor777.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart


abstract class StrategyBase<T: com.github.willor777.stock_analysis_lib.analysis.StrategyReqData> {

    abstract val strategyInfo: com.github.willor777.stock_analysis_lib.analysis.Strategies
    abstract val requiredPeriodRange: String?
    abstract val requiredCandleInterval: String?
    abstract val requiredPrepost: Boolean?

    abstract fun analyze(data: T): List<com.github.willor777.stock_analysis_lib.analysis.AnalysisResults>

    internal fun buildNeutralAnalysisResults(chart: StockChart): com.github.willor777.stock_analysis_lib.analysis.AnalysisResults {
        return com.github.willor777.stock_analysis_lib.analysis.AnalysisResults(
            chart.ticker, strategyInfo, strategyInfo.displayName, strategyInfo.description, 0, 0.0,
            chart.getCloseAtIndex(-1), 0.0, 0.0, false, false
        )
    }
}


