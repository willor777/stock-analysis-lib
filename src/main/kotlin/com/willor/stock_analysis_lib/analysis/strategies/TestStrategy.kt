package com.willor.stock_analysis_lib.analysis.strategies

import com.willor.stock_analysis_lib.analysis.AnalysisResults
import com.willor.stock_analysis_lib.analysis.Strategies
import com.willor.stock_analysis_lib.charts.StockChart

class TestStrategy: Strategy() {
    override val strategyName: Strategies = Strategies.TEST_STRATEGY
    override val strategyDisplayName: String = "Test Strategy"
    override val strategyDescription: String = "Strategy That throws either a Long or Short trigger every time. Intended for " +
            "Testing purposes only"
    override val requiredPeriodRange: String = "NONE"
    override val requiredCandleInterval: String = "NONE"
    override val requiredPrepost: Boolean = false

    override fun analyzeChart(chart: StockChart): AnalysisResults {
        return AnalysisResults(
            chart.ticker,
            strategyName,
            strategyDisplayName,
            strategyDescription,
            (-1..1).random(),
            .99,
            chart.getCloseAtIndex(-1),
            0.0,
            0.0,
            false,
            false
        )
    }
}