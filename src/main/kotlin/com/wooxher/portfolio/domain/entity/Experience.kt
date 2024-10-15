package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*

@Entity
// 이 어노테이션은 jpa에서 태이블과 매핑되는 클래스라는걸 인지함
class Experience( // ExperienceDetail 과 1 : N 관계
    title: String,
    description: String,
    startYear: Int,
    startMonth: Int,
    endYear: Int?,
    endMonth: Int?,
    isActive: Boolean,
) : BaseEntity() {

    @Id // 해당 필드가 pk라는걸 인지함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id") // db에서 어떤 컬럼이랑 매칭될지 정해줌
    var id: Long? = null

    var title:String = title

    var description: String = description

    var startYear:Int = startYear

    var startMonth: Int = startMonth

    var endYear:Int? = endYear

    var endMonth:Int? = endMonth

    var isActive: Boolean = isActive

    // ExperienceDetail과 1:N 관계
    @OneToMany(
        targetEntity = ExperienceDetail::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]) // 1 Experience : N ExperienceDetail
    @JoinColumn(name = "experience_id") // 조인할 컬럼이름
    var details: MutableList<ExperienceDetail> = mutableListOf()

    fun getEndYearMonth():String {
        if(endYear == null || endMonth == null){
            return "Present"
        }

        return "${endYear}.${endMonth}" // 2023.12

    }
    fun update(title: String, description: String, startYear: Int, startMonth: Int, endYear: Int?, endMonth: Int?, isActive: Boolean, ):Unit{
        this.title = title
        this.description = description
        this.startYear = startYear
        this.startMonth = startMonth
        this.endYear = endYear
        this.endMonth = endMonth
        this.isActive = isActive
    }
    fun addDetails(detail: MutableList<ExperienceDetail>?){
        if(detail != null){
            this.details.addAll(detail)
        }
    }












}