package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*

@Entity
// 이 어노테이션은 jpa에서 태이블과 매핑되는 클래스라는걸 인지함
class ProjectDetail(
    content: String,
    url: String?,
    isActive: Boolean,) : BaseEntity() {

    @Id // 해당 필드가 pk라는걸 인지함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_detail_id") // db에서 어떤 컬럼이랑 매칭될지 정해줌
    var id: Long? = null
    var content: String = content;
    var url: String? = url
    var isActive: Boolean = isActive

    fun update(content: String, url:String?, isActive: Boolean){
        this.content = content
        this.url = url
        this.isActive = isActive
    }
}