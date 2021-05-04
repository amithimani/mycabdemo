package basic;
import io.gatling.http.Predef._
import io.gatling.core.Predef._

class GatlingTest extends Simulation {
  val httpConf = http
    .baseUrl("http://localhost:8080/v1/drivers?onlineStatus=ONLINE")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("GetDriverList")
    .exec(http("Get Drivers Request")
      .get(""))
    .pause(1)

  setUp(
    scn.inject(atOnceUsers(100))
  ).protocols(httpConf)
}
