package com.github.willor777.stock_analysis_lib.analysis.strategies

import com.github.willor777.stock_analysis_lib.analysis.AnalysisResults
import com.github.willor777.stock_analysis_lib.analysis.Strategies
import com.github.willor777.stock_analysis_lib.analysis.StrategyBase
import com.github.willor777.stock_analysis_lib.analysis.StrategyReqData
import com.willor.stock_analysis_lib.charts.StockChart
import java.util.*
import kotlin.math.abs

class PremarketRangeBreak : com.github.willor777.stock_analysis_lib.analysis.StrategyBase<com.github.willor777.stock_analysis_lib.analysis.StrategyReqData.PreMarketRangeBreakReqData>() {

    override val strategyInfo: com.github.willor777.stock_analysis_lib.analysis.Strategies
        get() = com.github.willor777.stock_analysis_lib.analysis.Strategies.PREMARKET_RANGE_BREAK
    override val requiredPeriodRange: String
        get() = "2d"
    override val requiredCandleInterval: String?
        get() = null
    override val requiredPrepost: Boolean
        get() = true


    private val outlierPercentage = 0.03
    private val bufferPercentage = 0.005           // 0.5%

    override fun analyze(data: com.github.willor777.stock_analysis_lib.analysis.StrategyReqData.PreMarketRangeBreakReqData): List<com.github.willor777.stock_analysis_lib.analysis.AnalysisResults> {

        /* Steps...

        - Determine High and Low of premarket range (7am - 9:30am)
            - Remove outliers
            - Add small buffer, .5 avg candle maybe

        - Check that a range break has occured on current or prev candle
            - Makes sure it DID NOT Break on third candle
         */

        val results = mutableListOf<com.github.willor777.stock_analysis_lib.analysis.AnalysisResults>()

        for (chart in data.charts){

            // Find high and low of range
            val hl = determineHighLowOfRange(chart)
            if (hl == null){
                results.add(buildNeutralAnalysisResults(chart))
                continue
            }

            val high = hl.first + (hl.first * bufferPercentage)
            val low = hl.second - (hl.second * bufferPercentage)

            val trailOneHigh = chart.getHighAtIndex(-2)
            val trailTwoHigh = chart.getHighAtIndex(-3)

            val trailOneLow = chart.getLowAtIndex(-2)
            val trailTwoLow = chart.getLowAtIndex(-3)


            // Bullish
            if (trailOneHigh > high && trailTwoHigh < high) {
                 val res = com.github.willor777.stock_analysis_lib.analysis.AnalysisResults(
                     chart.ticker,
                     strategyInfo,
                     strategyInfo.displayName,
                     strategyInfo.description,
                     1,
                     .99,
                     chart.getCloseAtIndex(-1),
                     0.0,
                     0.0,
                     false,
                     false,
                 )

                results.add(res)
            }

            // Bearish
            else if (trailOneLow < low && trailTwoLow > low) {
                val res = com.github.willor777.stock_analysis_lib.analysis.AnalysisResults(
                    chart.ticker,
                    strategyInfo,
                    strategyInfo.displayName,
                    strategyInfo.description,
                    -1,
                    .99,
                    chart.getCloseAtIndex(-1),
                    0.0,
                    0.0,
                    false,
                    false,
                )

                results.add(res)
            }

            // Neutral
            else {
                results.add(buildNeutralAnalysisResults(chart))
            }
        }

        return results.toList()
    }




    private fun determineHighLowOfRange(chart: StockChart): Pair<Double, Double>? {

        // Determine todays date / time values
        val now = Calendar.getInstance()
        val m = now.get(Calendar.MONTH)
        val d = now.get(Calendar.DAY_OF_MONTH)
        val y = now.get(Calendar.YEAR)
        val h = now.get(Calendar.HOUR)
        val minute = now.get(Calendar.MINUTE)

        // Make sure it's after 9:30am
        if (h < 9) {
            return null
        } else if (h == 9 && minute < 30) {
            return null
        }

        // Set range start and end
        val rangeStart = Calendar.getInstance().apply {
            set(y, m, d, 7, 1)
        }.time
        val rangeEnd = Calendar.getInstance().apply {
            set(y, m, d, 9, 30)
        }.time

        // Get slice of candles for premarket range.
        val candles = chart.getSublistOfCandlesByDatetime(rangeStart, rangeEnd)

        // Get High and low values
        val allHighValues = candles.map {
            it.high
        }
        val allLowValues = candles.map {
            it.low
        }

        // Remove outliers and take min and max, Return
        val rangeHigh = removeOutliers(allHighValues).max()
        val rangeLow = removeOutliers(allLowValues).min()
        return Pair(rangeHigh, rangeLow)
    }

    /**
     * Removes outliers from data set. Outlier in this context is classified as a value that is % (outlierPercentage)
     * of stock price greater than both the previous and following values.
     */
    private fun removeOutliers(values: List<Double>): List<Double> {

        val validData = mutableListOf<Double>()

        for (i in 1 until values.lastIndex) {

            val prev = values[i - 1]
            val cur = values[i]
            val next = values[i + 1]

            // 3% of a random value
            val threshold = values.random() * outlierPercentage

            // Check if a 3% jump from prev -> cur -> next happend. Skip if so
            if (abs(cur - prev) > threshold && abs(cur - next) > threshold) {
                continue
            }

            // Add data to list
            validData.add(values[i])
        }

        return validData
    }
}
















