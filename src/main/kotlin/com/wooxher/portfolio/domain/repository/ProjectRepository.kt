package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Experience
import com.wooxher.portfolio.domain.entity.Project
import com.wooxher.portfolio.domain.entity.ProjectSkill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ProjectRepository : JpaRepository<Project, Long>{

    @Query("select p from Project p left join fetch p.skills s left join fetch s.skill where p.isActive = :isActive")
    fun findAllByIsActive(isActive : Boolean) :List<Project>

    @Query("select p from Project p left join fetch p.details where p.id = :id")
    override fun findById(id: Long): Optional<Project>
}