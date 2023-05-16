package com.github.willor777.stock_analysis_lib.analysis

import com.github.willor777.stock_analysis_lib.charts.StockChart


abstract class StrategyBase<T: StrategyReqData> {

    abstract val strategyInfo: Strategies
    abstract val requiredPeriodRange: String?
    abstract val requiredCandleInterval: String?
    abstract val requiredPrepost: Boolean?

    abstract fun analyze(data: T): List<AnalysisResults>

    /**
     * Builds analysis results to be returned if no trigger was found. Does not include suggested stop/takeProft
     */
    internal fun buildNeutralAnalysisResults(chart: StockChart): AnalysisResults {
        return AnalysisResults(
            chart.ticker, strategyInfo, strategyInfo.displayName, strategyInfo.description, 0, 0.0,
            chart.getCloseAtIndex(-1), 0.0, 0.0, false, false
        )
    }
}


