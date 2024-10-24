package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long> {

    // select * from link where is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Link>

}
