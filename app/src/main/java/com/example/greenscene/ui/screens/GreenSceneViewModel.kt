package com.example.greenscene.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenscene.common.snackbar.SnackbarManager
import com.example.greenscene.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.example.greenscene.model.service.RestaurantService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class GreenSceneViewModel() : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                println("throw $throwable") // todo: replace with LogService
            },
            block = block
        )
}
