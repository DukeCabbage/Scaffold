package com.cabbage.scaffold.ui.main

import android.Manifest
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
import com.cabbage.scaffold.ui.shouldUseAltTheme
import com.cabbage.scaffold.ui.toggleAltTheme
import com.cabbage.scaffold.ui.base.BaseActivity
import com.cabbage.scaffold.ui.container.view.ContainerActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.tvVersionName) lateinit var tvVersionName: TextView
    @BindView(R.id.tvVersionCode) lateinit var tvVersionCode: TextView

    @OnClick(R.id.fab)
    fun fabOnClick() {
        Intent(this, ContainerActivity::class.java)
                .also { startActivity(it) }
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

    @Inject lateinit var rxPermission: RxPermissions

    private var clickSubscription: Disposable? = null
    private var permissionSubscription: Disposable? = null

    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        Timber.v("onCreate")
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        tvVersionName.text = BuildConfig.VERSION_NAME
        tvVersionCode.text = "${BuildConfig.VERSION_CODE}"
    }

    public override fun onStart() {
        super.onStart()
        Timber.v("onStart")

//        ensureLocationPermission(rxPermission)
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
        permissionSubscription?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.v("onDestroy")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun showLocationPermissionResult(granted: Boolean) {
        Toast.makeText(this, "Granted: $granted", Toast.LENGTH_SHORT).show()
    }

    private fun ensureLocationPermission(rxPermissions: RxPermissions) {

        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        permissionSubscription = rxPermissions.request(locationPermission)
                .subscribeBy(
                        onNext = { showLocationPermissionResult(it) },
                        onError = { e -> Timber.e(e) }
                )
    }
}