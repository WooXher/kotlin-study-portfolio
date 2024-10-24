package com.wooxher.portfolio.domain

import com.wooxher.portfolio.domain.constant.SkillType
import com.wooxher.portfolio.domain.entity.Account
import com.wooxher.portfolio.domain.entity.Achievement
import com.wooxher.portfolio.domain.entity.Experience
import com.wooxher.portfolio.domain.entity.ExperienceDetail
import com.wooxher.portfolio.domain.entity.Introduction
import com.wooxher.portfolio.domain.entity.Link
import com.wooxher.portfolio.domain.entity.Project
import com.wooxher.portfolio.domain.entity.ProjectDetail
import com.wooxher.portfolio.domain.entity.ProjectSkill
import com.wooxher.portfolio.domain.entity.Skill
import com.wooxher.portfolio.domain.repository.AccountRepository
import com.wooxher.portfolio.domain.repository.AchievementRepository
import com.wooxher.portfolio.domain.repository.ExperienceRepository
import com.wooxher.portfolio.domain.repository.IntroductionRepository
import com.wooxher.portfolio.domain.repository.LinkRepository
import com.wooxher.portfolio.domain.repository.ProjectRepository
import com.wooxher.portfolio.domain.repository.SkillRepository
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val experienceRepository: ExperienceRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository,
    private val accountRepository: AccountRepository
) {

    val log = LoggerFactory.getLogger(DataInitializer::class.java)

    @PostConstruct
    fun initializeData() {

        log.info("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")

        // achievement 초기화
        val achievements = mutableListOf<Achievement>(
//            Achievement(
//                title = "2022 Catkao 해커톤 최우수상",
//                description = "고양이 쇼핑몰 검색 서비스의 아키텍처, 데이터 모델링, API 개발 역할 수행",
//                host = "캣카오",
//                achievedDate = LocalDate.of(2022, 8, 1),
//                isActive = true
//            ),
            Achievement(
                title = "정보처리기사 - 실기 봐야하는데",
                description = "자료구조, 운영체제, 알고리즘, 데이터베이스 등",
                host = "Q-Net",
                achievedDate = LocalDate.of(2024, 3, 14),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        // introduction 초기화
        val introductions = mutableListOf<Introduction>(
            Introduction(content = "주도적으로 문제를 찾고, 해결하는것에 몰입합니다.", isActive = true),
            Introduction(content = "기술을 위한 기술이 아닌, 비즈니스 문제를 풀기 위한 기술을 추구합니다.", isActive = true),
            Introduction(content = "기존 소스를 리팩토링하여 더 좋은 구조로 개선하는 작업을 좋아합니다.", isActive = true)
        )
        introductionRepository.saveAll(introductions)

        // link 초기화
        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/WooXher", isActive = true),
//            Link(name = "Linkedin", content = "https://www.linkedin.com/in/bokeunjeong", isActive = true),
        )
        linkRepository.saveAll(links)

        // experience / experience_detail 초기화
        val experience1 = Experience(
            title = "캣홀릭대학교(CatHolic Univ.)",
            description = "컴퓨터공학 전공",
            startYear = 2019,
            startMonth = 3,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 3.64/4.5", isActive = true),
//                ExperienceDetail(content = "소프트웨어 연구 학회 활동", isActive = true)
            )
        )

        experienceRepository.saveAll(mutableListOf(experience1))

        // skill 초기화
        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
//        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
//        val python = Skill(name = "Python", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
//        val django = Skill(name = "Django", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
//        val redis = Skill(name = "Redis", type = SkillType.DATABASE.name, isActive = true)
//        val kafka = Skill(name = "Kafka", type = SkillType.TOOL.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, /*kotlin, python,*/ spring, /*django,*/ mysql,/* redis, kafka*/))

        // project / project_detail / project_skill 초기화

        val project1 = Project(
            name = "졸업작품(진행) PHCCS(Pet Health Care Community Service)",
            description = "반려동물의 피부질환 검사 및 수의사 연결",
            startYear = 2024,
            startMonth = 9,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(content = "게시판 서비스", url = null, isActive = true),
                ProjectDetail(content = "웹 소켓 통신 서비스", url = null, isActive = true)
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mysql),
//                ProjectSkill(project = project1, skill = redis)
            )
        )
//        val project2 = Project(
//            name = "반려동물 홈 카메라 움직임 감지 분석 모듈",
//            description = "카메라에서 서버로 전달되는 신호를 분석하여 움직임이 감지될 경우 클라이언트에게 알림 발송 작업.",
//            startYear = 2022,
//            startMonth = 12,
//            endYear = null,
//            endMonth = null,
//            isActive = true
//        )
//        project2.addDetails(
//            mutableListOf(
//                ProjectDetail(content = "PIL(Pillow) 활용하여 이미지 분석 기능 개발", url = null, isActive = true),
//                ProjectDetail(content = "알림 발송을 비동기 처리하여 이미지 분석 - 알림 발송 기능간 의존도 감소", url = null, isActive = true),
//                ProjectDetail(content = "Github Repository", url = "https://github.com/infomuscle", isActive = true)
//            )
//        )
//        project2.skills.addAll(
//            mutableListOf(
//                ProjectSkill(project = project2, skill = python),
//                ProjectSkill(project = project2, skill = django),
//                ProjectSkill(project = project2, skill = kafka)
//            )
//        )
        projectRepository.saveAll(mutableListOf(project1))

        val account = Account(
            loginId = "admin1",
            pw = "\$2a\$10\$cnuc8tieyD6AmeNdUhP.Vey8f3KuTdJ/AMLyIEDnWOf3ybIuvFN2C"
        )
        accountRepository.save(account)
    }
}
