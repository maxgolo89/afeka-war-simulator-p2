package ws;

import static spark.Spark.get;

import bl.MissileLauncher;
import bl.WarModel;
import program.IConstants;
import spark.Request;
import spark.Response;
import spark.Route;

public class testWS {
    private WarModel war;

  public void startWS(){

      war = WarModel.getInstance();

      get("/addLauncher/:id/:isHidden", (request, response) -> {                                                                        // Expose basic commands to restful ws.
          String id = request.params(":id");
          String hidden = request.params(":isHidden");
          boolean isHidden = false;
          if(hidden.equals("true")){
              isHidden = true;
          }
          war.addMissileLauncher(id,isHidden);
          return "Missile Launcher " + id + " started";
      });
      get("/addLauncherDestructor/:id/:type", (request, response) -> {
          String id = request.params(":id");
          String type = request.params(":type");
          IConstants.LauncherDestructorType destructorType = IConstants.LauncherDestructorType.SHIP;
          if(type.equals("plane")){
              destructorType = IConstants.LauncherDestructorType.PLANE;
          }
          war.addLauncherDestructor(id,destructorType);
          return "Missile Launcher Destructor " + id + " started";
      });
      get("/addMissileDestructor/:id", (request, response) -> {
          String id = request.params(":id");
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
