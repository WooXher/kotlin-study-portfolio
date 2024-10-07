package com.wooxher.portfolio.domain.entity

import com.wooxher.portfolio.domain.constant.SkillType
import jakarta.persistence.*

@Entity
// 이 어노테이션은 jpa에서 태이블과 매핑되는 클래스라는걸 인지함
class Skill(
    name: String,
    type : String,
    isActive: Boolean,
) : BaseEntity() {

    @Id // 해당 필드가 pk라는걸 인지함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id") // db에서 어떤 컬럼이랑 매칭될지 정해줌 db에선s skill_id 이고 IDE 에서는 id
    var id: Long? = null

    var name: String = name

    @Column(name = "skill_type")
    @Enumerated(value = EnumType.STRING) // 자료형이 enum일때 사용 string이면 enum의 이름을(LANGUAGE, DATABASE이렇게)  넣어줌 ordinary면 숫자로 넣음(1, 2, 3..)
    // string으로 하는게 일반적으로 좋긴함
    var type : SkillType = SkillType.valueOf(type)

    var isActive : Boolean = isActive


}