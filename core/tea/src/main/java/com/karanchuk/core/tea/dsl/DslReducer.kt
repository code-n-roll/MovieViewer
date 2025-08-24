package com.karanchuk.core.tea.dsl

import com.karanchuk.core.tea.component.Reducer
import com.karanchuk.core.tea.component.Update

abstract class DslReducer<Command : Any, Effect : Any, Event : Any, State : Any> :
    Reducer<Command, Effect, Event, State> {

    private lateinit var updater: Updater<Command, Effect, State>

    val state: State
        get() = updater.state

    final override fun reduce(currentState: State, event: Event): Update<Command, Effect, State> {
        updater = Updater(currentState)
        reduce(event)
        return updater.collect()
    }

    protected abstract fun reduce(event: Event): Any?

    @TeaDsl2
    fun state(block: State.() -> State) {
        updater.state(block)
    }

    @TeaDsl2
    fun commands(vararg commands: Command) = updater.commands(*commands)

    @TeaDsl2
    fun effects(vararg effects: Effect) = updater.effects(*effects)
}