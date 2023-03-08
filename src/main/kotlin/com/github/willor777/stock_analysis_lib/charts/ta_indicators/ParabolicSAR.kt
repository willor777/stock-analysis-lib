package com.github.willor777.stock_analysis_lib.charts.ta_indicators

import com.github.willor777.stock_analysis_lib.charts.StockChart
import com.tictactec.ta.lib.MInteger

class ParabolicSAR(
    val inputData: List<StockChart.Candle>,
) : IndicatorBase() {

    val lastIndex = inputData.lastIndex
    val size = inputData.size
    val values: List<Double>

    init {

        val outArr = DoubleArray(size - 1)

        val highData = mutableListOf<Double>()
        val lowData = mutableListOf<Double>()
        inputData.map {
            highData.add(it.high)
            lowData.add(it.low)
        }

        // startI, endI, inHigh, inLow, accel, accelMax, _, _, out
        // default accel, and accelMax are .02 and .2.
        talib.sar(
            0,
            inputData.lastIndex,
            highData.toDoubleArray(),
            lowData.toDoubleArray(),
            .02,
            .2,
            MInteger(), MInteger(),
            outArr
        )

        values = fillMissingValues(outArr, highData)
    }


    fun getValueAtIndex(i: Int): Double {
        return values[findTrueIndex(i, values.lastIndex)]
    }

    fun getSublistOfValues(startIndex: Int, endIndex: Int): List<Double> {

        return values.subList(
            findTrueIndex(startIndex, values.lastIndex),
            findTrueIndex(endIndex, values.lastIndex) + 1
        )
    }
}
