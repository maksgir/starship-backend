package com.chomik.configuration

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.ext.Provider

@Provider
class CorsRequestFilter : ContainerRequestFilter {
    override fun filter(requestContext: ContainerRequestContext) {
        if (!requestContext.headers.containsKey("Access-Control-Allow-Origin")) {
            requestContext.headers.add("Access-Control-Allow-Origin", "*")
        }
        if (!requestContext.headers.containsKey("Access-Control-Allow-Headers")) {
            requestContext.headers.add("Access-Control-Allow-Headers", "*")
        }
        if (!requestContext.headers.containsKey("Access-Control-Allow-Methods")) {
            requestContext.headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS")
        }
    }
}
