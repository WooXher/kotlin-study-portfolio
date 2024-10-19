package com.wooxher.portfolio.admin.context.experience.form

import com.wooxher.portfolio.domain.entity.Experience
import com.wooxher.portfolio.domain.entity.ExperienceDetail
import jakarta.validation.constraints.NotBlank


data class ExperienceDetailForm(
    val id : Long,

    @NotBlank(message = "필수값 입니다.")
    val content : String,

    val isActive : Boolean
) {
    fun toEntity(): ExperienceDetail{
        return ExperienceDetail(
            content = this.content,
            isActive = this.isActive,
        )
    }



}