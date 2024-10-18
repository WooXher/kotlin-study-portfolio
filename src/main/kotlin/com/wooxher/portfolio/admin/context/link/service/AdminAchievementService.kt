package com.wooxher.portfolio.admin.context.link.service

import com.wooxher.portfolio.admin.data.TableDTO
import com.wooxher.portfolio.domain.entity.Achievement
import com.wooxher.portfolio.domain.entity.Link
import com.wooxher.portfolio.domain.repository.AchievementRepository
import com.wooxher.portfolio.domain.repository.LinkRepository
import org.springframework.stereotype.Service

@Service
class AdminLinkService(
    private val linkRepository : LinkRepository,
) {

    fun getLinkTable(): TableDTO {
        val classInfo = Link::class
        val entities = linkRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}