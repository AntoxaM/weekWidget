package com.ame.weeknumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ame.weeknumber.ui.theme.WeekNumberTheme
import java.time.LocalDate
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeekNumberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Month(MonthViewModel(LocalDate.now()))
                }
            }
        }
    }
}

@Composable
fun Month(month: MonthViewModel, modifier: Modifier = Modifier) {
    LazyColumn (modifier = modifier.fillMaxSize()) {
        item {
            Text(
                text = "W${month.week} ${month.dayOfWeek.name} / ${month.day} ${month.month.name} ${month.year}",
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        }
        item {
            Row(modifier = modifier.fillMaxWidth()) {
                val weight = modifier.fillMaxWidth().weight(1f)
                arrayOf("W", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun").map {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        modifier = weight
                    )

                }
            }
        }

        items(month.rows) {row ->
            WeekRow(modifier, row, month)
        }
    }
}

@Composable
private fun WeekRow(
    modifier: Modifier,
    row: MonthViewModel.Week,
    model: MonthViewModel
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = row.week.toString(),
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                color = if (row.isCurrent) Color.Red else Color.Blue
            ),
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        )
        for (d in row.days)
            Text(
                text = d.day.toString(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = when {
                        d.day == model.day -> Color.Red
                        d.isCurrent -> Color.Blue
                        else -> Color.LightGray
                    }
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeekNumberTheme {
        Month(MonthViewModel(LocalDate.now()))
    }
}