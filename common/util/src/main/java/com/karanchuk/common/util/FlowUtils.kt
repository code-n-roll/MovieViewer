package com.karanchuk.common.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

fun <T> Flow<T>.startWith(value: T): Flow<T> = onStart { emit(value) }

fun <T> Flow<T>.onCatchReturn(block: (Throwable) -> T) = catch { error -> emit(block(error)) }

fun <T> Flow<T>.onCatchLog(
    tag: String,
    message: String,
    rethrow: Boolean = true,
) = catch { error ->
    Timber.d(tag, message, error)
    if (rethrow) throw error
}