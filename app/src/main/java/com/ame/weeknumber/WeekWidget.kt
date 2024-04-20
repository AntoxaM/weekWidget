package com.ame.weeknumber

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.preferencesOf
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.google.android.glance.appwidget.host.glance.GlanceAppWidgetHostPreview


object WeekWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            // create your AppWidget here
            GlanceTheme {
                WeekContent()
            }
        }
    }
    @Composable
    private fun WeekContent() {
        Column(modifier = GlanceModifier.clickable(
            onClick = actionStartActivity<MainActivity>()
        ).fillMaxWidth()){
            val textStyle = TextStyle(
                color = ColorProvider(color = Color.White),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Text(text = "Week",
                style = textStyle,
                modifier = GlanceModifier.fillMaxWidth()
            )
            Text(text = getCurrentWeek().toString(),
                style = textStyle,
                modifier = GlanceModifier.fillMaxWidth()
            )
        }
    }
}

class WeekWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = WeekWidget
}

@OptIn(ExperimentalGlanceRemoteViewsApi::class)
@Preview
@Composable
fun MyGlanceWidgetPreview() {
    // The size of the widget
    val displaySize = DpSize(50.dp, 50.dp)
    // Your GlanceAppWidget instance
    val instance = WeekWidget
    // Provide a state depending on the GlanceAppWidget state definition
    val state = preferencesOf()

    GlanceAppWidgetHostPreview(
//        modifier = Modifier.fillMaxSize(),
        glanceAppWidget = instance,
        state = state,
        displaySize = displaySize,
    )}
