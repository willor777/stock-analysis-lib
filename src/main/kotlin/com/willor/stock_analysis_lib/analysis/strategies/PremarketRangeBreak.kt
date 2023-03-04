package com.willor.stock_analysis_lib.analysis.strategies

import com.willor.stock_analysis_lib.analysis.AnalysisResults
import com.willor.stock_analysis_lib.analysis.Strategy
import com.willor.stock_analysis_lib.analysis.StrategyName
import com.willor.stock_analysis_lib.charts.StockChart
import java.util.*
import kotlin.math.abs

class PremarketRangeBreak : Strategy() {

    override val strategyName: StrategyName
        get() = TODO("Not yet implemented")
    override val strategyDisplayName: String
        get() = TODO("Not yet implemented")
    override val strategyDescription: String
        get() = TODO("Not yet implemented")
    override val requiredPeriodRange: String
        get() = TODO("Not yet implemented")
    override val requiredCandleInterval: String
        get() = TODO("Not yet implemented")
    override val requiredPrepost: Boolean
        get() = TODO("Not yet implemented")


    private val outlierPercentage = 0.03
    private val bufferPercentage = 0.005           // 0.5%

    override fun analyzeChart(chart: StockChart): AnalysisResults {

        /* Steps...

        - Determine High and Low of premarket range (7am - 9:30am)
            - Remove outliers
            - Add small buffer, .5 avg candle maybe

        - Check that a range break has occured on current or prev candle
            - Makes sure it DID NOT Break on third candle
         */

        val hl = determineHighLowOfRange(chart) ?: return buildNeutralAnalysisResults(chart)

        val high = hl.first + (hl.first * bufferPercentage)
        val low = hl.second - (hl.second * bufferPercentage)

        val t1 = chart.getCloseAtIndex(-2)
        val t2 = chart.getCloseAtIndex(-3)

        // Bullish
        if (t1 > high && t2 < high) {
            return AnalysisResults(
                chart.ticker,
                strategyName,
                strategyDisplayName,
                strategyDescription,
                1,
                .99,
                chart.getCloseAtIndex(-1),
                0.0,
                0.0,
                false,
                false,
            )
        }

        // Bearish
        else if (t1 < low && t2 > low) {
            return AnalysisResults(
                chart.ticker,
                strategyName,
                strategyDisplayName,
                strategyDescription,
                -1,
                .99,
                chart.getCloseAtIndex(-1),
                0.0,
                0.0,
                false,
                false,
            )
        }

        // No Trigger
        return buildNeutralAnalysisResults(chart)
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
















