package com.chomik.configuration

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerResponseContext
import jakarta.ws.rs.container.ContainerResponseFilter
import jakarta.ws.rs.ext.Provider

@Provider
class CorsResponseFilter : ContainerResponseFilter {
    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        responseContext.headers.remove("Last-modified")
        if (!responseContext.headers.containsKey("Access-Control-Allow-Origin")) {
            responseContext.headers.add("Access-Control-Allow-Origin", "*")
        }
        if (!responseContext.headers.containsKey("Access-Control-Allow-Headers")) {
            responseContext.headers.add("Access-Control-Allow-Headers", "*")
        }
        if (!responseContext.headers.containsKey("Access-Control-Allow-Methods")) {
            responseContext.headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS")
        }
    }
}
