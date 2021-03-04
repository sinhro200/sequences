package com.sinhro.sequences.model

import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

abstract class SimpleSequenceGenerator<T> : ISequenceGenerator {

    private var onReady: ((newValues: List<String>) -> Unit)? = null

    private val sequence: MutableList<T> by lazy { mutableListOf() }

    private val scope = MainScope()

    abstract suspend fun nextValues(currentSequence: List<T>, count: Int): List<T>

    override fun generateNext(count: Int): Job {
        return scope.launch {
            val l = nextValues(sequence, count)
            sequence.addAll(l)
            onReady?.invoke(l.map { it.toString() })
        }
    }

    override fun onNewValuesReady(consumer: (newValues: List<String>) -> Unit) {
        onReady = consumer
    }

    override fun getAll(): List<String> {
        return sequence.map { it.toString() }
    }

    override suspend fun get(i: Int): String {
        if (sequence.size >= i)
            generateNext(i - sequence.size).join()
        return sequence[i].toString()
    }

    override fun restart() {
        sequence.clear()
    }
}