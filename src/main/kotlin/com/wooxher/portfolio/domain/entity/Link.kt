package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content

@Entity
// 이 어노테이션은 jpa에서 태이블과 매핑되는 클래스라는걸 인지함
class Link(
        name:String,
        content: String,
        isActive: Boolean,
) : BaseEntity() {

    @Id // 해당 필드가 pk라는걸 인지함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id") // db에서 어떤 컬럼이랑 매칭될지 정해줌
    var id: Long? = null

    var name: String = name;

    var content: String = content;

    var isActive: Boolean = isActive

}