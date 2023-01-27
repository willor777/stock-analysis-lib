package com.willor.stock_analysis_lib.analysis

import com.willor.stock_analysis_lib.charts.StockChart

class TestStrategy: Strategy {
    override val strategyName: StrategyName = StrategyName.TEST_STRATEGY
    override val displayName: String = "Test Strategy"
    override val description: String = "Strategy That throws either a Long or Short trigger every time. Intended for " +
            "Testing purposes only"
    override val requiredPeriodRange: String = "NONE"
    override val requiredCandleInterval: String = "NONE"
    override val requiredPrepost: Boolean = false

    override fun analyzeChart(chart: StockChart): AnalysisResults {
        TODO("Not yet implemented")
    }
}