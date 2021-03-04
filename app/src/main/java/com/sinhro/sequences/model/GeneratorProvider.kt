package com.sinhro.sequences.model

import com.sinhro.sequences.model.generators.FibonacciGeneratorByBigDecimal
import com.sinhro.sequences.model.generators.FibonacciGeneratorByLong
import com.sinhro.sequences.model.generators.PrimesBruteForceGenerator

object GeneratorProvider {
    val primesGenerator : ISequenceGenerator by lazy {
        PrimesBruteForceGenerator().also {
            it.generateNext(10)
        }
    }

    val fibonacciGenerator: ISequenceGenerator by lazy {
        FibonacciGeneratorByLong().also {
            it.generateNext(10)
        }
    }

    val fibonacciGeneratorByBigDecimal: ISequenceGenerator by lazy {
        FibonacciGeneratorByBigDecimal().also {
            it.generateNext(10)
        }
    }
}