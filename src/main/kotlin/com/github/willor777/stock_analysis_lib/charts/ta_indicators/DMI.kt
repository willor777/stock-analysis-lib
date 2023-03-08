package com.github.willor777.stock_analysis_lib.charts.ta_indicators

import com.github.willor777.stock_analysis_lib.charts.StockChart
import com.tictactec.ta.lib.MInteger

class DMI(
    val inputData: List<StockChart.Candle>,
    val window: Int = 14
) : IndicatorBase() {

    val size = inputData.size
    val lastIndex = inputData.lastIndex

    val posDiValues: List<Double>
    val negDiValues: List<Double>
    val adxValues: List<Double>


    init {

        val outSize = calculateOutputArraySize(inputData.size, window) - 1

        val outPosDI = DoubleArray(outSize)
        val outNegDI = DoubleArray(outSize)
        val outAdx = DoubleArray(outSize - 13)      // adx requires less space due to window

        val highData = mutableListOf<Double>()
        val lowData = mutableListOf<Double>()
        val closeData = mutableListOf<Double>()

        inputData.map {
            highData.add(it.high)
            lowData.add(it.low)
            closeData.add(it.close)
        }

        talib.plusDI(
            0,
            lastIndex,
            highData.toDoubleArray(),
            lowData.toDoubleArray(),
            closeData.toDoubleArray(),
            window,
            MInteger(), MInteger(),
            outPosDI
        )

        talib.minusDI(
            0,
            lastIndex,
            highData.toDoubleArray(),
            lowData.toDoubleArray(),
            closeData.toDoubleArray(),
            window,
            MInteger(), MInteger(),
            outNegDI
        )

        talib.adx(
            0,
            lastIndex,
            highData.toDoubleArray(),
            lowData.toDoubleArray(),
            closeData.toDoubleArray(),
            window,
            MInteger(), MInteger(),
            outAdx
        )

        posDiValues = fillMissingValues(outPosDI, highData)
        negDiValues = fillMissingValues(outNegDI, lowData)
        adxValues = fillMissingValues(outAdx, highData)

    }

    fun getPosDiValueAtIndex(i: Int): Double {
        return posDiValues[findTrueIndex(i, posDiValues.lastIndex)]
    }

    fun getSublistOfPosDiValues(startIndex: Int, endIndex: Int): List<Double> {

        return posDiValues.subList(
            findTrueIndex(startIndex, posDiValues.lastIndex),
            findTrueIndex(endIndex, posDiValues.lastIndex) + 1
        )
    }

    fun getNegDiValueAtIndex(i: Int): Double {
        return negDiValues[findTrueIndex(i, negDiValues.lastIndex)]
    }

    fun getSublistOfNegDiValues(startIndex: Int, endIndex: Int): List<Double> {

        return negDiValues.subList(
            findTrueIndex(startIndex, negDiValues.lastIndex),
            findTrueIndex(endIndex, negDiValues.lastIndex) + 1
        )
    }

    fun getAdxValueAtIndex(i: Int): Double {
        return adxValues[findTrueIndex(i, adxValues.lastIndex)]
    }

    fun getSublistOfAdxValues(startIndex: Int, endIndex: Int): List<Double> {

        return adxValues.subList(
            findTrueIndex(startIndex, adxValues.lastIndex),
            findTrueIndex(endIndex, adxValues.lastIndex) + 1
        )
    }
}

