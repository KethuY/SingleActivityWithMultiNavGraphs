package com.kethu.yerramma.samng.navgraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kethu.yerramma.samng.featureauth.AuthGraph
import com.kethu.yerramma.samng.featureauth.CreateNewAccount
import com.kethu.yerramma.samng.featureauth.ForgotPassword
import com.kethu.yerramma.samng.featureauth.SignIn
import com.kethu.yerramma.samng.featureauth.SignUp
import com.kethu.yerramma.samng.featureauth.ui.CreateAccountScreen
import com.kethu.yerramma.samng.featureauth.ui.ForgotPasswordScreen
import com.kethu.yerramma.samng.featureauth.ui.SignInScreen
import com.kethu.yerramma.samng.featureauth.ui.SignUpScreen
import com.kethu.yerramma.samng.featuredashboard.DashboardGraph

fun NavGraphBuilder.authGraph(navController: NavController, startDes: Any = CreateNewAccount) {

    navigation<AuthGraph>(startDestination = startDes) {
        composable<CreateNewAccount> {
            CreateAccountScreen(onSignIn = {
                navController.navigate(SignIn)
            }, onSignUp = {
                navController.navigate(SignUp)
            })
        }

        composable<SignIn> {
            SignInScreen(
                onSignUp = {
                    navController.navigate(SignUp)
                }, onForgotPwd = {
                    navController.navigate(ForgotPassword)
                },
                onNavigateToDashboard = {
                    navigateToDashboard(navController)
                }
            )
        }

        composable<SignUp> {
            SignUpScreen {
                navigateToDashboard(navController)
            }
        }

        composable<ForgotPassword> {
            ForgotPasswordScreen {
                navController.popBackStack()
            }
        }
    }
}

private fun navigateToDashboard(navController: NavController) {
    navController.navigate(DashboardGraph) {
        popUpTo(AuthGraph) {
            inclusive = true
        }
    }
}