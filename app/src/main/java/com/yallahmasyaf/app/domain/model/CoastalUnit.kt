package com.yallahmasyaf.app.domain.model

data class CoastalUnit(
    val id: String,
    val title: String,
    val description: String,
    val images: List<String>,
    val city: String,
    val district: String,
    val pricePerNight: Double,
    val minStayDays: Int,
    val bedroomCount: Int,
    val bathroomCount: Int,
    val bedCount: Int,
    val rating: Double,
    val reviewCount: Int,
    
    // Amenities & Features
    val hasKitchen: Boolean,
    val hasAirConditioning: Boolean,
    val hasWifi: Boolean,
    val hasPool: Boolean,
    val hasGarden: Boolean,
    val hasBeachAccess: Boolean,
    val requiresBeachCard: Boolean,
    val hasParking: Boolean,
    val hasElevator: Boolean,
    
    // Policies
    val isPetsAllowed: Boolean,
    val isFamiliesOnly: Boolean,
    val isBachelorAllowed: Boolean,
    
    // Check-in and Check-out times
    val checkInTime: String,
    val checkOutTime: String,
    
    val isFavorite: Boolean = false
)
