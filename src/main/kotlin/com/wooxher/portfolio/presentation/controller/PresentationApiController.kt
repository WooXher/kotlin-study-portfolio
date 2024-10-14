package com.wooxher.portfolio.presentation.controller

import com.wooxher.portfolio.presentation.dto.IntroductionDTO
import com.wooxher.portfolio.presentation.dto.LinkDTO
import com.wooxher.portfolio.presentation.dto.ProjectDTO
import com.wooxher.portfolio.presentation.dto.ResumeDTO
import com.wooxher.portfolio.presentation.service.PresentationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController(
    private val presentationService : PresentationService
){
    @GetMapping("/test")
    fun test(): String{
        return "OK";
    }
    @GetMapping("/v1/introductions")
    fun getIntroductions(): List<IntroductionDTO> =  presentationService.getIntroductions()

    @GetMapping("/v1/links")
    fun getLinks(): List<LinkDTO> =  presentationService.getLinks()

    @GetMapping("/v1/resume")
    fun getResume(): ResumeDTO =  presentationService.getResume()

    @GetMapping("/v1/projects")
    fun getProjects(): List<ProjectDTO> =  presentationService.getProjects()

}