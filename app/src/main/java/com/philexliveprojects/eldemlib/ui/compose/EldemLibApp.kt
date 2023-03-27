package com.philexliveprojects.eldemlib.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.philexliveprojects.eldemlib.ui.compose.*

/*TODO Implement windowSizeClass and create adaptive layouts*/
@Composable
fun EldemLibApp(windowWidthSizeClass: WindowWidthSizeClass) {
    val navController = rememberNavController()

    EldemLibNavHost(
        navController = navController
    )
}

@Composable
fun EldemLibNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE
    ) {
        composable(route = HOME_ROUTE) {
            HomeScreen(
                onArticleClick = { navController.navigate("$ARTICLE/$it") },
                onCategoryClick = { navController.navigate("$CATEGORY/$it") },
                onSearchClicked = { navController.navigate("$SEARCH/$it") },
            )
        }

        composable(
            route = CATEGORY_ROUTE,
            arguments = listOf(navArgument(CATEGORY_ID) { type = NavType.StringType })
        ) {
            CategoryScreen(
                category = checkNotNull(it.arguments?.getString(CATEGORY_ID)),
                onArticleClick = { navController.navigate("$ARTICLE/$it") },
                onSearchClicked = { navController.navigate("$SEARCH/$it") }
            )
        }

        composable(
            route = ARTICLE_ROUTE,
            arguments = listOf(navArgument(ARTICLE_ID) { type = NavType.LongType })
        ) {
            ArticleScreen(
                onImageClicked = { navController.navigate("$IMAGE/$it") }
            )
        }

        composable(
            route = IMAGE_SCREEN,
            arguments = listOf(navArgument(IMAGE_URL) { type = NavType.StringType })
        ) { backStackEntry ->
            ImageScreen(backStackEntry.arguments?.getString(IMAGE_URL) ?: "")
        }

        composable(
            route = SEARCH_ROUTE,
            arguments = listOf(navArgument(SEARCH_SCOPE) { type = NavType.StringType })
        ) { backStackEntry ->
            SearchScreen(
                searchScope = backStackEntry.arguments?.getString(SEARCH_SCOPE) ?: GLOBAL,
                onNavigateBack = { navController.navigateUp() })
        }
    }
}