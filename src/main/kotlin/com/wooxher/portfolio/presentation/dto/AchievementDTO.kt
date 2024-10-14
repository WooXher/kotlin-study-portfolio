package com.wooxher.portfolio.presentation.dto

import org.apache.catalina.Host

data class AchievementDTO(
    val title : String,
    val description : String,
    val host : String,
    val achievedDate : String?,
) {


}