package com.karanchuk.core.tea.util

import com.karanchuk.core.tea.Store

fun <UiEvent : Any> Store<*, UiEvent, *>.acceptable(event: UiEvent): () -> Unit {
    return { accept(event) }
}

fun <UiEvent : Any, T1> Store<*, UiEvent, *>.acceptable(block: (T1) -> UiEvent): (T1) -> Unit {
    return { t1: T1 -> accept(block(t1)) }
}

fun <UiEvent : Any, T1, T2> Store<*, UiEvent, *>.acceptable(block: (T1, T2) -> UiEvent): (T1, T2) -> Unit {
    return { t1: T1, t2: T2 -> accept(block(t1, t2)) }
}

fun <UiEvent : Any, T1, T2, T3> Store<*, UiEvent, *>.acceptable(block: (T1, T2, T3) -> UiEvent): (T1, T2, T3) -> Unit {
    return { t1: T1, t2: T2, t3: T3 -> accept(block(t1, t2, t3)) }
}

fun <UiEvent : Any, T1, T2, T3, T4> Store<*, UiEvent, *>.acceptable(block: (T1, T2, T3, T4) -> UiEvent): (T1, T2, T3, T4) -> Unit {
    return { t1: T1, t2: T2, t3: T3, t4: T4 -> accept(block(t1, t2, t3, t4)) }
}

fun <UiEvent : Any, T1, T2, T3, T4, T5> Store<*, UiEvent, *>.acceptable(block: (T1, T2, T3, T4, T5) -> UiEvent): (T1, T2, T3, T4, T5) -> Unit {
    return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 -> accept(block(t1, t2, t3, t4, t5)) }
}

fun <UiEvent : Any, T1, T2, T3, T4, T5, T6> Store<*, UiEvent, *>.acceptable(block: (T1, T2, T3, T4, T5, T6) -> UiEvent): (T1, T2, T3, T4, T5, T6) -> Unit {
    return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 -> accept(block(t1, t2, t3, t4, t5, t6)) }
}

fun <UiEvent : Any, T1, T2, T3, T4, T5, T6, T7> Store<*, UiEvent, *>.acceptable(block: (T1, T2, T3, T4, T5, T6, T7) -> UiEvent): (T1, T2, T3, T4, T5, T6, T7) -> Unit {
    return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 -> accept(block(t1, t2, t3, t4, t5, t6, t7)) }
}

fun <UiEvent : Any, T1, T2, T3, T4, T5, T6, T7, T8> Store<*, UiEvent, *>.acceptable(block: (T1, T2, T3, T4, T5, T6, T7, T8) -> UiEvent): (T1, T2, T3, T4, T5, T6, T7, T8) -> Unit {
    return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 -> accept(block(t1, t2, t3, t4, t5, t6, t7, t8)) }
}

fun <UiEvent : Any, T1, T2, T3, T4, T5, T6, T7, T8, T9> Store<*, UiEvent, *>.acceptable(block: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> UiEvent): (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> Unit {
    return { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9 -> accept(block(t1, t2, t3, t4, t5, t6, t7, t8, t9)) }
}