package com.wooxher.portfolio.presentation.intercepter

import com.wooxher.portfolio.domain.entity.HttpInterface
import com.wooxher.portfolio.domain.repository.HttpInterfaceRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception

@Component
class PresentationInterceptor(
    private val httpInterfaceRepository: HttpInterfaceRepository,
): HandlerInterceptor {
    //컨트롤러까지 가기전
//    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        return super.preHandle(request, response, handler)
//    }

    //컨트롤러 응답을 받은 후, 예외 받으면 동작 멈춤
//    override fun postHandle(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        handler: Any,
//        modelAndView: ModelAndView?
//    ) {
//        super.postHandle(request, response, handler, modelAndView)
//    }


    //컨트롤러가 요청을 처리한 후 응답을 할 때 그 시점에동작한다 , 컨트롤러가 응답으로 예외 줘도 동작함
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        val httpInterface = HttpInterface(request)
        httpInterfaceRepository.save(httpInterface)
    }
}