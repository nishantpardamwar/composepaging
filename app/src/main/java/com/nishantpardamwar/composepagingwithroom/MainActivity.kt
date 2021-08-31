package com.nishantpardamwar.composepagingwithroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.nishantpardamwar.composepagingwithroom.ui.NoteListScreen
import com.nishantpardamwar.composepagingwithroom.ui.theme.ComposePagingWithRoomTheme
import com.nishantpardamwar.composepagingwithroom.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePagingWithRoomTheme { // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NoteListScreen(vm)
                }
            }
        }
    }
}