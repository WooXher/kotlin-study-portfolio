package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.ProjectSkill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProjectSkillRepository : JpaRepository<ProjectSkill, Long>{
    // select * from project_skill where project_id =:projectId and skill_id = :SkillId
    fun findByProjectIdAndSkillId(projectId: Long, SkillId:Long): Optional<ProjectSkill>

}