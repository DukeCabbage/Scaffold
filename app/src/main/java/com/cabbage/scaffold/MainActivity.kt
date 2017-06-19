package com.cabbage.scaffold

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.cabbage.scaffold.mvp.MvpView
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : AppCompatActivity(), MvpView {

    var presenter: MainPresenter? = null

    @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar
    @BindView(R.id.fab) lateinit var mFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)

        RxView.clicks(mFab).subscribeBy(onNext = { presenter?.ensureLocationPermission() })
    }

    public override fun onStart() {
        super.onStart()
        if (presenter == null) presenter = MainPresenter(RxPermissions(this))
        presenter?.attachView(this)
        presenter?.ensureLocationPermission()
    }

    public override fun onStop() {
        super.onStop()
        presenter?.detachView()
    }

    fun showLocationPermissionResult(granted: Boolean) {
        Toast.makeText(this, "Granted: $granted", Toast.LENGTH_SHORT).show()
    }
}
