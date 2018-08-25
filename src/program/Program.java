package program;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.AppConfiguration;
import ui.App;

import java.util.Scanner;

public class Program implements IConstants, CommandLineRunner {

	static Scanner s = new Scanner(System.in);
	public static final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
	
	public static void main(String[] args) {

        SpringApplication.run(Program.class, args);
//		System.out.println(CONSOLE_OR_JFX);
//		
//		if ( s.nextInt() != 1 )
//			javafx.application.Application.launch(App.class);
//		else
//			new ConsoleView().init();

	}

    @Override
    public void run(String... args) throws Exception {

	    try {

        javafx.application.Application.launch(App.class);
        } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println(ex.getMessage());
        }
    }

    public static AnnotationConfigApplicationContext getContext() {
	    return ctx;

    }
}
