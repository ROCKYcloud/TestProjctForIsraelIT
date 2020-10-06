package com.example.testprojectforwork.view

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testprojectforwork.R

@Suppress("UNREACHABLE_CODE")
class SplashFragment : Fragment() {
    private val time:Long = 3000
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = view.context.getSharedPreferences("keystore", 0)
        openSignInFragment()
    }

    private fun openSignInFragment(){
        Handler().postDelayed({
            val alias = "a"
            val password = preferences.getString(alias, null)
            if (password != null) {
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_contentFragment)
            }
        }, time)
    }
}