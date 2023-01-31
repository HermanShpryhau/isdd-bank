package org.shph.bank.controller.exception

import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class ValidationExceptionHandler {
    @ExceptionHandler
    fun handleValidationException(e: MethodArgumentNotValidException): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "clientForm"
        modelAndView.addObject("validationErrors", true)
        return modelAndView
    }
}