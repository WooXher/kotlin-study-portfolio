package com.wooxher.portfolio.admin.advice

import com.wooxher.portfolio.admin.exception.AdminException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// 다른 컨트롤러에서 예외가 발생하는것을 처리하는 핸들러들ㅇ르 모으는 클래스에 적용하는 어노테이션
@RestControllerAdvice
class AdminApiControllerAdvice {

    val log = LoggerFactory.getLogger(AdminApiControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(e: AdminException): ResponseEntity<String>{
        log.info(e.message, e)

        return ResponseEntity.status(e.httpStatus).body(e.message)
    }

    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String>{
        log.info(e.message, e)

        val fieldError = e.bindingResult.fieldErrors[0]
        val message = "[${fieldError.field} ${fieldError.defaultMessage}]"

        return ResponseEntity.badRequest().body(message)
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String>{
        log.info(e.message, e)

        return ResponseEntity.internalServerError().body("시스템 오류가 발생했습니다.")
    }
}