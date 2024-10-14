package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Experience
import com.wooxher.portfolio.domain.entity.ExperienceDetail
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.math.exp
import kotlin.test.Test

@DataJpaTest // 테스트콛드 실행마다 jpa 사용이 가능한 만큼의 빈 생성
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 인스턴스의 라이프 사이클이 클래스 단위가 된다 ??? 무슨말???
class ExperienceRepositoryTest(
    @Autowired val experienceRepository: ExperienceRepository
) {
    val DATA_SIZE = 10

    // 더미 엔티티 생성용
    private fun createExperience(n : Int) : Experience{
        val experience = Experience(
            title = "$n",
            description = "테스트 설명 ${n}",
            startYear = 2023,
            startMonth = 9,
            endYear = 2023,
            endMonth = 9,
            isActive = true,
        )

        val details = mutableListOf<ExperienceDetail>()
        for(i in 1..n){
            val experienceDetail = ExperienceDetail(content = "테스트 $i", isActive = true)
            details.add(experienceDetail)
        }
        experience.addDetails(details)

        return experience
    }

    @BeforeAll
    fun beforeAll(){
        println("----데이터 초기화 이전 조회 시작----")
        val beforeInitialize = experienceRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        println("----데이터 초기화 이전 조회 종료----")

        println("----테스트 데이터 초기화 시작----")
        val experiences = mutableListOf<Experience>()
        for(i in 1..DATA_SIZE){
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        println("----테스트 데이터 초기화 종료----")
    }

    @Test
    fun testFindAll(){
        println("---- findAll 테스트 시작 ----")
        val experiences = experienceRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)
        println("experiences.size: ${experiences.size}")

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experiences.details.size: ${experience.details.size}")
        }

        println("---- findAll 테스트 종료 ----")
    }
    @Test
    @DisplayName("활동?")
    fun testFindAllByIsActive(){
        println("---- findAllByIsActive 테스트 시작 ----")
        val experiences = experienceRepository.findAllByIsActive(true)
        assertThat(experiences).hasSize(DATA_SIZE)
        println("experiences.size: ${experiences.size}")

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experiences.details.size: ${experience.details.size}")
        }

        println("---- findAllByIsActive 테스트 종료 ----")
    }

    @Test
    fun test(){
        val findById = experienceRepository.findById(1)
        println(findById.isPresent)

    }

}