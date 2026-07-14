package com.yallahmasyaf.app.domain.repository

import com.yallahmasyaf.app.domain.model.CoastalUnit

interface CoastalRepository {
    
    /**
     * Retrieves all available coastal units.
     */
    suspend fun getCoastalUnits(): List<CoastalUnit>

    /**
     * Retrieves a specific coastal unit by its ID.
     */
    suspend fun getCoastalUnitById(id: String): CoastalUnit?

    /**
     * Filters coastal units based on city and/or maximum price per night.
     */
    suspend fun filterCoastalUnits(city: String?, maxPrice: Double?): List<CoastalUnit>

    /**
     * Toggles the favorite status of a coastal unit by its ID.
     */
    suspend fun toggleFavorite(unitId: String)

    /**
     * Retrieves all units marked as favorites by the user.
     */
    suspend fun getFavoriteUnits(): List<CoastalUnit>
}
