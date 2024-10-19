package com.wooxher.portfolio.admin.context.project.controller

import com.wooxher.portfolio.admin.context.project.service.AdminProjectService
import com.wooxher.portfolio.admin.data.FormElementDTO
import com.wooxher.portfolio.admin.data.SelectFormElementDTO
import com.wooxher.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model

@Controller
@RequestMapping("/admin/project")
class AdminProjectViewController(
    private val adminProjectService: AdminProjectService,
) {

    @GetMapping
    fun project(model: Model): String{
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("name", 4),
            TextFormElementDTO("description",8),
            SelectFormElementDTO("startYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("startMonth", 2, (1..12).toList()),
            SelectFormElementDTO("endYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("endMonth", 2, (1..12).toList()),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("formElements", formElements)

        val detailFormElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 10),
            TextFormElementDTO("url", 6),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("detailFormElements", detailFormElements)

        val table = adminProjectService.getProjectTable()
        model.addAttribute("table", table)

        val detailsTable = adminProjectService.getProjectTable()
        model.addAttribute("detailsTable", detailsTable)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Project"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", true),
        )
        model.addAllAttributes(pageAttributes)
        return "admin/page-table"
    }
}