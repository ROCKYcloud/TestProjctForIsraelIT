package com.example.testprojectforwork.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.testprojectforwork.R

class ScreenNavigator {
    companion object {
        private const val BEER_ID = "BEER_ID"
    }

    fun contentOpenDetail(
        navController: NavController,
        id: Long
    ) {
        navController.navigate(R.id.action_contentFragment_to_detailFragment, Bundle().apply {
            putLong(BEER_ID, id)
        })
    }

    fun getBeerParentsId(fragment: Fragment): Long? {
        return fragment.arguments?.getLong(BEER_ID)
    }
}