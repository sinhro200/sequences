package com.sinhro.sequences.model

interface ISequenceGenerator {
    fun generateNext(count: Int)
    fun onNewValuesReady(consumer: (newValues: List<String>) -> Unit)
    fun getAll(): List<String>
    fun restart()
}