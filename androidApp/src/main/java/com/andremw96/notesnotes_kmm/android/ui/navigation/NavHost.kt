package com.andremw96.notesnotes_kmm.android.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andremw96.notesnotes_kmm.android.model.AssetParamType
import com.andremw96.notesnotes_kmm.android.model.NoteItem
import com.andremw96.notesnotes_kmm.android.ui.addeditnote.AddEditNoteScreen
import com.andremw96.notesnotes_kmm.android.ui.addeditnote.AddEditNoteViewModel
import com.andremw96.notesnotes_kmm.android.ui.listnotes.ListNoteScreen
import com.andremw96.notesnotes_kmm.android.ui.listnotes.ListNoteViewModel
import com.andremw96.notesnotes_kmm.android.ui.login.LoginFormState
import com.andremw96.notesnotes_kmm.android.ui.login.LoginScreen
import com.andremw96.notesnotes_kmm.android.ui.login.LoginViewModel
import com.andremw96.notesnotes_kmm.android.ui.signup.SignupScreen
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

            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToNoteList = {
                    navController.popBackStack(NavGraphConstant.login, true)
                    navController.navigate(NavGraphConstant.note_list)
                },
                onNavigateToSignup = {
                    navController.navigate(NavGraphConstant.signup)
                }
            )
        }
        composable(
            route = NavGraphConstant.signup
        ) {
            SignupScreen()
        }
        composable(
            route = NavGraphConstant.note_list,
        ) {
            val viewmodel: ListNoteViewModel = getViewModel()

            LaunchedEffect(key1 = Unit) {
                viewmodel.fetchData()
            }

            ListNoteScreen(viewmodel, navController, onNavigateLogin = {
                navController.popBackStack(NavGraphConstant.note_list, true)
                navController.navigate(NavGraphConstant.login)
            })
        }
        composable(
            route = "${NavGraphConstant.add_edit_note}/{${NavGraphConstant.note_details_id_key}}",
            arguments = listOf(
                navArgument(NavGraphConstant.note_details_id_key) { type = AssetParamType() }
            ),
        ) {
            val viewModel: AddEditNoteViewModel = getViewModel()
            val noteItem = it.arguments?.getParcelable<NoteItem>(NavGraphConstant.note_details_id_key)

            LaunchedEffect(key1 = Unit){
                viewModel.initState(noteItem)
            }

            AddEditNoteScreen(
                viewModel = viewModel,
                navController = navController,
            ) {
                navController.popBackStack(NavGraphConstant.login, true)
                navController.navigate(NavGraphConstant.note_list)
            }
        }

    }
}
