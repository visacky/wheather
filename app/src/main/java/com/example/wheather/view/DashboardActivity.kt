package com.example.wheather.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.wheather.R
import com.example.wheather.databinding.ActivityMainBinding
import com.example.wheather.extension.hasNetwork
import com.example.wheather.extension.showTost
import com.example.wheather.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val viewModel : DashboardViewModel by viewModel()
    private var isLoading: Boolean = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setSupportActionBar(toolbar)
        checkNetworkExists()
        initProgressbar()
    }

    private fun checkNetworkExists() {
        if (!this.hasNetwork()) {
            this.showTost(getString(R.string.internet_connection))
        }
    }

    private fun initProgressbar() {
        viewModel.isLoadingLiveData.observe(this, Observer {
            isLoading = it
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                binding.invalidateAll()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
