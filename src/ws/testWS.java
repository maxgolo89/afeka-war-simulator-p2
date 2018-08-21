package ws;

import static spark.Spark.get;

import spark.Request;
import spark.Response;
import spark.Route;

public class testWS {

  public void startWS(){
      get("/hello/:name", (request, response) -> {
          return "Hello " + request.params(":name");
      });
  }
}
