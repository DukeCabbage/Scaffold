package com.cabbage.scaffold.ui.demo

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import com.cabbage.scaffold.BuildConfig
import com.cabbage.scaffold.R
import com.cabbage.scaffold.showAlertDialog
import com.cabbage.scaffold.ui.base.BaseActivity
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

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

        activityComponent.inject(this)

        tvVersionName.text = BuildConfig.VERSION_NAME
        tvVersionCode.text = "${BuildConfig.VERSION_CODE}"
    }

    public override fun onStart() {
        super.onStart()
        Timber.v("onStart")
        presenter.attachView(this)
        presenter.ensureLocationPermission(rxPermission)

        val clickCallback = { _: Any? ->
            dialog = this.showAlertDialog(
                    onPositive = {
                        val intent = Intent(this@MainActivity, NextActivity::class.java)
                        intent.putExtra("MEOW", "Here's the message")
                        startActivity(intent)

//                        presenter.ensureLocationPermission(rxPermission)
                    },
//                    onNegative = {
//                        this@MainActivity.toast("Canceled")
//                    },
                    cancelable = false
            )
        }

        clickSubscription = RxView.clicks(fab).subscribeBy(onNext = clickCallback)
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
}
