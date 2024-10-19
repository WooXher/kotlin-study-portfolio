package com.wooxher.portfolio.admin.context.project.service

import com.wooxher.portfolio.admin.data.TableDTO
import com.wooxher.portfolio.domain.repository.ExperienceRepository
import com.wooxher.portfolio.domain.repository.ProjectRepository
import com.wooxher.portfolio.domain.repository.ProjectSkillRepository
import com.wooxher.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectSkillService(
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository,
) {
    @Transactional
    fun getProjectSkillTable(): TableDTO{

        val projects = projectRepository.findAll()
        val columns = mutableListOf<String>(
            "id", "project", "projectName", "skillId", "skillName", "createdDateTime", "updatedDateTime"
        )

        val records = mutableListOf<MutableList<String>>()
        for (project in projects) {
            project.skills.forEach {
                val record = mutableListOf<String>()
                record.add(it.id.toString())
                record.add(it.project.id.toString())
                record.add(it.project.name)
                record.add(it.project.id.toString())
                record.add(it.project.name)
                record.add(it.createdDateTime.toString())
                record.add(it.updatedDateTime.toString())
                records.add(record)
            }
        }
        return TableDTO(name = "ProjectSkill", columns = columns, record = records)
    }
    fun getProjectList(): List<String>{
        val project = projectRepository.findAll()
        return project.map { "${it.id} (${it.name})" }.toList()
    }

    fun getSkillList(): List<String>{
        val skills = skillRepository.findAll()

        return skills.map { "${it.id} (${it.name})" }.toList()
    }
}