package com.sinhro.sequences.model.generators

import com.sinhro.sequences.model.SimpleSequenceGenerator
import java.math.BigDecimal

class FibonacciGeneratorByBigDecimal : SimpleSequenceGenerator<BigDecimal>() {

    private var gotMaxValue = false

    override suspend fun nextValues(
        currentSequence: List<BigDecimal>,
        count: Int
    ): List<BigDecimal> {
        val newValues = mutableListOf<BigDecimal>()
        if (gotMaxValue)
            return newValues

        var cnt = count

        if (currentSequence.isEmpty()) {
            newValues.add(BigDecimal(1))
            cnt--
            if (cnt == 0)
                return newValues
            else {
                newValues.add(BigDecimal(1))
                cnt--
            }
        }
        if (currentSequence.size == 1) {
            newValues.add(BigDecimal(1))
            cnt--
        }
        if (cnt > 0) {
            var cur: BigDecimal =
                if (currentSequence.isEmpty()) newValues.last() else currentSequence.last()
            var prev: BigDecimal =
                if (currentSequence.isEmpty())
                    newValues[newValues.size - 2]
                else
                    currentSequence[currentSequence.size - 2]
            var newValue: BigDecimal
            while (cnt-- > 0) {
                newValue = cur.plus(prev)
                newValues.add(newValue)
                prev = cur
                cur = newValue
            }
        }
        return newValues
    }
}