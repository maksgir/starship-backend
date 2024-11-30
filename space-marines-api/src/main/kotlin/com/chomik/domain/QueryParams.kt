package com.chomik.domain

import com.chomik.domain.enums.SortColumn
import com.chomik.domain.enums.SortOrder

data class QueryParams(
    val sortBy: List<SortColumn>?,
    val sortOrder: SortOrder?,
    val filters: List<Filter>?,
    val page: Int,
    val size: Int,
)
