package com.edebec.mynouts.view.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edebec.mynouts.R
import com.edebec.mynouts.ui.theme.MyNoutsTheme
import com.edebec.mynouts.view.ui.components.EmptyState
import com.edebec.mynouts.view.ui.components.NoutsList
import com.edebec.mynouts.view.ui.model.Nout


@Preview
@Composable
private fun MyNoutsScreenPreview() {
    MyNoutsTheme {
        SearchBarMain()
    }
}

@Composable
fun MyNoutsScreen(modifier: Modifier = Modifier, nouts: List<Nout>) {
    Scaffold(modifier = modifier, floatingActionButton = {
        ExtendedFloatingActionButton(
            onClick = { },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            text = {
                Text(text = stringResource(id = R.string.myNouts_screen_newNout))
            }
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (nouts.isNotEmpty()) {
                SearchBarMain(modifier = Modifier.padding(bottom = 28.dp))
                NoutsList(modifier = Modifier.fillMaxSize(), nouts = nouts)
            } else {
                EmptyState(
                    illustration = R.drawable.undraw_modern_design_re_dlp8_1,
                    title = R.string.myNouts_screen_emptystate_title,
                    message = R.string.myNouts_screen_emptystate_message
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarMain(modifier: Modifier = Modifier) {
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(
                text = stringResource(id = R.string.myNouts_screen_searchBar_placeholder),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        },
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
    ) {}
}