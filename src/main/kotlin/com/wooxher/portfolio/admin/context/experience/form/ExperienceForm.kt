package com.wooxher.portfolio.admin.context.experience.form

import com.wooxher.portfolio.domain.entity.Experience
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class ExperienceForm (
    @NotBlank(message = "필수값 입니다.")
    val title: String,

    @NotBlank(message = "필수값 입니다.")
    val description: String,

    @Positive(message = "0보다 커야 합니다.")
    val startYear: Int,

    @Min(value = 1, message = "최소값은 1")
    @Min(value = 12, message = "최대값은 12")
    val startMonth: Int,

    val endYear: Int?,
    val endMonth: Int?,
    val isActive : Boolean,
    val details: List<ExperienceDetailForm>?,

){
    fun toEntity(): Experience {
        return Experience(
            title = this.title,
            description = this.description,
            startYear =  this.startYear,
            startMonth = this.startMonth,
            endYear = this.endYear,
            endMonth = this.endMonth,
            isActive = this.isActive
        )
    }
}














