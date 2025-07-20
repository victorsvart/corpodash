package com.project.base.application.base;

public record ErrorResponse(int status, String error, String message) {
}
