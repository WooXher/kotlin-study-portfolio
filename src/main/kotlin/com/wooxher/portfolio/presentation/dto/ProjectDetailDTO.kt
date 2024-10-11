package com.wooxher.portfolio.presentation.dto

import com.wooxher.portfolio.domain.entity.ProjectDetail

data class ProjectDetailDTO(
    val content: String,
    val url: String?,
) {
constructor(projentDetail: ProjectDetail): this(
    content = projentDetail.content,
    url = projentDetail.url
)

}