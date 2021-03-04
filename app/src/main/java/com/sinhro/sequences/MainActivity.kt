package com.sinhro.sequences

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sinhro.sequences.databinding.ActivityMainBinding
import com.sinhro.sequences.model.FibonacciGeneratorByLong
import com.sinhro.sequences.model.GeneratorProvider
import com.sinhro.sequences.model.ISequenceGenerator
import com.sinhro.sequences.model.PrimesBruteForceGenerator
import com.sinhro.sequences.ui.ScrollableSequenceAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var adapter : ScrollableSequenceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.main_menu_fibonacci-> {
                title = getString(R.string.fibonacci_title)
                adapter.sequenceGenerator = GeneratorProvider.fibonacciGenerator
                adapter.refreshData()
            }
            R.id.main_menu_primes-> {
                title = getString(R.string.primes_title)
                adapter.sequenceGenerator = GeneratorProvider.primesGenerator
                adapter.refreshData()
            }
            R.id.main_menu_refresh_generator-> {
                adapter.sequenceGenerator.restart()
                adapter.sequenceGenerator.generateNext(10)
                adapter.refreshData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFields(){
        title = getString(R.string.fibonacci_title)
        adapter = ScrollableSequenceAdapter(GeneratorProvider.fibonacciGenerator)
        elements_recycler_view.adapter = adapter
    }
}