package com.wooxher.portfolio.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
// 이 어노테이션은 해당 클래스를 상속받는 클래스의
// 엔티티 컬럼들과 해당 클래스의 컬럼과 매핑이 되게 해줌
abstract class BaseEntity{

    @CreatedDate
    @Column(nullable = false, updatable = false)
    // 널 허용 안하기 떄문에 값 필수
    // 생성일자는 수정되면 안되기 떄문에 수정 못하게 막기
    var createdDateTime: LocalDateTime = LocalDateTime.now();

    @LastModifiedDate // 언제 마지막으로 수정되었는지 ㅎ
    @Column(nullable = false) // 수정확인시마다 같이 변경 되어야 함 따라서 updatable = true
    var updatedDateTime: LocalDateTime = LocalDateTime.now();

}