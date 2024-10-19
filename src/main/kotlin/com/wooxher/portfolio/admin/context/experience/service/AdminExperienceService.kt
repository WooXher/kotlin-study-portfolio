package com.wooxher.portfolio.admin.context.experience.service

import com.wooxher.portfolio.admin.data.TableDTO
import com.wooxher.portfolio.admin.exception.AdminBadRequestException
import com.wooxher.portfolio.domain.entity.Achievement
import com.wooxher.portfolio.domain.entity.ExperienceDetail
import com.wooxher.portfolio.domain.repository.ExperienceRepository
import org.springframework.stereotype.Service

@Service
class AdminExperienceService(
    private val experienceRepository: ExperienceRepository,
) {
    fun getExperienceTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = experienceRepository .findAll()

        return TableDTO.from(classInfo, entities, "details")
    }

    fun getExperienceTable(id: Long?): TableDTO{
        val classInfo = ExperienceDetail::class
        val entities = if(id != null) experienceRepository.findById(id)
            .orElseThrow{ throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.")}
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}