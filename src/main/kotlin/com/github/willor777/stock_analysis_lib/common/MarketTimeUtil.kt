package com.github.willor777.stock_analysis_lib.common

import java.util.*


object MarketTimeUtil {

    fun getNextFullyFormedCandle(ts: Long, candleInterval: CandleInterval): Long {
        // Set the start time to 9am of the current day
        val cal = Calendar.getInstance()
        cal.timeInMillis = ts

        // Check that day is in M-F range
        val isBusinessDay = isBusinessDay(ts)
        if (!isBusinessDay(ts)) {
            return getFirstCandleOfNextBusinessDay(ts, candleInterval)
        }

        if (candleInterval == CandleInterval.D1) {
            return getFirstCandleOfNextBusinessDay(ts, candleInterval)
        }

        // Get hour and minute of today
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)

        // If premarket / return first candle of day
        when {
            (h < 9 || h == 9 && m < 30) -> {
                cal.set(Calendar.MILLISECOND, 0)
                cal.set(Calendar.SECOND, 0)
                cal.set(Calendar.MINUTE, candleInterval.minuteOfFirstCandle)
                cal.set(Calendar.HOUR_OF_DAY, candleInterval.hourOfFirstCandle)
                return cal.timeInMillis
            }

            // If postmarket / return first candle of NEXT day
            (h >= 16) -> {
                return getFirstCandleOfNextBusinessDay(ts, candleInterval)
            }

            // Valid time
            else -> {
                val hourMinute = determineNextCandleTime(h, m, candleInterval)

                // Validate the next candle time is within market hours
                if (hourMinute.first >= 16) {
                    return getFirstCandleOfNextBusinessDay(ts, candleInterval)
                }

                cal.set(Calendar.MILLISECOND, 0)
                cal.set(Calendar.SECOND, 0)
                cal.set(Calendar.MINUTE, hourMinute.second)
                cal.set(Calendar.HOUR_OF_DAY, hourMinute.first)
                return cal.timeInMillis
            }
        }


    }


    private fun determineNextCandleTime(hour: Int, minute: Int, candleInterval: CandleInterval): Pair<Int, Int> {


        val timeInInterval = candleInterval.minutesInInterval
        val remainder = minute % timeInInterval

        val addition = timeInInterval - remainder
        var nextMinuteInInterval = minute + addition

        // Check if hour increased
        if (nextMinuteInInterval >= 60){
            val nextHourInInterval = hour + 1
            nextMinuteInInterval = 0
            return Pair(nextHourInInterval, nextMinuteInInterval)
        }
        return Pair(hour, nextMinuteInInterval)
    }


    private fun getFirstCandleOfNextBusinessDay(
        ts: Long,
        candleInterval: CandleInterval
    ): Long {
        // Add 24hrs until next bus day
        var newTime = ts
        while (!isBusinessDay(newTime)) {
            newTime += 24 * 60 * 60 * 1000
        }

        // Reset cal time with first candle of new day
        val newCal = Calendar.getInstance().apply { timeInMillis = newTime }
        newCal.set(Calendar.MILLISECOND, 0)
        newCal.set(Calendar.SECOND, 0)
        newCal.set(Calendar.HOUR_OF_DAY, candleInterval.hourOfFirstCandle)
        newCal.set(Calendar.MINUTE, candleInterval.minuteOfFirstCandle)
        return newCal.timeInMillis
    }

    private fun isBusinessDay(ts: Long): Boolean {

        val cal = Calendar.getInstance().apply {
            timeInMillis = ts
        }

        if (cal.get(Calendar.DAY_OF_WEEK) in 1..6) {
            return true
        }

        return false
    }

    private fun isAfterHours(ts: Long): Boolean {
        val cal = Calendar.getInstance().apply { timeInMillis = ts }

        if (cal.get(Calendar.HOUR_OF_DAY) >= 16) {
            return true
        }
        return false
    }

    private fun isPremarket(ts: Long): Boolean {
        val cal = Calendar.getInstance().apply { timeInMillis = ts }
        if (cal.get(Calendar.HOUR_OF_DAY) < 9) {
            return true
        } else if (cal.get(Calendar.HOUR_OF_DAY) == 9 && cal.get(Calendar.MINUTE) < 30) {
            return true
        }
        return false
    }

    /*
    TODO
        Find list of all market hollidays, Use an api to keep it clean if possible
     */
//    private fun isMarketHolliday(ts: Long): Boolean {
//
//    }

}


fun main() {
}