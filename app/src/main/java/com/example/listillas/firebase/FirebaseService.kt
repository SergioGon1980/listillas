package com.example.listillas.firebase

import android.content.Context
import androidx.activity.result.contract.ActivityResultContracts
import com.example.listillas.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import kotlin.math.sin

class FirebaseService constructor(val context: Context){
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    init {
        signIn()
        auth = Firebase.auth
    }

    private fun signIn() {
        oneTapClient = Identity.getSignInClient(context)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(R.string.firebase_id.toString())
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .setAutoSelectEnabled(true)
            .build()

        /*oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(context) { result ->
                try {
                    ActivityResultContracts.StartIntentSenderForResult
                }
            }*/

    }
}