package com.edebec.mynouts.view.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.edebec.mynouts.R
import com.edebec.mynouts.view.ui.components.EmptyState
import com.edebec.mynouts.view.ui.components.NoutsList
import com.edebec.mynouts.view.ui.model.Nout

@Composable
fun TodayScreen(modifier: Modifier = Modifier, nouts: List<Nout>) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (nouts.isNotEmpty()) {
            Text(
                text = "Good morning!",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(vertical = 28.dp)
                    .fillMaxWidth()
            )
            NoutsList(modifier = Modifier.fillMaxSize(), nouts = nouts)
        } else {
            EmptyState(
                illustration = R.drawable.undraw_completed_m9ci_1,
                title = R.string.today_screen_emptystate_title,
                message = R.string.today_screen_emptystate_message
            )
        }
    }
}
