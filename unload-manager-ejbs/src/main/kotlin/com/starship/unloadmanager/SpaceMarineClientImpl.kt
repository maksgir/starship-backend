package com.starship.unloadmanager

import jakarta.ejb.Stateless
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import org.jboss.ejb3.annotation.Pool

@Stateless
@Pool("unload-manager-pool")
class SpaceMarineClientImpl : SpaceMarineClientInterface {
    override fun removeSpaceMarineFromStarship(starshipId: Long, spaceMarineId: Long): SpaceMarineResponse {
        val client = ClientBuilder.newBuilder().hostnameVerifier { _, _ -> true }.build()

        val response = client
            .target("$SPACE_MARINE_URL/starship/$starshipId/unload/$spaceMarineId")
            .request()
            .post(Entity.text(""))

        return SpaceMarineResponse(response.status)
    }

    override fun removeAllSpaceMarinesFromStarship(starshipId: Long): SpaceMarineResponse {
        val client = ClientBuilder.newBuilder().hostnameVerifier { _, _ -> true }.build()

        val response = client
            .target("$SPACE_MARINE_URL/starship/$starshipId/unload-all")
            .request()
            .post(Entity.text(""))

        return SpaceMarineResponse(response.status)
    }

    companion object {
        private const val SPACE_MARINE_URL = "http://127.0.0.1:8080/space-marines-api-0.0.1-SNAPSHOT/api/v1"
    }
}
