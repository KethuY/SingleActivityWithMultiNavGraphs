package com.kethu.yerramma.samng.featureauth

import kotlinx.serialization.Serializable

/**
 * Created by Kethu on 11/06/2024.
 */
@Serializable
object AuthGraph // Root of the Auth Feature Navigation Graph

@Serializable
object CreateNewAccount

@Serializable
object SignIn

@Serializable
object SignUp

@Serializable
object ForgotPassword


