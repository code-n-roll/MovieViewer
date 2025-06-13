package com.karanchuk.movieviewer.core.tea.util

import kotlinx.coroutines.flow.merge
import com.karanchuk.movieviewer.core.tea.component.Actor

internal fun <Command : Any, Event : Any> combineActors(
    actors: Set<Actor<Command, Event>>,
): Actor<Command, Event> {
    return Actor { commands ->
        actors
            .map { actor -> actor.act(commands) }
            .merge()
    }
}