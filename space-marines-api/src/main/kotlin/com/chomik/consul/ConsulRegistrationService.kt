package com.chomik.consul

import com.chomik.util.PropertiesUtil
import jakarta.annotation.PostConstruct
import jakarta.ejb.Singleton
import jakarta.ejb.Startup
import jakarta.inject.Inject
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import org.apache.hc.core5.ssl.SSLContextBuilder
import javax.net.ssl.SSLContext


@Singleton
@Startup
class ConsulRegistrationService {

    @Inject
    private lateinit var propertiesUtil: PropertiesUtil

    private lateinit var sslContext: SSLContext

    private val consulUrl = "http://localhost:8500"

    @PostConstruct
    fun postConstruct() {
        println("ConsulRegistrationService-postConstruct")
        sslContext = SSLContextBuilder().loadTrustMaterial(
            this.javaClass.classLoader.getResource(propertiesUtil.getValueByPropertyNameOrEmpty("ssl.keystore.path")),
            propertiesUtil.getValueByPropertyNameOrEmpty("ssl.keystore.password").toCharArray()
        ).build()

        registerService()
    }

    private fun registerService() {
        val serviceName = "space-marines"
        val serviceId = "$serviceName-id"

        val serviceAddress = "127.0.0.1"
        val httpsServicePort = 27880
        val httpServicePort = 27809

        val registrationJson = """
            {
                "ID": "$serviceId",
                "Name": "$serviceName",
                "Address": "$serviceAddress",
                "Port": $httpsServicePort,
                "Check": {
                    "HTTP": "http://$serviceAddress:$httpServicePort/api/v1/test/ping",
                    "Interval": "10s"
                }
            }
        """.trimIndent()

        val client = ClientBuilder.newBuilder()
            .sslContext(sslContext)
            .hostnameVerifier { _, _ -> true }
            .build()

        try {
            val response = client
                .target("$consulUrl/v1/agent/service/register?replace-existing-checks")
                .request()
                .put(Entity.json(registrationJson))

            if (response.status == 200) {
                println("Service registered successfully.")
            } else {
                println("Failed to register service: HTTP ${response.status}, ${response.readEntity(String::class.java)}")
            }
        } catch (ex: Exception) {
            println("Exception while registering service: ${ex.message}")
        }
    }

}
