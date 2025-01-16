package com.starship.unloadmanager

import com.chomik.util.PropertiesUtil
import com.orbitz.consul.Consul
import com.orbitz.consul.model.health.ServiceHealth
import jakarta.annotation.PostConstruct
import jakarta.ejb.Singleton
import jakarta.ejb.Startup
import jakarta.ejb.Stateless
import jakarta.inject.Inject
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import org.apache.hc.core5.ssl.SSLContextBuilder
import org.jboss.ejb3.annotation.Pool
import javax.net.ssl.SSLContext

@Singleton
@Startup
@Pool("unload-manager-pool")
class SpaceMarineClientImpl : SpaceMarineClientInterface {

    @Inject
    private lateinit var propertiesUtil: PropertiesUtil

    private lateinit var sslContext: SSLContext
    private lateinit var consul: Consul
    private lateinit var spaceMarineUrl: String

    @PostConstruct
    fun postConstruct() {
        sslContext = SSLContextBuilder().loadTrustMaterial(
            this.javaClass.classLoader.getResource(propertiesUtil.getValueByPropertyNameOrEmpty("ssl.keystore.path")),
            propertiesUtil.getValueByPropertyNameOrEmpty("ssl.keystore.password").toCharArray()
        ).build()

        consul = Consul.builder()
            .withUrl(propertiesUtil.getValueByPropertyNameOrEmpty("consul.url"))
            .build()

        spaceMarineUrl = getServiceUrl("space-marines")
    }

    private fun getServiceUrl(serviceName: String): String {
        val healthClient = consul.healthClient()
        val healthyServices: List<ServiceHealth> = healthClient.getHealthyServiceInstances(serviceName).response

        if (healthyServices.isEmpty()) {
            throw IllegalStateException("No healthy instances found for service: $serviceName")
        }

        val service = healthyServices.first().service
        return "https://${service.address}:${service.port}/api/v1"
    }

    override fun removeSpaceMarineFromStarship(starshipId: Long, spaceMarineId: Long): SpaceMarineResponse {
        val client = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier { _, _ -> true }.build()

        val response = client
            .target("$spaceMarineUrl/starship/$starshipId/unload/$spaceMarineId")
            .request()
            .post(Entity.text(""))

        return SpaceMarineResponse(response.status)
    }

    override fun removeAllSpaceMarinesFromStarship(starshipId: Long): SpaceMarineResponse {
        val client = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier { _, _ -> true }.build()

        val response = client
            .target("$spaceMarineUrl/starship/$starshipId/unload-all")
            .request()
            .post(Entity.text(""))

        return SpaceMarineResponse(response.status)
    }

    companion object {
        private const val SPACE_MARINE_URL = "https://127.0.0.1:8443/space-marines-api-0.0.1-SNAPSHOT/api/v1"
    }
}
