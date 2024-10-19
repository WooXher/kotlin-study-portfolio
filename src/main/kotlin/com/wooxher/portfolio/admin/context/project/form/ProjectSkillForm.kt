package com.wooxher.portfolio.admin.context.project.form

import jakarta.validation.constraints.NotBlank

data class ProjectSkillForm(

    @NotBlank(message = "필수값 입니다.")
    val project: String,

    @NotBlank(message = "필수값 입니다.")
    val skill: String,


) {
}