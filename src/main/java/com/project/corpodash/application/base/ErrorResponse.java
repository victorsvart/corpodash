package com.project.corpodash.application.base;

public record ErrorResponse(int status, String error, String message) {}
