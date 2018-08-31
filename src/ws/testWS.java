package ws;

import static spark.Spark.get;
import static spark.Spark.post;

import bl.WarModel;
import program.IConstants;

public class testWS {
    private WarModel war;

  public void startWS(){

      war = WarModel.getInstance();

      post("/addMissileLauncher", ((request, response) -> {
          String id = request.queryParams("id");
          String hidden = request.queryParams("isHidden");
          boolean isHidden = false;
          if(hidden.equals("true")){
              isHidden = true;
          }
          war.addMissileLauncher(id,isHidden);
          return "Missile Launcher " + id + " started";
      }));

      post("/addLauncherDestructor", (request, response) -> {
          String id = request.queryParams("id");
          String type = request.queryParams("type");
          IConstants.LauncherDestructorType destructorType = IConstants.LauncherDestructorType.SHIP;
          if(type.equals("plane")){
              destructorType = IConstants.LauncherDestructorType.PLANE;
          }
          war.addLauncherDestructor(id,destructorType);
          return "Missile Launcher Destructor " + id + " started";
      });
      post("/addMissileDestructor", (request, response) -> {
          String id = request.queryParams("id");
          war.addMissileDestructor(id);
          return "Missile Destructor " + id + " started";
      });
      post("/launchMissile", (request, response) -> {
          String launcherId = request.queryParams("launcherId");
          String missileId = request.queryParams("missileId");
          int damage = Integer.parseInt(request.queryParams("damage"));
          String destination = request.queryParams("destination");
          int flyTime = Integer.parseInt(request.queryParams("flyTime"));
          war.addMissileLaunch(launcherId,missileId,damage,destination,flyTime);
          return "Missile " + missileId + " launched from launcher " + launcherId + " flies towards " + destination + ", fly time : " + flyTime + ", pot. damage " + damage;
      });
      post("/destructMissile", (request, response) -> {
          String destructorId = request.queryParams("destructorId");
          String missileId = request.queryParams("missileId");
          war.addMissileDestruct(destructorId,missileId);
          return "Trying to destruct missile " + missileId + " from missile destructor " + destructorId;
      });
      post("/destructLauncher", (request, response) -> {
          String launcherId = request.queryParams("launcherId");
          String destructorId = request.queryParams("destructorId");
          war.addLauncherDestruct(destructorId,launcherId);
          return "Trying to destruct launcher " + launcherId + " from missile launcher destructor " + destructorId;
      });
      get("/statistics", ((request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
          response.cookie("name","dadwwaw");
          return war.getAllLaunchers();

      }));
  }
}
