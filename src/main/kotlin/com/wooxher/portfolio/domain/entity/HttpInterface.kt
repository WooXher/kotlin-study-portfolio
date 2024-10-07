package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest

@Entity
// 이 어노테이션은 jpa에서 태이블과 매핑되는 클래스라는걸 인지함
class HttpInterface(httpServletRequest: HttpServletRequest) : BaseEntity() {

    @Id // 해당 필드가 pk라는걸 인지함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "http_interface_id") // db에서 어떤 컬럼이랑 매칭될지 정해줌
    var id: Long? = null

    var cookies: String? = httpServletRequest.cookies
        ?.map{ "${it.name}:${it.value}" }
        ?.toString() // 널이면 실행 안하고 널 아미ㅕㄴ 실행
    var referer: String? = httpServletRequest.getHeader("referer") // google.com 이 referer임
    var localAddr: String? = httpServletRequest.localAddr
    var remoteAddr: String? = httpServletRequest.remoteAddr
    var remoteHost: String? = httpServletRequest.remoteHost
    var requestURI: String? = httpServletRequest.requestURI
    var userAgent: String? = httpServletRequest.getHeader("user- agent")
}