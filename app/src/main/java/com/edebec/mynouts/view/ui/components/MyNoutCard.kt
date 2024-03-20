package com.edebec.mynouts.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.edebec.mynouts.view.ui.model.Nout

/*@Preview(name = "NoutSListPreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
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

    val lista2: List<Nout> = emptyList()
    MyNoutsTheme {
        TodayScreen(nouts = lista)
    }
}*/

@Composable
fun NoutsList(modifier: Modifier = Modifier, nouts: List<Nout>) {
    // method separateNouts will be in the viewmodel
    val testList = separateNouts(nouts)

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)) {
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
                if (nout.isCheckable) {
                    Checkbox(checked = nout.isChecked, onCheckedChange = {})
                }
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

private fun isPrimeNumber(number: Int): Boolean {
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
