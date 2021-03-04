package com.sinhro.sequences.model

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
}