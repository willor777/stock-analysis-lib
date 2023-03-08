package com.github.willor777.stock_analysis_lib.analysis.strategies

import com.github.willor777.stock_analysis_lib.analysis.AnalysisResults
import com.github.willor777.stock_analysis_lib.analysis.Strategies
import com.github.willor777.stock_analysis_lib.analysis.StrategyBase
import com.github.willor777.stock_analysis_lib.analysis.StrategyReqData

class TestStrategy: StrategyBase<StrategyReqData.TestStrategyReqData>() {
    override val requiredPeriodRange: String = "NONE"
    override val requiredCandleInterval: String = "NONE"
    override val requiredPrepost: Boolean = false
    override val strategyInfo: Strategies
        get() = Strategies.TEST_STRATEGY

    override fun analyze(data: StrategyReqData.TestStrategyReqData): List<AnalysisResults>{

        val results = mutableListOf<AnalysisResults>()
        for (chart in data.charts){


        val ar =  AnalysisResults(
            chart.ticker,
            strategyInfo,
            strategyInfo.displayName,
            strategyInfo.description,
            (-1..1).random(),
            .99,
            chart.getCloseAtIndex(-1),
            0.0,
            0.0,
            false,
            false
        )
            results.add(ar)
        }

        return results
    }
}