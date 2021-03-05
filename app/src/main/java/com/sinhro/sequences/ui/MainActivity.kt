package com.sinhro.sequences.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sinhro.sequences.R
import com.sinhro.sequences.databinding.ActivityMainBinding
import com.sinhro.sequences.model.GeneratorProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var adapter : ScrollableSequenceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)
        initFields()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.main_menu_fibonacci -> {
                setFibonacciGeneratorByLong()
            }
            R.id.main_menu_primes -> {
                setPrimesGenerator()
            }
            R.id.main_menu_refresh_generator -> {
                refreshGenerator()
            }
            R.id.main_menu_fibonacci_bigdec ->{
                setFibonacciGeneratorByBigDecimal()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFields(){
        title = getString(R.string.fibonacci_title)
        adapter = ScrollableSequenceAdapter(GeneratorProvider.fibonacciGenerator)
        elements_recycler_view.adapter = adapter
    }

    override fun onDestroy() {
        GeneratorProvider.fibonacciGenerator.restart()
        GeneratorProvider.fibonacciGeneratorByBigDecimal.restart()
        GeneratorProvider.primesGenerator.restart()
        super.onDestroy()
    }

    private fun setFibonacciGeneratorByLong(){
        title = getString(R.string.fibonacci_title)
        adapter.sequenceGenerator = GeneratorProvider.fibonacciGenerator
    }

    private fun setFibonacciGeneratorByBigDecimal(){
        title = getString(R.string.fibonacci_title_bigdec)
        adapter.sequenceGenerator = GeneratorProvider.fibonacciGeneratorByBigDecimal
    }

    private fun setPrimesGenerator(){
        title = getString(R.string.primes_title)
        adapter.sequenceGenerator = GeneratorProvider.primesGenerator
    }

    private fun refreshGenerator(){
        adapter.sequenceGenerator.restart()
        adapter.sequenceGenerator.generateNext(10)
        adapter.refreshData()
    }
}