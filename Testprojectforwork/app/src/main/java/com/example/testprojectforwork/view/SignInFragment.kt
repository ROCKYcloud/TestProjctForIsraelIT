package com.example.testprojectforwork.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.testprojectforwork.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_sign_in.*
import com.example.testprojectforwork.PreferenceHelper.username
import com.example.testprojectforwork.PreferenceHelper.email
import com.example.testprojectforwork.PreferenceHelper.password
import com.example.testprojectforwork.R
import com.example.testprojectforwork.kaystore.Encryption
import org.json.JSONObject

class SignInFragment : Fragment() {

    private var isSingIn = true
    private val crypto = Encryption()
    private lateinit var preferences: SharedPreferences
    val et_alias = "a"
    private val str = "User_data"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentLayout = inflater.inflate(R.layout.fragment_sign_in, container, false)

        fragmentLayout.findViewById<Button>(R.id.btnInput)
            .setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_signInFragment_to_contentFragment,
                    null
                )
            )

        return fragmentLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = view.context.getSharedPreferences("keystore", 0)
        btnInput.setOnClickListener {
            enter(view.context, findNavController())
        }
        btnSingUp.setOnClickListener {
            isSingIn = !isSingIn
            if (isSingIn) {
                etConfirmPassword.visibility = View.GONE
                etUserName.visibility = View.GONE
            } else {
                etConfirmPassword.visibility = View.VISIBLE
                etUserName.visibility = View.VISIBLE
            }
        }
    }


    private fun toEncrypt(context: Context) {
        if (hasTextToEncrypt()) {
            val pref =
                PreferenceHelper.customPreference(
                    context,
                    str
                )
            val encryptMap = crypto.encryptText(et_alias, etPassword.text.toString())
            preferences.edit().putString(et_alias, encryptMap.toString()).apply()
            pref.password = encryptMap.get("encrypted").toString()
        }

    }

    private fun enter(context: Context, navController: NavController) {
        if (isSingIn) {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
                val alias = "a"
                val encData = preferences.getString(alias, null)
                if (encData != null) {
                    val map = JSONObject(encData.toString())
                    val pass = crypto.decryptText(
                        alias,
                        map.getString("encrypted"),
                        map.getString("iv")
                    )
                    if (etPassword.text.toString() == pass) {
                        navController.navigate(R.id.action_signInFragment_to_contentFragment)

                    }
                }
            } else {
                Toast.makeText(context, "Some field is Empty!!", Toast.LENGTH_LONG).show()
            }
        } else {
            if (etUserName.text.isNotEmpty() && etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty() && etConfirmPassword.text.isNotEmpty()) {
                if (etPassword.text.toString() == etConfirmPassword.text.toString()) {
                    var pref =
                        PreferenceHelper.customPreference(
                            context,
                            str
                        )
                    pref.username = etUserName.text.toString()
                    pref.email = etEmail.text.toString()
                    toEncrypt(context)
                    Toast.makeText(context, "secsesful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        context,
                        "field password not equals confirm password!!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(context, "Some field is Empty!!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun hasTextToEncrypt() = if (etPassword.text.isNotEmpty()) true else false
}