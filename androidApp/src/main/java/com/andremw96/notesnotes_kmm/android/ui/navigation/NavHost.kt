package com.andremw96.notesnotes_kmm.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andremw96.notesnotes_kmm.android.ui.listnotes.ListNoteScreen
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
            LoginScreen(viewModel = loginViewModel) {
                navController.navigate(NavGraphConstant.note_list)
            }
        }
        composable(NavGraphConstant.note_list) {
            ListNoteScreen()
        }
    }
}
