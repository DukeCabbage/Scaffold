package com.cabbage.scaffold.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cabbage.scaffold.ScaffoldApplication
import com.cabbage.scaffold.dagger.ActivityComponent
import com.cabbage.scaffold.dagger.ActivityModule
import com.cabbage.scaffold.dagger.ConfigPersistentComponent
import com.cabbage.scaffold.dagger.DaggerConfigPersistentComponent
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong


abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private val KEY_ACTIVITY_ID = "ACTIVITY_ID"
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

        val configPersistentComponent = ComponentMap[mActivityId!!] ?: DaggerConfigPersistentComponent.builder()
                .appComponent(ScaffoldApplication.appComponent)
                .build()

        if (!ComponentMap.containsKey(mActivityId!!)) {
            ComponentMap.put(mActivityId!!, configPersistentComponent)
        }

        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.v("Saving activity id=$mActivityId")
        outState.putLong(KEY_ACTIVITY_ID, mActivityId!!)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=$mActivityId")
            ComponentMap.remove(mActivityId)
        }
        super.onDestroy()
    }
}
