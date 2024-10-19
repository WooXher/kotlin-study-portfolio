package com.wooxher.portfolio.admin.context.project.service

import com.wooxher.portfolio.admin.data.TableDTO
import com.wooxher.portfolio.admin.exception.AdminBadRequestException
import com.wooxher.portfolio.domain.entity.Achievement
import com.wooxher.portfolio.domain.entity.ProjectDetail
import com.wooxher.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class AdminProjectService(
    private val projectRepository: ProjectRepository,
) {
    fun getProjectTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = projectRepository .findAll()

        return TableDTO.from(classInfo, entities, "details", "skills")
    }

    fun getProjectTable(id: Long?): TableDTO{
        val classInfo = ProjectDetail::class
        val entities = if(id != null) projectRepository.findById(id)
            .orElseThrow{ throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.")}
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}