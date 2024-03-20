package com.edebec.mynouts.view.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmptyState(modifier: Modifier = Modifier, @DrawableRes illustration: Int, @StringRes title: Int, @StringRes message: Int) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .widthIn(max = 318.dp)
                .padding(bottom = 61.dp),
            textAlign = TextAlign.Center
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(painter = painterResource(id = illustration), contentDescription = "Illustration", modifier = Modifier.size(268.dp))
            Text(
                text = stringResource(id = message),
                style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}