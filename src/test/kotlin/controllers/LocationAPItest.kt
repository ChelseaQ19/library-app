package controllers

import ie.setu.controllers.LocationAPI
import ie.setu.models.Location
import ie.setu.persistance.XMLSerializer
import ie.setu.persistance.JSONSerializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

class LocationAPITest {

    private var location1: Location? = null
    private var location2: Location? = null
    private var location3: Location? = null
    private var location4: Location? = null
    private var location5: Location? = null
    private var populatedLocations: LocationAPI? = LocationAPI(XMLSerializer(File("locations.xml")))
    private var emptyLocations: LocationAPI? = LocationAPI(XMLSerializer(File("empty-locations.xml")))

    @BeforeEach
    fun setup() {
        location1 = Location(9, 1, "Shelf A", 3, false)
        location2 = Location(13, 2, "Shelf D", 3, false)
        location3 = Location(14, 1, "Shelf G", 5, false)
        location4 = Location(10, 6, "Shelf B", 7, false)
        location5 = Location(11, 8, "Shelf C", 9, false)

        //adding 5 Note to the notes api
        populatedLocations!!.add(location1!!)
        populatedLocations!!.add(location2!!)
        populatedLocations!!.add(location3!!)
        populatedLocations!!.add(location4!!)
        populatedLocations!!.add(location5!!)
    }

    @AfterEach
    fun tearDown() {
        location1 = null
        location2 = null
        location3 = null
        location4 = null
        location5 = null
        populatedLocations = null
        emptyLocations = null
    }

    @Nested
    inner class AddLocations {
        @Test
        fun `adding a Location to a populated list adds to ArrayList`() {
            val newLocation = Location(11, 4, "Shelf D", 9, false)
            assertTrue(populatedLocations!!.add(newLocation))
        }

        @Test
        fun `adding a Location to an empty list adds to ArrayList`() {
            val newLocation = Location(12, 6, "Shelf E", 1, false)
            assertTrue(emptyLocations!!.add(newLocation))
        }
    }

    @Nested
    inner class ListLocations {

        @Test
        fun `listAllLocations returns No Locations Stored message when ArrayList is empty`() {
            assertEquals(0, emptyLocations!!.numberOfLocations())
            assertTrue(emptyLocations!!.listAllLocations().lowercase().contains("no locations"))
        }
        //make sure that test contains an element from the book, such as title.
        @Test
        fun `listAllLocations returns Locations when ArrayList has locations stored`() {
            assertEquals(5, populatedLocations!!.numberOfLocations())
            val locationsString = populatedLocations!!.listAllLocations()

            println("Locations String: $locationsString")

            assertTrue(locationsString.contains("locationId=9, locationAisle=1, locationShelf=Shelf A, locationIndex=3"))
            assertTrue(locationsString.contains("locationId=13, locationAisle=2, locationShelf=Shelf D, locationIndex=3"))
            assertTrue(locationsString.contains("locationId=14, locationAisle=1, locationShelf=Shelf G, locationIndex=5"))
            assertTrue(locationsString.contains("locationId=10, locationAisle=6, locationShelf=Shelf B, locationIndex=7"))
            assertTrue(locationsString.contains("locationId=11, locationAisle=8, locationShelf=Shelf C, locationIndex=9"))
        }
    }

    @Nested
    inner class PersistenceTests {

        @Test
        fun `saving and loading an empty collection in XML doesn't crash app`() {

            val storingLocations = LocationAPI(XMLSerializer(File("locations.xml")))
            storingLocations.store()


            val loadedLocations = LocationAPI(XMLSerializer(File("locations.xml")))
            loadedLocations.load()


            assertEquals(0, storingLocations.numberOfLocations())
            assertEquals(0, loadedLocations.numberOfLocations())
            assertEquals(storingLocations.numberOfLocations(), loadedLocations.numberOfLocations())
        }

        @Test
        fun `saving and loading an loaded collection in XML doesn't loose data`() {

            val storingLocations = LocationAPI(XMLSerializer(File("locations.xml")))
            storingLocations.add(location1!!)
            storingLocations.add(location4!!)
            storingLocations.add(location5!!)
            storingLocations.store()


            val loadedLocations = LocationAPI(XMLSerializer(File("locations.xml")))
            loadedLocations.load()


            assertEquals(3, storingLocations.numberOfLocations())
            assertEquals(3, loadedLocations.numberOfLocations())
            assertEquals(storingLocations.numberOfLocations(), loadedLocations.numberOfLocations())
            assertEquals(storingLocations.findLocation(1), loadedLocations.findLocation(1))
            assertEquals(storingLocations.findLocation(4), loadedLocations.findLocation(4))
            assertEquals(storingLocations.findLocation(5), loadedLocations.findLocation(5))
        }
    }

    @Test
    fun `saving and loading an empty collection in JSON doesn't crash app`() {

        val storingLocations = LocationAPI(JSONSerializer(File("locations.json")))
        storingLocations.store()


        val loadedLocations = LocationAPI(JSONSerializer(File("locations.json")))
        loadedLocations.load()


        assertEquals(0, storingLocations.numberOfLocations())
        assertEquals(0, loadedLocations.numberOfLocations())
        assertEquals(storingLocations.numberOfLocations(), loadedLocations.numberOfLocations())
    }

    @Test
    fun `saving and loading an loaded collection in JSON doesn't loose data`() {

        val storingLocations = LocationAPI(JSONSerializer(File("locations.json")))
        storingLocations.add(location1!!)
        storingLocations.add(location4!!)
        storingLocations.add(location5!!)
        storingLocations.store()


        val loadedLocations = LocationAPI(JSONSerializer(File("locations.json")))
        loadedLocations.load()


        assertEquals(3, storingLocations.numberOfLocations())
        assertEquals(3, loadedLocations.numberOfLocations())
        assertEquals(storingLocations.numberOfLocations(), loadedLocations.numberOfLocations())
        assertEquals(storingLocations.findLocation(1), loadedLocations.findLocation(1))
        assertEquals(storingLocations.findLocation(4), loadedLocations.findLocation(4))
        assertEquals(storingLocations.findLocation(5), loadedLocations.findLocation(5))
    }
}
