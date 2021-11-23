package com.example.coolblueapptest.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.example.coolblueapptest.R
import com.example.coolblueapptest.di.component.DaggerProductPresenterComponent
import com.example.coolblueapptest.di.module.ProductPresenterModule
import com.example.coolblueapptest.main.contract.ProductContract
import com.example.coolblueapptest.main.presenter.ProductPresenter
import com.example.coolblueapptest.main.view.adapter.ProductAdapter
import com.example.coolblueapptest.util.Utils
import com.example.domain.entity.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var mainLayout: View
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var dialog: MaterialDialog
    @Inject
    lateinit var presenter: ProductPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerProductPresenterComponent.builder().productPresenterModule(
            ProductPresenterModule(this, this)
        ).build().inject(this)

        initView()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    /**
     * Init view.
     */
    private fun initView() {
        mainLayout = findViewById(R.id.mainLayout)
        refreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerView)

        refreshLayout.setOnRefreshListener(onRefreshListener)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
    }

    /**
     * Get data from server on Coroutine Dispatcher IO.
     */
    private fun getData() {
        lifecycleScope.launch(Dispatchers.IO) {
            if (Utils.isOnline()) {
                presenter.requestDataFromServer()
            } else {
                showErrorDialog(getString(R.string.request_error_no_internet))
            }
        }
    }

    /**
     * Define onRefreshListener.
     */
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        getData()
    }

    /**
     * Define onClickListener.
     */
    private val onClickListener = View.OnClickListener { view ->

    }

    override fun showProgress() {
        lifecycleScope.launch {
            refreshLayout.isRefreshing = true
            mainLayout.visibility = View.GONE
        }
    }

    override fun hideProgress() {
        lifecycleScope.launch {
            refreshLayout.isRefreshing = false
            mainLayout.visibility = View.VISIBLE
        }
    }

    override fun updateView(productData: ProductData) {
        lifecycleScope.launch {
            recyclerView.adapter = ProductAdapter(productData.products, onClickListener)
        }
    }

    override fun onResponseFailure(error: String) {
        showErrorDialog(error)
    }

    /**
     * Show error dialog when request failed.
     */
    private fun showErrorDialog(error: String) {
        lifecycleScope.launch {
            if (!this@MainActivity.isFinishing && !dialog.isShowing) {
                Utils.showDialog(dialog,
                    title = getString(R.string.request_error_title),
                    message = getString(
                        R.string.request_error_message,
                        error
                    ),
                    positive = getString(R.string.retry),
                    negative = getString(R.string.close),
                    positiveAction = {
                        getData()
                    },
                    negativeAction = {})
            }
        }
    }

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }
}
