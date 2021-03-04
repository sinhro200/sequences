package com.sinhro.sequences.model

import kotlinx.coroutines.Job

interface ISequenceGenerator {
    fun generateNext(count: Int): Job
    fun onNewValuesReady(consumer: (newValues: List<String>) -> Unit)
    fun getAll(): List<String>
    suspend fun get(i: Int): String
    fun restart()
}