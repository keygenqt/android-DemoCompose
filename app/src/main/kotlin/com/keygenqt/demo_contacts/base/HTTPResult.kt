/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
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

class Result422(error: String = "Data validation failed") : RuntimeException(error)