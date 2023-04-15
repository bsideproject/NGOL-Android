package com.nugu.exception

import java.io.IOException

/** The case where the response of a REST API call is a failure */
class ResponseException(
    val errorMessage: String?,
    val errorCode: String?
) : IOException(errorMessage)