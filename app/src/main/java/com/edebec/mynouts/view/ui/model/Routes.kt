package com.edebec.mynouts.view.ui.model

sealed class Routes(val id: String) {
    data object TodayScreen : Routes("todayScreen")
    data object MyNoutsScreen : Routes("myNoutsScreen")
    data object DonedScreen : Routes("donedScreen")
    data object NewNoutScreen : Routes("newNoutScreen/{noutId}") {
        fun createRoute(noutId: Int) = "newNoutScreen/$noutId"
    }
}