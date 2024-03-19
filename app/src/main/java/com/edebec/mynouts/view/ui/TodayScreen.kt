package com.edebec.mynouts.view.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edebec.mynouts.ui.theme.MyNoutsTheme
import com.edebec.mynouts.view.ui.model.Nout

@Preview(name = "NoutSListPreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun NoutSListPreview() {
    val lista = listOf(
        Nout(
            id = 8261,
            title = "conclusionemque",
            description = "Lorem ipsum dolor sit amet consectetur. Viverra a tristique vel volutpat et eget dui. Iaculis maecenas consequat sit pretium id faucibus",
            datetime = "elementum",
            isChecked = false
        ),
        Nout(id = 6919L, title = "Jacobo", description = "Chassidy", datetime = "Tye", isChecked = true),
        Nout(id = 6507, title = "sollicitudin", description = "novum", datetime = "ante", isChecked = false),
        Nout(
            id = 8219,
            title = "mediocritatem",
            description = "Lorem ipsum dolor sit amet consectetur. Viverra a tristique vel volutpat et eget dui. Iaculis maecenas consequat sit pretium id faucibus",
            datetime = "altera",
            isChecked = false
        ),
        Nout(id = 7801, title = "posidonium", description = "quot", datetime = "dis", isChecked = false),
        Nout(
            id = 7645,
            title = "lorem",
            description = "Lorem ipsum dolor sit amet consectetur. Viverra a tristique vel volutpat et eget dui. Iaculis maecenas consequat sit pretium id faucibus",
            datetime = "similique",
            isChecked = false
        ),
        Nout(id = 8742, title = "laudem", description = "eu", datetime = "nisl", isChecked = false)
    )
    MyNoutsTheme {
        NoutsList(nouts = lista)
    }
}

/*
@Preview(name = "EmptyStatePreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun EmptyStatePreview() {
    MyNoutsTheme {
        EmptyState(
            illustration = R.drawable.undraw_completed_m9ci_1,
            title = R.string.today_screen_emptystate_title,
            message = R.string.today_screen_emptystate_message
        )
    }
}
*/


/*@Preview(name = "NoutCardPreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun NoutCardPreview() {
    MyNoutsTheme {
        NoutCard(
            nout = Nout(
                id = 4553,
                title = "sapien",
                description = "Lorem ipsum dolor sit amet consectetur. Viverra a tristique vel volutpat et eget dui. Iaculis maecenas consequat sit pretium id faucibus.",
                datetime = "integer",
                isChecked = true
            )
        )
    }
}*/

/*@Preview(name = "CategoryItemPreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun CategoryItemPreview() {
    MyNoutsTheme {
        CategoryItem(text = "Category-item")
    }
}*/

@Composable
fun NoutsList(modifier: Modifier = Modifier, nouts: List<Nout>) {
    // method separateNouts will be in the viewmodel
    val testList = separateNouts(nouts)

    Row(modifier = modifier.background(Color.White), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = testList.first, key = { it.id }) { nout ->
                NoutCard(nout = nout)
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = testList.second, key = { it.id }) { nout ->
                NoutCard(nout = nout)
            }
        }
    }
}

@Composable
fun EmptyState(modifier: Modifier = Modifier, @DrawableRes illustration: Int, @StringRes title: Int, @StringRes message: Int) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(61.dp)) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.widthIn(max = 318.dp),
            textAlign = TextAlign.Center
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(painter = painterResource(id = illustration), contentDescription = "Illustration")
            Text(
                text = stringResource(id = message),
                style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface
            )
        }
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
private fun NoutCard(modifier: Modifier = Modifier, nout: Nout) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = nout.title,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Checkbox(checked = nout.isChecked, onCheckedChange = {})
            }
            Text(
                text = nout.description,
                modifier = Modifier.padding(top = 8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


fun isPrimeNumber(number: Int): Boolean {
    return number % 2 == 0
}

private fun separateNouts(lista: List<Nout>): Pair<List<Nout>, List<Nout>> {
    val firstList = mutableListOf<Nout>()
    val secondList = mutableListOf<Nout>()

    for ((index, elemento) in lista.withIndex()) {
        if (isPrimeNumber(index + 1)) {
            secondList.add(elemento)
        } else {
            firstList.add(elemento)
        }
    }

    return firstList to secondList
}
