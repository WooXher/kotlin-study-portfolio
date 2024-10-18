package com.wooxher.portfolio.admin.context.achivement.service

import com.wooxher.portfolio.admin.data.TableDTO
import com.wooxher.portfolio.domain.entity.Achievement
import com.wooxher.portfolio.domain.repository.AchievementRepository
import org.springframework.stereotype.Service

@Service
class AdminAchievementService(
    private val achievementRepository : AchievementRepository,
) {

    fun getAchievementTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = achievementRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}