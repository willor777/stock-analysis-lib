package com.willor.stock_analysis_lib.common

enum class CandleInterval(
    val timeOfFirstCandle: String,
    val hourOfFirstCandle: Int,
    val minuteOfFirstCandle: Int,
    ) {
    M1("09:31", 9, 31),
    M5("09:35", 9, 35),
    M15("09:45", 9, 45),
    M30("10:00", 10, 0),
    H1("10:30", 10, 30),
    D1("09:30", 9, 30),
}