package com.karanchuk.movieviewer.repository.favorite_movies.domain

import androidx.annotation.StringRes
import com.karanchuk.movieviewer.R

enum class DomainSort {
    TITLE_ASC,
    TITLE_DESC,
    RELEASE_DATE_ASC,
    RELEASE_DATE_DESC,
    DATE_ADDED_ASC,
    DATE_ADDED_DESC,
}

@StringRes
fun DomainSort.toTitle(): Int = when(this) {
    DomainSort.TITLE_ASC -> R.string.sort_by_title_asc
    DomainSort.TITLE_DESC -> R.string.sort_by_title_desc
    DomainSort.RELEASE_DATE_ASC -> R.string.sort_by_release_date_asc
    DomainSort.RELEASE_DATE_DESC -> R.string.sort_by_release_date_desc
    DomainSort.DATE_ADDED_ASC -> R.string.sort_by_date_added_asc
    DomainSort.DATE_ADDED_DESC -> R.string.sort_by_date_added_desc
}