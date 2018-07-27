package com.cabbage.scaffold.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cabbage.scaffold.ScaffoldApplication
import com.cabbage.scaffold.dagger.activity.ActivityComponent
import com.cabbage.scaffold.dagger.activity.ActivityModule
import com.cabbage.scaffold.dagger.activity.ConfigPersistentComponent
import com.cabbage.scaffold.dagger.activity.DaggerConfigPersistentComponent
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private const val KEY_ACTIVITY_ID = "ACTIVITY_ID"
        private val NextId = AtomicLong(0)
        private val ComponentMap: MutableMap<Long, ConfigPersistentComponent> = HashMap()
    }

    protected lateinit var activityComponent: ActivityComponent
    private var mActivityId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NextId.getAndIncrement()
        mActivityId!!.let {
            val configPersistentComponent = ComponentMap[it]
                                            ?: DaggerConfigPersistentComponent.builder()
                                                    .appComponent(ScaffoldApplication.appComponent)
                                                    .build()

            if (!ComponentMap.containsKey(it)) {
                Timber.d("Adding ConfigPersistentComponent id=$mActivityId")
                ComponentMap[it] = configPersistentComponent
            }
            activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.v("Saving activity id=$mActivityId")
        outState.putLong(KEY_ACTIVITY_ID, mActivityId!!)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.d("Clearing ConfigPersistentComponent id=$mActivityId")
            ComponentMap.remove(mActivityId)
        }
        super.onDestroy()
    }
}