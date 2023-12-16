package com.example.phonepeassignment.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class VenueResponse(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("venues")
	val venues: MutableList<VenuesItem>
)

@Entity(tableName = "venues_table")
data class VenuesItem(

	@field:SerializedName("display_location")
	val displayLocation: String? = null,

	@field:SerializedName("country")
	val country: String? = null,


	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("capacity")
	val capacity: Int? = null,

	@field:SerializedName("extended_address")
	val extendedAddress: String? = null,

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("name_v2")
	val nameV2: String? = null,

	@field:SerializedName("num_upcoming_events")
	val numUpcomingEvents: Int? = null,

	@field:SerializedName("metro_code")
	val metroCode: Int? = null,

	@field:SerializedName("popularity")
	val popularity: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("has_upcoming_events")
	val hasUpcomingEvents: Boolean? = null
)

data class Stats(

	@field:SerializedName("event_count")
	val eventCount: Int? = null
)


data class Geolocation(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("range")
	val range: String? = null,

	@field:SerializedName("lon")
	val lon: Any? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("lat")
	val lat: Any? = null
)

data class Meta(

	@field:SerializedName("took")
	val took: Int? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("geolocation")
	val geolocation: Geolocation? = null
)
