package com.sinhro.sequences.model

class PrimesBruteForceGenerator : SimpleSequenceGenerator<Int>() {

    override suspend fun nextValues(currentSequence: List<Int>, count: Int): List<Int> {
        val primeNumbers = mutableListOf<Int>()
        if (count <= 0)
            return primeNumbers

        var cnt = count
        if (currentSequence.isEmpty()) {
            primeNumbers.add(2)
            cnt--
            if (cnt==0)
                return primeNumbers
            else{
                primeNumbers.add(3)
                cnt--
            }
        }
        if (currentSequence.size == 1) {
            primeNumbers.add(3)
            cnt--
        }

        var curNumber =
            if (currentSequence.isEmpty()) primeNumbers.last() else currentSequence.last()

        while (cnt > 0) {
            curNumber += 2
            if (isPrime(curNumber)) {
                primeNumbers.add(curNumber)
                cnt--
            }
        }
        return primeNumbers
    }

    private fun isPrime(number: Int): Boolean {
        var i = 2
        while (i * i <= number) {
            if (number % i == 0) {
                return false
            }
            i++
        }
        return true
    }
}