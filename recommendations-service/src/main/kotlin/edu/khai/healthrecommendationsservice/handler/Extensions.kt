package edu.khai.healthrecommendationsservice.handler

import java.util.StringJoiner
import javax.validation.Validator


fun <T> T.validate(validator: Validator): Boolean {
    val constraintViolations = validator.validate(this)
    if (constraintViolations.isNotEmpty()) {
        val stringJoiner = StringJoiner(" ")
        constraintViolations.forEach { loginModelConstraintViolation ->
            stringJoiner
                .add(loginModelConstraintViolation.getPropertyPath().toString())
                .add(":")
                .add(loginModelConstraintViolation.getMessage())
        }
        throw RuntimeException(stringJoiner.toString())
    }
    return true
}