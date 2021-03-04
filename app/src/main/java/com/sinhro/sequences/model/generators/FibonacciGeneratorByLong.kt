package com.sinhro.sequences.model.generators

import com.sinhro.sequences.model.SimpleSequenceGenerator
import java.lang.ArithmeticException

class FibonacciGeneratorByLong : SimpleSequenceGenerator<Long>() {

    private var gotMaxValue = false

    override suspend fun nextValues(currentSequence: List<Long>, count: Int): List<Long> {
        val newValues = mutableListOf<Long>()
        if (gotMaxValue)
            return newValues

        var cnt = count

        if (currentSequence.isEmpty()) {
            newValues.add(1)
            cnt--
            if (cnt == 0)
                return newValues
            else {
                newValues.add(1)
                cnt--
            }
        }
        if (currentSequence.size == 1) {
            newValues.add(1)
            cnt--
        }
        if (cnt > 0) {
            var cur: Long =
                if (currentSequence.isEmpty()) newValues.last() else currentSequence.last()
            var prev: Long =
                if (currentSequence.isEmpty())
                    newValues[newValues.size - 2]
                else
                    currentSequence[currentSequence.size - 2]
            var newValue: Long
            while (cnt-- > 0) {
                try {
                    newValue = Math.addExact(cur, prev)
                } catch (e: ArithmeticException){
                    gotMaxValue = true
                    return newValues
                }
                newValues.add(newValue)
                prev = cur
                cur = newValue
            }
        }
        return newValues
    }

    override fun restart() {
        super.restart()
        gotMaxValue = false
    }
}