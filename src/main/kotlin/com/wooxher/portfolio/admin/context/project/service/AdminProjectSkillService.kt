package com.wooxher.portfolio.admin.context.project.service

import com.wooxher.portfolio.admin.context.project.form.ProjectSkillForm
import com.wooxher.portfolio.admin.data.TableDTO
import com.wooxher.portfolio.admin.exception.AdminBadRequestException
import com.wooxher.portfolio.admin.exception.AdminInternalServerErrorException
import com.wooxher.portfolio.domain.entity.ProjectSkill
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
    private val projectSkillRepository: ProjectSkillRepository,
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

    @Transactional
    fun save(form: ProjectSkillForm){
        //  "id (name)"
        val projectId = parseId(form.project)
        val skillId = parseId(form.skill)
        projectSkillRepository.findByProjectIdAndSkillId(projectId, skillId)
            .ifPresent{throw AdminBadRequestException("이미 매핑된 데이터 입니다.")}
        val project = projectRepository.findById(projectId)
            .orElseThrow{ throw AdminBadRequestException("ID ${projectId}에 해당하는 데이터를 찾을 수 없습니다.")}
        val skill = skillRepository.findById(skillId)
            .orElseThrow{throw AdminBadRequestException("ID ${skillId}에 해당하는 데이터를 찾을 수 없습니다.")}
        val projectSkill = ProjectSkill(
            project = project,
            skill = skill,
        )

        project.skills.add(projectSkill)
    }

    @Transactional
    fun delete(id: Long){
        projectSkillRepository.deleteById(id)
    }

    private fun parseId(line: String): Long{
        try{
            val endIndex = line.indexOf(" ") -1
            val id = line.slice(0..endIndex).toLong()

            return id
        }catch (e: Exception){
            throw AdminInternalServerErrorException("ID 추출중 오류가 발생했습니다.")
        }
    }
}