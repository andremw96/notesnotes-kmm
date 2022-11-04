package com.andremw96.notesnotes_kmm.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andremw96.notesnotes_kmm.android.ui.listnotes.ListNoteScreen
import com.andremw96.notesnotes_kmm.android.ui.listnotes.ListNoteViewModel
import com.andremw96.notesnotes_kmm.android.ui.login.LoginFormState
import com.andremw96.notesnotes_kmm.android.ui.login.LoginScreen
import com.andremw96.notesnotes_kmm.android.ui.login.LoginViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NotesNotesNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = NavGraphConstant.login) {
        composable(NavGraphConstant.login) {
            val loginViewModel: LoginViewModel = getViewModel()
            loginViewModel.checkLogin()

            val state = loginViewModel.loginFormState.observeAsState(
                initial = LoginFormState()
            ).value

            LaunchedEffect(key1 = state) {
                if (state.accessToken != null && state.username != "") {
                    navController.popBackStack(NavGraphConstant.login, true)
                    navController.navigate(NavGraphConstant.note_list)
                }
            }

            LoginScreen(viewModel = loginViewModel) {
                navController.popBackStack(NavGraphConstant.login, true)
                navController.navigate(NavGraphConstant.note_list)
            }
        }
        composable(NavGraphConstant.note_list) {
            val viewmodel: ListNoteViewModel = getViewModel()

            LaunchedEffect(key1 = Unit) {
                viewmodel.fetchData()
            }

            ListNoteScreen(viewmodel) {
                navController.navigate(route = NavGraphConstant.login) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
                navController.graph.setStartDestination(NavGraphConstant.login)
            }
        }
    }
}
