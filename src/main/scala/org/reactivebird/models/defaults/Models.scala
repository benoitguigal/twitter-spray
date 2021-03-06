package org.reactivebird.models.defaults

import org.joda.time.DateTime
import org.reactivebird.models.CanBeIdentified


case class User(
    created_at: DateTime,
    followers_count: Int,
    friends_count: Int,
    id: Long,
    id_str: String,
    name: String,
    screen_name: String,
    statuses_count: Int) extends CanBeIdentified

case class Status(
    coordinates: Option[Coordinates],
    created_at: DateTime,
    entities: Entities,
    favorite_count: Option[Int],
    id: Long,
    id_str: String,
    retweet_count: Int,
    retweeted: Boolean,
    source: String,
    text: String,
    user: User) extends CanBeIdentified

case class Place(
    country: String,
    country_code: String,
    full_name: String,
    id: String,
    name: String,
    place_type: String,
    url: String)

case class Entities(
    hashtags: Option[Seq[Hashtag]],
    media: Option[Seq[Media]],
    urls: Option[Seq[URL]],
    user_mentions: Option[Seq[UserMention]])

case class Coordinates(longitude: Double, latitude: Double)

case class Hashtag(indices: Seq[Int], text: String)

case class Media(
    display_url: String,
    expanded_url: String,
    id: Long,
    id_str: String,
    indices: Seq[Int],
    media_url: String,
    url: String)

case class URL(display_url: String, expanded_url: String, indices: Seq[Int], url: String)

case class UserMention(id: Long, id_str: String, indices: Seq[Int], name: String, screen_name: String)

case class DirectMessage(
    created_at: DateTime,
    entities: Entities,
    id: Long,
    id_str: String,
    recipient_id: Long,
    recipient_screen_name: String,
    sender_id: Long,
    sender_screen_name: String,
    text: String)


case class Source(
    id: Long,
    id_str: String,
    screen_name: String,
    following: Boolean,
    followed_by: Boolean)

case class Target(
    id: Long,
    id_str: String,
    screen_name: String,
    following: Boolean,
    followed_by: Boolean)

case class Relationship(source: Source, target: Target)

case class Friendship(relationship: Relationship)

case class SavedSearch(
    created_at: DateTime,
    id: Long,
    id_str: String,
    name: String,
    query: String)

case class SearchResults(
    statuses: Seq[Status],
    search_metadata: SearchMetaData)

case class SearchMetaData(
    max_id: Long,
    refresh_url: String,
    next_results: String,
    count: Int,
    completed_in: Double,
    since_id_str: String,
    query: String,
    max_id_str: String)

case class Settings(
    always_use_https: Boolean,
    discoverable_by_email: Boolean,
    language: String,
    screen_name: String,
    time_zone: Timezone,
    trend_location: Option[Seq[Location]],
    use_cookie_personalization: Boolean)

case class Timezone(name: String, tzinfo_name: String, utc_offset: Long)

case class Location(country: String, countryCode: String, name: String, parentid: Long, url: String, woeid: Long)




