package com.yallahmasyaf.app.data.datasource

import com.yallahmasyaf.app.domain.model.CoastalUnit

class MockCoastalDataSource {

    // Persistent in-memory cache of units list to preserve state changes (such as favorites)
    private val unitsList = mutableListOf(
        CoastalUnit(
            id = "1",
            title = "شاليه فاخر صف أول على البحر مباشرة - مراسي",
            description = "استمتع بإقامة فاخرة في أرقى قرى الساحل الشمالي (مراسي سيدي عبد الرحمن). الشاليه صف أول على البحر بإطلالة كاملة ومباشرة، مجهز بالكامل بأرقى قطع الأثاث والأجهزة الكهربائية الحديثة، بالقرب من الكلوب هاوس والمنطقة الترفيهية.",
            images = listOf(
                "https://images.unsplash.com/photo-1540555700478-4be289fbecef?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=800&q=80"
            ),
            city = "الساحل الشمالي",
            district = "مراسي (سيدي عبد الرحمن)",
            pricePerNight = 8000.0,
            minStayDays = 3,
            bedroomCount = 3,
            bathroomCount = 2,
            bedCount = 4,
            rating = 4.8,
            reviewCount = 24,
            hasKitchen = true,
            hasAirConditioning = true,
            hasWifi = true,
            hasPool = true,
            hasGarden = false,
            hasBeachAccess = true,
            requiresBeachCard = true,
            hasParking = true,
            hasElevator = false,
            isPetsAllowed = false,
            isFamiliesOnly = true,
            isBachelorAllowed = false,
            checkInTime = "02:00 PM",
            checkOutTime = "12:00 PM",
            isFavorite = false
        ),
        CoastalUnit(
            id = "2",
            title = "فيلا خاصة مع مسبح خاص وحديقة - الجونة",
            description = "فيلا مستقلة راقية جداً في مارينا الجونة مع حمام سباحة خاص مدفأ وحديقة واسعة تطل على البحيرة مباشرة. الفيلا تضمن خصوصية تامة ومجهزة بإنترنت فائق السرعة ومطبخ كامل وموقف سيارات مجاني لعدة سيارات.",
            images = listOf(
                "https://images.unsplash.com/photo-1613977257363-707ba9348227?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1580587771525-78b9dba3b914?auto=format&fit=crop&w=800&q=80"
            ),
            city = "الغردقة",
            district = "الجونة (مارينا)",
            pricePerNight = 15000.0,
            minStayDays = 5,
            bedroomCount = 4,
            bathroomCount = 4,
            bedCount = 6,
            rating = 4.95,
            reviewCount = 12,
            hasKitchen = true,
            hasAirConditioning = true,
            hasWifi = true,
            hasPool = true,
            hasGarden = true,
            hasBeachAccess = true,
            requiresBeachCard = false,
            hasParking = true,
            hasElevator = false,
            isPetsAllowed = true,
            isFamiliesOnly = false,
            isBachelorAllowed = true,
            checkInTime = "03:00 PM",
            checkOutTime = "12:00 PM",
            isFavorite = false
        ),
        CoastalUnit(
            id = "3",
            title = "شاليه بإطلالة بانورامية ساحرة - بورتو السخنة",
            description = "شاليه رائع يقع في المرتفعات ببورتو العين السخنة مع إطلالة بانورامية ساحرة على البحر الأحمر وحمامات السباحة. القرية بها كافة الخدمات من مطاعم وتلفريك ومحلات تجارية، والشاليه مجهز بمكيفات وشاشة ذكية ومصعد للوصول السهل.",
            images = listOf(
                "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688?auto=format&fit=crop&w=800&q=80"
            ),
            city = "العين السخنة",
            district = "بورتو السخنة",
            pricePerNight = 3500.0,
            minStayDays = 2,
            bedroomCount = 2,
            bathroomCount = 1,
            bedCount = 3,
            rating = 4.3,
            reviewCount = 45,
            hasKitchen = true,
            hasAirConditioning = true,
            hasWifi = false,
            hasPool = true,
            hasGarden = false,
            hasBeachAccess = false,
            requiresBeachCard = false,
            hasParking = true,
            hasElevator = true,
            isPetsAllowed = false,
            isFamiliesOnly = true,
            isBachelorAllowed = true,
            checkInTime = "02:00 PM",
            checkOutTime = "11:00 AM",
            isFavorite = false
        ),
        CoastalUnit(
            id = "4",
            title = "شقة كلاسيكية تطل على البحر - المعمورة الشاطئ",
            description = "شقة مصيفية كلاسيكية ومريحة تقع داخل منطقة المعمورة الشاطئ بالإسكندرية ذات الحراسة الخاصة والشواطئ المميزة. الشقة قريبة جداً من المنتزه والمطاعم والخدمات، وتوفر إقامة هادئة للعائلات مع بلكونة بإطلالة مباشرة ع البحر.",
            images = listOf(
                "https://images.unsplash.com/photo-1484154218962-a197022b5858?auto=format&fit=crop&w=800&q=80"
            ),
            city = "الإسكندرية",
            district = "المعمورة الشاطئ",
            pricePerNight = 2000.0,
            minStayDays = 2,
            bedroomCount = 2,
            bathroomCount = 1,
            bedCount = 3,
            rating = 4.1,
            reviewCount = 30,
            hasKitchen = true,
            hasAirConditioning = true,
            hasWifi = false,
            hasPool = false,
            hasGarden = false,
            hasBeachAccess = true,
            requiresBeachCard = true,
            hasParking = false,
            hasElevator = true,
            isPetsAllowed = false,
            isFamiliesOnly = true,
            isBachelorAllowed = false,
            checkInTime = "01:00 PM",
            checkOutTime = "12:00 PM",
            isFavorite = false
        ),
        CoastalUnit(
            id = "5",
            title = "كوخ خشبي هادئ ومريح على الشاطئ مباشرة - دهب",
            description = "اهرب من صخب المدينة واستمتع بالهدوء التام في هذا الكوخ الخشبي البيئي المصمم بأسلوب سيناوي دافئ في مدينة دهب الجميلة. الكوخ يقع مباشرة على البحر بالقرب من مناطق الغوص ومناسب جداً للشباب ومحبي الحيوانات الأليفة والاسترخاء.",
            images = listOf(
                "https://images.unsplash.com/photo-1499793983690-e29da59ef1c2?auto=format&fit=crop&w=800&q=80"
            ),
            city = "دهب",
            district = "البلو هول / العصلة",
            pricePerNight = 1800.0,
            minStayDays = 1,
            bedroomCount = 1,
            bathroomCount = 1,
            bedCount = 2,
            rating = 4.7,
            reviewCount = 18,
            hasKitchen = false,
            hasAirConditioning = false,
            hasWifi = true,
            hasPool = false,
            hasGarden = true,
            hasBeachAccess = true,
            requiresBeachCard = false,
            hasParking = true,
            hasElevator = false,
            isPetsAllowed = true,
            isFamiliesOnly = false,
            isBachelorAllowed = true,
            checkInTime = "12:00 PM",
            checkOutTime = "10:00 AM",
            isFavorite = false
        ),
        CoastalUnit(
            id = "6",
            title = "شاليه صف ثاني مودرن مع حديقة - أمواج",
            description = "شاليه أرضي عصري في قرية أمواج الشهيرة بالساحل الشمالي. يحتوي على حديقة خاصة صغيرة لعمل حفلات الشواء والاسترخاء، مجهز بكافة الأجهزة الحديثة ومكيف بالكامل، مع توافر كروت الشاطئ للدخول.",
            images = listOf(
                "https://images.unsplash.com/photo-1564013799919-ab600027ffc6?auto=format&fit=crop&w=800&q=80"
            ),
            city = "الساحل الشمالي",
            district = "أمواج (سيدي عبد الرحمن)",
            pricePerNight = 5500.0,
            minStayDays = 3,
            bedroomCount = 2,
            bathroomCount = 2,
            bedCount = 3,
            rating = 4.5,
            reviewCount = 15,
            hasKitchen = true,
            hasAirConditioning = true,
            hasWifi = true,
            hasPool = true,
            hasGarden = true,
            hasBeachAccess = false,
            requiresBeachCard = true,
            hasParking = true,
            hasElevator = false,
            isPetsAllowed = false,
            isFamiliesOnly = true,
            isBachelorAllowed = false,
            checkInTime = "02:00 PM",
            checkOutTime = "12:00 PM",
            isFavorite = false
        )
    )

    fun getUnits(): List<CoastalUnit> {
        return unitsList
    }

    /**
     * Finds unit by ID and toggles its isFavorite flag state.
     */
    fun toggleFavorite(unitId: String) {
        val index = unitsList.indexOfFirst { it.id == unitId }
        if (index != -1) {
            val currentUnit = unitsList[index]
            unitsList[index] = currentUnit.copy(isFavorite = !currentUnit.isFavorite)
        }
    }
}
