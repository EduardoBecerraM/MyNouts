package com.edebec.mynouts.view.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.edebec.mynouts.R
import com.edebec.mynouts.ui.theme.MyNoutsTheme

@Preview(name = "BottomMenuPreview", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun BottomMenuPreview() {
    MyNoutsTheme {
        BottomNavigation()
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    var selectedDestination by rememberSaveable { mutableStateOf(NoutsRoute.TODAY) }

    NavigationBar(modifier = modifier.fillMaxWidth(), containerColor = MaterialTheme.colorScheme.surface) {
        TOP_LEVEL_DESTINATIONS.forEach { noutsDestination ->
            NavigationBarItem(
                selected = selectedDestination == noutsDestination.route,
                onClick = { selectedDestination = noutsDestination.route },
                icon = {
                    Icon(
                        painter = painterResource(id = if (selectedDestination == noutsDestination.route) noutsDestination.selectedIcon else noutsDestination.unselectedIcon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = noutsDestination.iconTextId), style = MaterialTheme.typography.labelMedium)
                })
        }
    }
}

object NoutsRoute {
    const val TODAY = "Today"
    const val MY_NOUTS = "MyNouts"
    const val DONED = "Doned"
}

data class NoutsTopLevelDestination(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val iconTextId: Int
)

val TOP_LEVEL_DESTINATIONS = listOf(
    NoutsTopLevelDestination(
        route = NoutsRoute.TODAY,
        selectedIcon = R.drawable.circle_filled,
        unselectedIcon = R.drawable.circle_outlined,
        iconTextId = R.string.nouts_top_level_destination_today
    ),
    NoutsTopLevelDestination(
        route = NoutsRoute.MY_NOUTS,
        selectedIcon = R.drawable.today_calendar_filled,
        unselectedIcon = R.drawable.today_calendar,
        iconTextId = R.string.nouts_top_level_destination_my_nouts
    ),
    NoutsTopLevelDestination(
        route = NoutsRoute.DONED,
        selectedIcon = R.drawable.check,
        unselectedIcon = R.drawable.check,
        iconTextId = R.string.nouts_top_level_destination_doned
    )
)