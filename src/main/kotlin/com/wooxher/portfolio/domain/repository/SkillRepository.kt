package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.constant.SkillType
import com.wooxher.portfolio.domain.entity.Link
import com.wooxher.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SkillRepository : JpaRepository<Skill, Long>{
    // select * from Skill where is_active = :isActive
    fun findAllByIsActive(isActive: Boolean) : List<Skill>

    // select * from skill where lower(name) = lower(:name) and skill_type = :type
    fun findByNameIgnoreCaseAndType(name : String, type : SkillType): Optional<Skill>

}