package com.philexliveprojects.eldemlib.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.philexliveprojects.eldemlib.data.AppContainer
import com.philexliveprojects.eldemlib.ui.*
import com.philexliveprojects.eldemlib.ui.screen.*

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onBottomSheetExpand: () -> Unit = {},
    editMode: Boolean = false
) {
    NavHost(
        navController = navHostController,
        startDestination = HOME_ROUTE,
        modifier = modifier.fillMaxSize()
    ) {
        // Home Screen
        composable(route = HOME_ROUTE) {
            HomeScreen(
                onCategoryClicked = { groupId ->
                    navHostController.navigate("$GROUP/$groupId")
                },
                onSearchClicked = { tag ->
                    navHostController.navigate("$SEARCH/$tag")
                },
                editMode = editMode,
                onBottomSheetExpand = onBottomSheetExpand
            )
        }

        // Group Screen
        composable(
            route = GROUP_ROUTE,
            arguments = listOf(navArgument(GROUP_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            GroupScreen(
                groupId = backStackEntry.arguments?.getString(GROUP_ID) ?: "",
                onUnitClicked = { unitId ->
                    navHostController.navigate("$UNIT/$unitId")
                },
                onSearchClicked = { tag ->
                    navHostController.navigate("$SEARCH/$tag")
                }
            )
        }

        // Unit Screen
        composable(
            route = UNIT_ROUTE,
            arguments = listOf(navArgument(UNIT_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            ArticleScreen(
                pieceId = backStackEntry.arguments?.getInt(UNIT_ID) ?: -1,
                onImageClicked = { imgUrl ->
                    navHostController.navigate("$IMAGE/$imgUrl")
                }
            )
        }

        // Image screen receives an imgUrl
        composable(
            route = IMAGE_SCREEN,
            arguments = listOf(navArgument(IMAGE_URL) { type = NavType.StringType })
        ) { backStackEntry ->
            ImageScreen(backStackEntry.arguments?.getString(IMAGE_URL) ?: "")
        }

        // Search Screen
        composable(
            route = SEARCH_ROUTE,
            arguments = listOf(navArgument(SEARCH_SCOPE) { type = NavType.StringType })
        ) { backStackEntry ->
            SearchScreen(
                searchScope = backStackEntry.arguments?.getString(SEARCH_SCOPE) ?: GLOBAL,
                onNavigateBack = {navHostController.navigateUp()})
        }
    }
}