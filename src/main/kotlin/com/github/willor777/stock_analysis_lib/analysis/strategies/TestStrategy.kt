package com.github.willor777.stock_analysis_lib.analysis.strategies

import com.github.willor777.stock_analysis_lib.analysis.AnalysisResults
import com.github.willor777.stock_analysis_lib.analysis.Strategies
import com.willor.stock_analysis_lib.charts.StockChart

class TestStrategy: Strategy() {
    override val strategyName: com.github.willor777.stock_analysis_lib.analysis.Strategies = com.github.willor777.stock_analysis_lib.analysis.Strategies.TEST_STRATEGY
    override val strategyDisplayName: String = "Test Strategy"
    override val strategyDescription: String = "Strategy That throws either a Long or Short trigger every time. Intended for " +
            "Testing purposes only"
    override val requiredPeriodRange: String = "NONE"
    override val requiredCandleInterval: String = "NONE"
    override val requiredPrepost: Boolean = false

    override fun analyzeChart(chart: StockChart): com.github.willor777.stock_analysis_lib.analysis.AnalysisResults {
        return com.github.willor777.stock_analysis_lib.analysis.AnalysisResults(
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