package com.willor.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart

class TestStrategy: Strategy {
    override val strategyName: StrategyName = StrategyName.TEST_STRATEGY
    override val strategyDisplayName: String = "Test Strategy"
    override val strategyDescription: String = "Strategy That throws either a Long or Short trigger every time. Intended for " +
            "Testing purposes only"
    override val requiredPeriodRange: String = "NONE"
    override val requiredCandleInterval: String = "NONE"
    override val requiredPrepost: Boolean = false

    override fun analyzeChart(chart: StockChart): AnalysisResults {
        return AnalysisResults(
            chart.ticker,
            (-1..1).random(),
            strategyName,
            strategyDisplayName,
            strategyDescription,
            chart.getCloseAtIndex(-1),
            (0..100).random().toDouble() / 100.0,
            suggestedStop = chart.getCloseAtIndex(-1) - (chart.getCloseAtIndex(-1) * .005),
            suggestedTakeProfit = chart.getCloseAtIndex(-1) + (chart.getCloseAtIndex(-1) * .01),
            shouldCloseLongPositions = false,
            shouldCloseShortPositions = false,
        )
    }
}