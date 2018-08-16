package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mvc.WarController;

public class App extends Application {

	private static WarUI warUI;
	private WarFxmlController warUIController;

	@Override
	public void start(final Stage stage) throws Exception {
		try {
			// get layout from fxml-file
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(UIConstants.MAIN_PANE_FXML_PATH));
			BorderPane borderPane = (BorderPane) loader.load();
			warUIController = (WarFxmlController) loader.getController();
			
			// set scene on the stage
			Scene scene = new Scene(borderPane);
			scene.getStylesheets().add("ui/war.css");
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			warUI = new War(warUIController.getWarPane(), warUIController.getStatisticsPane() );
			WarController controller = new WarController();
			controller.addView(warUI);
			warUI.showReadFromConfigMenu();
			
			AnimationTimer timer = new MyTimer();
	        timer.start();
			
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	private class MyTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            doHandle();
        }

        private void doHandle() {
//        	for (WarMovingObject mvo : War.getAllMovingObjects()) {
//        		setMovingObject(WarMovingObject o, double x, double y, int time){
//        		War.setMovingObject(mvo, mvo.getX0() + mvo.getvX(), mvo.getY0() + mvo.getvY(), 8);
//        		mvo.update();
        		//mvo.
//        	}
//        	WarMovingObject missile = War.getMovingObjectById("L101");
//			if (missile == null)
//				return;
//			double targetX = missile.getTranslateX() + missile.getvX() * 1;
//			if ( targetX > War.pane.getWidth() || targetX < 0)
//				targetX =  War.pane.getWidth() / 2;
//			double targetY = missile.getTranslateY() + missile.getvY() * 1;
//			if ( targetY < 0 || targetY > War.pane.getHeight())
//				targetY =  War.pane.getHeight() / 2;
//			
//			WarMovingObject dMissile = new WarMovingObject("L101" + "L102", War.D_MISSILE_IMAGE_PATH, false,
//					1, 1, targetX, targetY, 1);
//			synchronized (War.allMovingObjects) {
//				War.allMovingObjects.add(dMissile);
//			}
//			War.setMovingObject( dMissile , targetX, targetY, 1);
        }
    }
	
	public static WarUI getWarPane(){
		return warUI;
	}
	
	public void stop() {
		System.exit(1);
	}
}