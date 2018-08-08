package com.cabbage.scaffold.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
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
import com.cabbage.scaffold.ui.gallery.ViewImageActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.tvVersionName) lateinit var tvVersionName: TextView
    @BindView(R.id.tvVersionCode) lateinit var tvVersionCode: TextView

    @OnClick(R.id.fab)
    fun fabOnClick() {
        Intent(this, NextActivity::class.java)
                .also { startActivity(it) }



//        val intent = intent
//        val k = 6
    }

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
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityComponent.inject(this)

        tvVersionName.text = BuildConfig.VERSION_NAME
        tvVersionCode.text = "${BuildConfig.VERSION_CODE}"
    }

    public override fun onStart() {
        super.onStart()
        Timber.v("onStart")

        presenter.attachView(this)
        presenter.ensureLocationPermission(rxPermission)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
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
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}