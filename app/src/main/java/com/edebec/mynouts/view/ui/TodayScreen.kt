package com.edebec.mynouts.view.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edebec.mynouts.ui.theme.MyNoutsTheme


@Preview(name = "NoutCardPreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun NoutCardPreview() {
    MyNoutsTheme {
        NoutCard()
    }
}

@Preview(name = "CategoryItemPreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun CategoryItemPreview() {
    MyNoutsTheme {
        CategoryItem(text = "Category-item")
    }
}

@Composable
fun CategoryItem(modifier: Modifier = Modifier, text: String) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(Color(0xFFABB2CA))
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium, color = Color(0xFF1D2333))
    }
}

@Composable
private fun NoutCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Title nout",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium
                )
                Checkbox(checked = false, onCheckedChange = {})
            }
            Text(
                text = "Lorem ipsum dolor sit amet consectetur. Viverra a tristique vel volutpat et eget dui. Iaculis maecenas consequat sit pretium id faucibus.",
                modifier = Modifier.padding(top = 8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

