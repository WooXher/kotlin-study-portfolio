package com.wooxher.portfolio.admin.context.project.form

import com.wooxher.portfolio.domain.entity.ProjectDetail
import jakarta.validation.constraints.NotBlank

class ProjectDetailForm(
    val id : Long,

    @NotBlank(message = "필수값 입니다.")
    val content : String,
    val url: String?,
    val isActive : Boolean
) {
    fun toEntity(): ProjectDetail {
        return ProjectDetail(
            content = this.content,
            url = this.url,
            isActive = this.isActive,
        )
    }

}