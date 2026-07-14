package com.yallahmasyaf.app.data.repository

import com.yallahmasyaf.app.data.datasource.MockCoastalDataSource
import com.yallahmasyaf.app.domain.model.CoastalUnit
import com.yallahmasyaf.app.domain.repository.CoastalRepository

class CoastalRepositoryImpl(
    private val dataSource: MockCoastalDataSource
) : CoastalRepository {

    override suspend fun getCoastalUnits(): List<CoastalUnit> {
        return dataSource.getUnits()
    }

    override suspend fun getCoastalUnitById(id: String): CoastalUnit? {
        return dataSource.getUnits().firstOrNull { it.id == id }
    }

    override suspend fun filterCoastalUnits(city: String?, maxPrice: Double?): List<CoastalUnit> {
        return dataSource.getUnits().filter { unit ->
            val matchesCity = city.isNullOrBlank() || unit.city.equals(city, ignoreCase = true)
            val matchesPrice = maxPrice == null || unit.pricePerNight <= maxPrice
            matchesCity && matchesPrice
        }
    }

    override suspend fun toggleFavorite(unitId: String) {
        dataSource.toggleFavorite(unitId)
    }

    override suspend fun getFavoriteUnits(): List<CoastalUnit> {
        return dataSource.getUnits().filter { it.isFavorite }
    }
}
