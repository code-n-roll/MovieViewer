package com.karanchuk.movieviewer.core.tea.component

class Update<Command : Any, Effect : Any, State : Any>(
    val state: State? = null,
    val commands: List<Command> = emptyList(),
    val effects: List<Effect> = emptyList(),
)