package controllers

import ie.setu.controllers.LocationAPI
import ie.setu.models.Location
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LocationAPITest {

    private var location1: Location? = null
    private var location2: Location? = null
    private var location3: Location? = null
    private var location4: Location? = null
    private var location5: Location? = null
    private var populatedLocations: LocationAPI? = LocationAPI()
    private var emptyLocations: LocationAPI? = LocationAPI()

    @BeforeEach
    fun setup(){
        location1 = Location(1, 2, 3, false)
        location2 = Location(2, 2, 4, false)
        location3 = Location(1, 1, 5, false)
        location4 = Location(6, 6, 7, false)
        location5 = Location(8, 9, 9, false)

        //adding 5 Note to the notes api
        populatedLocations!!.add(location1!!)
        populatedLocations!!.add(location2!!)
        populatedLocations!!.add(location3!!)
        populatedLocations!!.add(location4!!)
        populatedLocations!!.add(location5!!)
    }

    @AfterEach
    fun tearDown(){
        location1 = null
        location2 = null
        location3 = null
        location4 = null
        location5 = null
        populatedLocations = null
        emptyLocations = null
    }

    @Test
    fun `adding a Location to a populated list adds to ArrayList`(){
        val newLocation = Location(4, 5, 9, false)
        assertTrue(populatedLocations!!.add(newLocation))
    }

    @Test
    fun `adding a Location to an empty list adds to ArrayList`(){
        val newLocation = Location(6, 6, 1, false)
        assertTrue(emptyLocations!!.add(newLocation))
    }
}