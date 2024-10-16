package com.wooxher.portfolio.admin.interceptor

data class MenuDTO(
    val name: String,
    val pages: List<PageDTO>,
) {
}