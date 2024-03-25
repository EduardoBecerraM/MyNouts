package com.edebec.mynouts.view.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import com.edebec.mynouts.R
import com.edebec.mynouts.view.ui.components.EmptyState
import com.edebec.mynouts.view.ui.model.Routes


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    var selectedDestination by rememberSaveable { mutableStateOf(Routes.TodayScreen.id) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (selectedDestination) {
            Routes.TodayScreen.id -> {
                TodayScreen(modifier = Modifier.weight(1f), nouts = emptyList())
            }

            Routes.MyNoutsScreen.id -> {
                MyNoutsScreen(modifier = Modifier.weight(1f), nouts = emptyList(), navHostController = navHostController)
            }

            else -> {
                EmptyState(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    illustration = R.drawable.undraw_completed_m9ci_1,
                    title = R.string.no_screen_emptystate_title,
                    message = R.string.no_screen_emptystate_message
                )
            }
        }

        NavigationBar(modifier = modifier.fillMaxWidth(), containerColor = MaterialTheme.colorScheme.surface) {
            TOP_LEVEL_DESTINATIONS.forEach { noutsDestination ->
                NavigationBarItem(
                    selected = selectedDestination == noutsDestination.route.id,
                    onClick = { selectedDestination = noutsDestination.route.id },
                    icon = {
                        Icon(
                            painter = painterResource(id = if (selectedDestination == noutsDestination.route.id) noutsDestination.selectedIcon else noutsDestination.unselectedIcon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = stringResource(id = noutsDestination.iconTextId), style = MaterialTheme.typography.labelMedium)
                    }
                )
            }
        }
    }
}

data class NoutsTopLevelDestination(
    val route: Routes,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val iconTextId: Int
)

val TOP_LEVEL_DESTINATIONS = listOf(
    NoutsTopLevelDestination(
        route = Routes.TodayScreen,
        selectedIcon = R.drawable.circle_filled,
        unselectedIcon = R.drawable.circle_outlined,
        iconTextId = R.string.nouts_top_level_destination_today
    ),
    NoutsTopLevelDestination(
        route = Routes.MyNoutsScreen,
        selectedIcon = R.drawable.today_calendar_filled,
        unselectedIcon = R.drawable.today_calendar,
        iconTextId = R.string.nouts_top_level_destination_my_nouts
    ),
    NoutsTopLevelDestination(
        route = Routes.DonedScreen,
        selectedIcon = R.drawable.check,
        unselectedIcon = R.drawable.check,
        iconTextId = R.string.nouts_top_level_destination_doned
    )
)