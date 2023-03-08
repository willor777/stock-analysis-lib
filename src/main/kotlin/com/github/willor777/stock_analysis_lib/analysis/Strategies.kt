package com.github.willor777.stock_analysis_lib.analysis

enum class Strategies(val displayName: String, val description: String) {
    TEST_STRATEGY(
        "Test Strategy",
        "Simple Strategy designed to throw a trigger on every call to analyze() for every chart." +
                "Triggers are random -1, 0, 1"
    ),

    PREMARKET_RANGE_BREAK(
        "Pre-Market Range Break",
        "Classic Strategy designed to throw a trigger when the Pre-Market High or Low level has been " +
                "breached with significance (by a fair amount)."
    ),

    RSRW_Basic(
        "RS-RW Basic",
        "Classic Strategy that tests a stock chart's % movement vrs SPY % movement"
    )
}