package ws;

import static spark.Spark.get;
import static spark.Spark.post;

import bl.MissileLauncher;
import bl.WarModel;
import program.IConstants;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;
import java.util.Set;

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
      get("/launchMissile/:launcherId/:missileId/:damage/:destination/:flyTime", (request, response) -> {
          String launcherId = request.params(":launcherId");
          String missileId = request.params(":missileId");
          int damage = Integer.parseInt(request.params(":damage"));
          String destination = request.params(":destination");
          int flyTime = Integer.parseInt(request.params(":flyTime"));
          war.addMissileLaunch(launcherId,missileId,damage,destination,flyTime);
          return "Missile " + missileId + " launched from launcher " + launcherId + " flies towards " + destination + ", reach time : " + flyTime + ", pot. damage " + damage;
      });
      get("/destructMissile/:destructorId/:missileId", (request, response) -> {
          String destructorId = request.params(":destructorId");
          String missileId = request.params(":missileId");
          war.addMissileDestruct(destructorId,missileId);
          return "trying to destruct missile " + missileId + " from missile destructor " + destructorId;
      });
      get("/destructLauncher/:destructorId/:launcherId", (request, response) -> {
          String launcherId = request.params(":launcherId");
          String destructorId = request.params(":destructorId");
          war.addLauncherDestruct(destructorId,launcherId);
          return "trying to destruct launcher " + launcherId + " from missile launcher destructor " + destructorId;
      });
  }
}
