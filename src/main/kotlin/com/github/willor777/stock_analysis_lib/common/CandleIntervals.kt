package com.github.willor777.stock_analysis_lib.common

enum class CandleInterval(
    val timeOfFirstCandle: String,
    val hourOfFirstCandle: Int,
    val minuteOfFirstCandle: Int,
    val serverCode: String,
    val minutesInInterval: Int,
    ) {
    M1("09:31", 9, 31, "1m", 1),
    M5("09:35", 9, 35, "5m", 5),
    M15("09:45", 9, 45, "15m", 15),
    M30("10:00", 10, 0, "30m", 30),
    H1("10:30", 10, 30, "1h", 60),
    D1("09:30", 9, 30, "1d", 1440),
}