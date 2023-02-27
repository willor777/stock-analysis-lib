package com.willor.stock_analysis_lib.charts.ta_indicators

import com.willor.stock_analysis_lib.charts.TaLibCore

/**
 * All sublist methods return startIndex up to and including endIndex
 */
abstract class IndicatorBase {

    internal val talib = TaLibCore.getTaLibCore()

    protected fun findTrueIndex(i: Int, lastIndexOfValues: Int): Int {
        return if (i < 0) {
            lastIndexOfValues + (i + 1)
        } else {
            i
        }
    }


    /**
     * TA-Lib Indicator functions do not return values that are needed for the initial calculation.
     * Example: If calculating a 10 period SMA, with 100 data points, a dataset of 90 will be returned.
     * This function fills those missing values. So if you calculate a SMA on a dataset of 100,
     * a dataset of 100 will be returned
     */
    protected fun fillMissingValues(
        outputData: DoubleArray, inputData: Collection<Double>
    ): List<Double> {


        // Find n missingValues
        val missingValues = inputData.size - outputData.size

        val finalData = mutableListOf<Double>()
        for (n in 1..missingValues) {
            finalData.add(0.0)
        }

        finalData.addAll(outputData.toList())
        return finalData
    }


    protected fun calculateOutputArraySize(inputDataSize: Int, window: Int): Int {
        return inputDataSize - window + 1
    }


}