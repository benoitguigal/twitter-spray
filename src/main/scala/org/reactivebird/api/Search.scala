package org.reactivebird.api

import org.reactivebird.{TwitterApi, version}
import org.joda.time.DateTime
import spray.json.{JsArray, DeserializationException, JsonParser}


trait Search {
  self: TwitterApi =>

  def search(
      q: String,
      geocode: Option[String] = None,
      lang: Option[String] = None,
      locale: Option[String] = None,
      until: Option[DateTime] = None,
      resultType: Option[String] = None,
      includeEntities: Option[Boolean] = None)(implicit page: MaxIdPage = MaxIdPage(None, None, None)) = {

    val params = Seq(
      Some("q" -> q),
      geocode map ("geocode" -> _),
      lang map ("lang" -> _),
      locale map ("locale" -> _),
      resultType map ("result_type" -> _),
      includeEntities map ("include_entities" -> _.toString),
      page.count map ("count" -> _.toString),
      page.sinceId map ("since_id" -> _.toString),
      page.maxId map ("max_id" -> _.toString)).flatten.toMap

    get(s"/$version/search/tweets.json", params) map { r =>
      JsonParser(r.entity.asString).asJsObject.getFields("statuses") match {
        case Seq(JsArray(statuses)) => statuses map (_.convertTo[Status])
        case _ => throw new DeserializationException("Failed parsing sequence of tweets")
      }
    }

  }

}
