## Twitter-Spray

***
Twitter-Spray is a scala library based on Spray and Akka for accessing the Twitter REST APIv1.1. Support for OAuth. The streaming API will be added in the future.

### Features
- Fully asynchronous (built on top of akka). API calls are wrapped in `scala.concurrent.Future`
- Support for OAuth sign-in flow
- Successful calls returns instances of scala classes. Defaults models are provided but you can also provide your own for convenience
- Twitter errors are returns as subtypes of `TwitterError`
- Support for paginating timelines and navigating collections

### Get Twitter-Spray

Twitter-Spray for scala 2.10.2 is available on Sonatype.

```
libraryDependencies += "me.benoitguigal" %% "twitter" % "1.1-SNAPSHOT"

resolvers += Resolver.sonatypeRepo("snapshots")
```

No stable version yet

### Usage

```
val consumer = Consumer("your-consumer-key", "your-consumer-secret")
val token = Token("your-token-key", "your-token-secret")
val twitterApi = new TwitterApi(consumer, token)
val timeline: Future[Seq[Status]] = twitterApi.homeTimeline()
```

### Pagination

When working with timelines or large lists of items, you will need to paginate through the result set.

```
/// pagination for timelines, working with sinceId and maxId
val paging = IdPaging(twitterApi.userTimeline(screenName = Some("BGuigal"))(_))
val tweets: Future[Seq[Status]] = paging.items(500) // retrieves 500 most recent tweets
val pages: Future[Seq[Seq[Status]] = paging.pages(3, 20) // retrieves 3 pages of 20 tweets
```

```
/// pagination for followers list, working with cursors
val paging = CursorPaging(twitterApi.followersIds(screenName = Some("BGuigal"))(_))
val followersIds: Future[Seq[String]] = paging.items(3000) // retrieves 3000 followers
val pages: Future[Seq[Seq[String]] = paging.pages(2, 1500) // retrieves 2 pages of 1500 followers
```

May the rate limit be hit in the process, the pagination will stop and return all the items that were
successfully retrieved


### Providing your own models

It is possible to provide your own models for User, Status, Place, etc. Just override the desired fields from the `ModelFactory` trait.

```
class MyStatus
implicit myStatusFormat: JsonFormat[MyStatus]

val twitterApi = new TwitterApi(consumer, token) {
    override type Status = MyStatus
    override implicit val statusFormat = myStatusFormat
}

```


### OAuth flow

```
import me.benoitguigal.twitter.oauth.OAuthHandler

val consumer = Consumer("your-consumer-key", "your-consumer-secret")
val auth = OAuthHandler(consumer)

/// Get a request token
val authorizationUrl = auth.authorizationUrl("your-oauth-callback")

/// Redirect the user to authorizationUrl for sign-in
/// Once signed in the user is redirected to "your-oauth-callback"
/// extract oauth_token and oauth_verifier from the query string and then request an access token

val accessToken = auth.accessToken("oauth_token", "oauth_verifier")
/// Store token information and access protected resources
```


### License
Twitter-spray is free software licensed under the MIT/X11 license. Details provided in the LICENSE file.
