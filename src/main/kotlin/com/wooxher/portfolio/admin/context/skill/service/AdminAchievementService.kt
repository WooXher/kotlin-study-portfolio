package com.wooxher.portfolio.admin.context.skill.service

import com.wooxher.portfolio.admin.data.TableDTO
import com.wooxher.portfolio.domain.entity.Achievement
import com.wooxher.portfolio.domain.entity.Skill
import com.wooxher.portfolio.domain.repository.AchievementRepository
import com.wooxher.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service

@Service
class AdminSkillService(
    private val skillRepository : SkillRepository,
) {

    fun getSkillTable(): TableDTO {
        val classInfo = Skill::class
        val entities = skillRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}