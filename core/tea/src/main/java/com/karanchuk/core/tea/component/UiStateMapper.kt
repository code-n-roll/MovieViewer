package com.karanchuk.core.tea.component

/**
 * Maps [State] to [UiState]. Contains all the logic for mapping data to the UI
 */
fun interface UiStateMapper<State, UiState> {

    fun map(state: State): UiState
}