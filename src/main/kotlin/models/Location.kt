package ie.setu.models

data class Location(val locationId: Int,
                    val locationAisle: Int,
                    val locationShelf: Int,
                    val locationIndex: Int,
                    val isLocationArchived :Boolean) {
}