package com.keygenqt.demo_contacts.base

sealed class HTTPResult(val code: Int, error: String) : RuntimeException(error) {
    object Result200 : HTTPResult(200, "OK")
    class Result400(error: String = "Bad Request") : HTTPResult(400, error)
    class Result401(error: String = "Unauthorized") : HTTPResult(401, error)
    class Result403(error: String = "Forbidden") : HTTPResult(403, error)
    class Result404(error: String = "Not Found") : HTTPResult(404, error)
    class Result500(error: String = "Internal Server Error") : HTTPResult(500, error)
    class ResultUnknown(code: Int = -1, error: String = "HTTP Version Not Supported") : HTTPResult(code, error)
}