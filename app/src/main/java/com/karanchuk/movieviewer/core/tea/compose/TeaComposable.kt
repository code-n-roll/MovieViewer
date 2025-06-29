package com.karanchuk.movieviewer.core.tea.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import com.karanchuk.movieviewer.core.tea.Store
import kotlinx.coroutines.Dispatchers

@Composable
fun <UiState : Any, UiEvent : Any, Effect : Any> TeaComposable(
    store: Store<Effect, UiEvent, UiState>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    content: @Composable TeaScope<UiEvent, Effect>.(state: UiState) -> Unit,
) {
	val scope = rememberTeaScope(lifecycleState = lifecycleState, store = store)
	val state by store.state.collectAsStateOnLifecycle(Dispatchers.Main.immediate, lifecycleState)

	content(scope, state)
}