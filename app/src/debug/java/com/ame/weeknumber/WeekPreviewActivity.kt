package com.ame.weeknumber

import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.google.android.glance.tools.viewer.GlanceSnapshot
import com.google.android.glance.tools.viewer.GlanceViewerActivity

@OptIn(ExperimentalGlanceRemoteViewsApi::class)
class WeekPreviewActivity : GlanceViewerActivity() {

    override fun getProviders() = listOf(
        WeekWidgetReceiver::class.java,
    )

    override suspend fun getGlanceSnapshot(
        receiver: Class<out GlanceAppWidgetReceiver>
    ): GlanceSnapshot {
        return when (receiver) {
            WeekWidgetReceiver::class.java -> GlanceSnapshot(
                instance = WeekWidget,
                state = mutablePreferencesOf(
                )
            )
            else -> throw IllegalArgumentException()
        }
    }
}