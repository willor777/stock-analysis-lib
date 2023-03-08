package com.github.willor777.stock_analysis_lib.charts

import com.tictactec.ta.lib.Core


internal class TaLibCore {


    companion object {
        private var taLibCore: Core? = null

        fun getTaLibCore(): Core {
            return buildTaLibCore()
        }

        private fun buildTaLibCore(): Core {
            if (taLibCore == null) {
                taLibCore = Core()
            }
            return taLibCore!!
        }
    }
}