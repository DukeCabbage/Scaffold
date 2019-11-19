package com.cabbage.scaffold.ui.main

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.cabbage.scaffold.BuildConfig
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.shouldUseAltTheme
import com.cabbage.scaffold.ui.toggleAltTheme
import com.cabbage.scaffold.ui.base.BaseActivity
import com.cabbage.scaffold.ui.counter.view.CounterActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.include_app_bar.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var rxPermission: RxPermissions

    private var clickSubscription: Disposable? = null
    private var permissionSubscription: Disposable? = null

    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        Timber.v("onCreate")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        tvVersionName.text = BuildConfig.VERSION_NAME
        tvVersionCode.text = "${BuildConfig.VERSION_CODE}"

        fab.setOnClickListener {
            Intent(this, NextActivity::class.java).also(::startActivity)
        }

        fab_theme_1.setOnClickListener {
            if (shouldUseAltTheme()) {
                toggleAltTheme(false)
                recreate()
            }
        }

        fab_theme_2.setOnClickListener {
            if (!shouldUseAltTheme()) {
                toggleAltTheme(true)
                recreate()
            }
        }
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val message = when (item.itemId) {
            R.id.action_refresh -> "Refresh"
            R.id.action_share -> "Share"
            R.id.action_favorite -> "Favourite"
            else -> null
        }

        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
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