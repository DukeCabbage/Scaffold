package com.cabbage.scaffold.ui.demo

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.cabbage.scaffold.BuildConfig
import com.cabbage.scaffold.R
import com.cabbage.scaffold.shouldUseAltTheme
import com.cabbage.scaffold.toggleAltTheme
import com.cabbage.scaffold.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.tvVersionName) lateinit var tvVersionName: TextView
    @BindView(R.id.tvVersionCode) lateinit var tvVersionCode: TextView

    @BindView(R.id.fab) lateinit var fab: FloatingActionButton

    @OnClick(R.id.fab_theme_1)
    fun theme1OnClick() {
        if (shouldUseAltTheme()) {
            toggleAltTheme(false)
            recreate()
        }
    }

    @OnClick(R.id.fab_theme_2)
    fun theme2OnClick() {
        if (!shouldUseAltTheme()) {
            toggleAltTheme(true)
            recreate()
        }
    }

    @Inject lateinit var presenter: MainContract.Presenter
    @Inject lateinit var rxPermission: RxPermissions

    private var clickSubscription: Disposable? = null

    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("onCreate")
        if (shouldUseAltTheme()) {
            setTheme(R.style.AltAppTheme_NoActionBar)
        } else {
            setTheme(R.style.AppTheme_NoActionBar)
        }

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityComponent.inject(this)

        tvVersionName.text = BuildConfig.VERSION_NAME
        tvVersionCode.text = "${BuildConfig.VERSION_CODE}"
    }

    public override fun onStart() {
        super.onStart()
        Timber.v("onStart")

        presenter.attachView(this)
        presenter.ensureLocationPermission(rxPermission)

        val list = arrayListOf("one", "two", "three", "four")
        val iterator = list.iterator()

        while (iterator.hasNext()) {
            if (iterator.next() == "two") iterator.remove()
        }

        list.forEach { Timber.d(it); }
    }

    public override fun onStop() {
        super.onStop()
        Timber.v("onStop")
        dialog?.dismiss()
        clickSubscription?.dispose()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.v("onDestroy")
    }

    override fun showLocationPermissionResult(granted: Boolean) {
        Toast.makeText(this, "Granted: $granted", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }
}