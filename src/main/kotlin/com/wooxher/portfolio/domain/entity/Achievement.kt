package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
// 이 어노테이션은 jpa에서 태이블과 매핑되는 클래스라는걸 인지함
class Achievement(
        title:String,
        description:String,
        achievedDate: LocalDate?,
        host:String,
        isActive:Boolean
) : BaseEntity() {

    @Id // 해당 필드가 pk라는걸 인지함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id") // db에서 어떤 컬럼이랑 매칭될지 정해줌
    var id: Long? = null

    var title: String = title;

    var description: String = description;

    var achievedDate: LocalDate? = achievedDate;

    var host: String = host;

    var isActive: Boolean = isActive

}