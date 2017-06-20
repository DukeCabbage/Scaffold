package com.cabbage.scaffold.demo

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ScaffoldApplication
import com.cabbage.scaffold.dagger.ActivityComponent
import com.cabbage.scaffold.dagger.ActivityModule
import com.cabbage.scaffold.dagger.DaggerActivityComponent
import com.cabbage.scaffold.mvp.MvpView
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MvpView {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var rxPermission: RxPermissions

    @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar
    @BindView(R.id.fab) lateinit var mFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)

        val actComponent: ActivityComponent = DaggerActivityComponent.builder()
                .appComponent(ScaffoldApplication.appComponent)
                .activityModule(ActivityModule(this))
                .build()

        actComponent.inject(this)

        RxView.clicks(mFab).subscribeBy(onNext = { presenter.ensureLocationPermission() })
    }

    public override fun onStart() {
        super.onStart()
        presenter.mvpView = this
        presenter.ensureLocationPermission()
    }

    public override fun onStop() {
        super.onStop()
        presenter.mvpView = null
    }

    fun showLocationPermissionResult(granted: Boolean) {
        Toast.makeText(this, "Granted: $granted", Toast.LENGTH_SHORT).show()
    }
}
