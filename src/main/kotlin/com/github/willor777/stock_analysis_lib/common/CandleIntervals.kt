package com.github.willor777.stock_analysis_lib.common

enum class CandleInterval(
    val timeOfFirstCandle: String,
    val hourOfFirstCandle: Int,
    val minuteOfFirstCandle: Int,
    val serverCode: String
    ) {
    M1("09:31", 9, 31, "1m"),
    M5("09:35", 9, 35, "5m"),
    M15("09:45", 9, 45, "15m"),
    M30("10:00", 10, 0, "30m"),
    H1("10:30", 10, 30, "1h"),
    D1("09:30", 9, 30, "1d"),
}