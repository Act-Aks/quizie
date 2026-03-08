package com.actaks.quizie.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.actaks.quizie.presentation.dashboard.DashboardScreen
import com.actaks.quizie.presentation.dashboard.DashboardState
import com.actaks.quizie.presentation.issueReport.IssueReportScreen
import com.actaks.quizie.presentation.issueReport.IssueReportState
import com.actaks.quizie.presentation.quiz.QuizScreen
import com.actaks.quizie.presentation.quiz.QuizState
import com.actaks.quizie.presentation.result.ResultScreen
import com.actaks.quizie.presentation.result.ResultState

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Route.DashboardScreen
    ) {
        composable<Route.DashboardScreen> {
            DashboardScreen(
                state = DashboardState()
            )
        }
        composable<Route.QuizScreen> {
            QuizScreen(
                state = QuizState()
            )
        }
        composable<Route.ResultScreen> {
            ResultScreen(
                state = ResultState()
            )
        }
        composable<Route.IssueReportScreen> {
            IssueReportScreen(
                state = IssueReportState()
            )
        }
    }
}