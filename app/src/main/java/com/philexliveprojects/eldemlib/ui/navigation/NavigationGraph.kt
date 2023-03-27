package com.philexliveprojects.eldemlib.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                onRecentClicked = { navHostController.navigate("$ARTICLE/$it") },
                onCategoryClicked = { navHostController.navigate("$CATEGORY/$it") },
                onSearchClicked = { navHostController.navigate("$SEARCH/$it") },
                editMode = editMode,
                onBottomSheetExpand = onBottomSheetExpand
            )
        }

        // Group Screen
        composable(
            route = CATEGORY_ROUTE,
            arguments = listOf(navArgument(CATEGORY_ID) { type = NavType.StringType })
        ) {
            CategoryScreen(
                category = checkNotNull(it.arguments?.getString(CATEGORY_ID)),
                onArticleClick = { navHostController.navigate("$ARTICLE/$it") },
                onSearchClicked = { navHostController.navigate("$SEARCH/$it") }
            )
        }

        // Unit Screen
        composable(
            route = ARTICLE_ROUTE,
            arguments = listOf(navArgument(ARTICLE_ID) { type = NavType.LongType })
        ) {
            ArticleScreen(
                onImageClicked = { navHostController.navigate("$IMAGE/$it") }
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
                onNavigateBack = { navHostController.navigateUp() })
        }
    }
}