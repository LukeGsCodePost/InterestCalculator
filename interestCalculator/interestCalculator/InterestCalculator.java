package interestCalculator;

import java.time.LocalDate;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;


//Imports for layout.
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


import javafx.scene.control.DatePicker;
//Imports for components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.util.Duration;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class InterestCalculator extends Application {

	Label lblCapital, lblInterest, lblTerm;
	//Date types.
	LocalDate startDate, endDate;
		
	//Datepicker controls for the dialog.
	DatePicker dpStartDate, dpEndDate;
	
	public InterestCalculator() {//constructor
	
	}
	
	//Index for sentence display.
	int i = 0;
	
	//Int for interest rate
	int InterestRate = 0;
	
	//term years.
	int TermYears = 0;
	
	//Textfields for display and typing.
	TextField txtCapital, txtInterestRate, txtTermYears;
	
     //text area
	TextArea txtfTyping;
	
	
	//Buttons. Start and Stop.
	Button btnQuit, btnCalculate, btnDot;
	
	//CheckMessages
	CheckBox chkMessage1;

	
	public void init(){
   //The textfields.
		
	txtCapital = new TextField();
	txtInterestRate = new TextField();
	txtTermYears = new TextField();
	txtfTyping = new TextArea();
	//dot button
	btnDot = new Button("Investment Term");
	btnDot.setMinWidth(20);
	
	//Start and stop buttons.
	btnQuit = new Button("Quit");
	btnCalculate = new Button("Calculate");
	//DatePickers
	dpStartDate = new DatePicker();
	dpEndDate = new DatePicker();	
	//declares all above at class level
	
	btnDot.setOnAction(ae -> DateSelector());
	
	}
	private void DateSelector(){
		//Create a stage for the dialog.
		Stage dialog = new Stage();
		
		//Set the title.
		dialog.setTitle("Select Investment Term");	
		
		//Labels
		Label lblStart = new Label("Start date:");
		Label lblEnd = new Label("End date:");
		
		
		
		
		//Manage label sizes.,
		lblStart.setMinWidth(70);
		
	    
	    
		//Buttons for the dialog.
		Button btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");
		
		//Manage button sizes.
		btnOK.setMinWidth(70);
		btnCancel.setMinWidth(70);
		
		
		//Handle events on the dialog OK button.
		btnOK.setOnAction(ae ->{

			
			//picks up date from date dialog and puts the year difference into investment term
			long date1 = dpStartDate.getValue().toEpochDay();
			long date2 = dpEndDate.getValue().toEpochDay();
			int  days  = (int) Math.abs(date1 - date2);
			int years = 365;
			int end = days/years;
			String strI = String.valueOf(end);
			txtTermYears.setText(strI);
		
			
			
			dialog.close();
		});
		
		
		btnCancel.setOnAction(ae -> {
			
			//Cancel the dialog. Just close it.
			dialog.close();
			
		});
		
		//Create a layout for the dialog.
		GridPane dgp = new GridPane();
		
		//Put padding around the gridpane.
		dgp.setPadding(new Insets(30));
		
		//Set the h and v gaps.
		dgp.setHgap(30);
		dgp.setVgap(30);
		
		//Add components to the gridpane.
		dgp.add(lblStart, 0, 0);
		dgp.add(dpStartDate, 1, 0);
		
		dgp.add(lblEnd, 0, 1);
		dgp.add(dpEndDate, 1, 1);
		
		dgp.add(btnCancel, 2, 2);
		dgp.add(btnOK, 3, 2);
		
		
		dpEndDate.getValue();
		
		
		//Create a scene.
		Scene dialogScene = new Scene(dgp);
		
		//Set the scene.
		dialog.setScene(dialogScene);
				
		//Show the stage.
		dialog.show();
				
	}//showBookingDialog()

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage pStage) throws Exception {
		// TODO Auto-generated method stub
		//Set the title.
		pStage.setTitle("Interest Calculator");
		
		//Width and height.
		pStage.setWidth(600);
		pStage.setHeight(400);
		
		//Instantiate components.
		lblCapital = new Label("Capital:");
		lblInterest = new Label("Interest Rate (%):");
		lblTerm = new Label("Investment Term (years):");
		
		//checkboxes
		CheckBox Compound = new CheckBox("Compound Interest");			 
		
		CheckBox simple = new CheckBox("Simple Interest");
		
		
		
		//Size the buttons.
		btnQuit.setMinWidth(65);
		btnCalculate.setMinWidth(60);
		btnDot.setMinWidth(40);
		
		//Quit button clears all data
		btnQuit.setOnAction(ae -> {
			txtCapital.clear();
			txtInterestRate.clear();
			txtTermYears.clear();
			txtfTyping.clear();
		});
		//calculation for compound and simple interest
		btnCalculate.setOnAction(ae -> {
			
			//gets strings from text fields
			String str1 = txtCapital.getText();
			String str2 = txtInterestRate.getText();
			String str3 = txtTermYears.getText();
			//converts strings to doubles
			double c = Double.parseDouble(str1);
			double i = Double.parseDouble(str2);
			double y = Double.parseDouble(str3);
			
			//interest and amount calculations for simple interest
			double interest= Double.parseDouble(str2)*Double.parseDouble(str3);
			double amount= Double.parseDouble(str2)*Double.parseDouble(str3)+Double.parseDouble(str1);
			
			//simple interest
			if(simple.isSelected()){
	
         txtfTyping.setText("Simple Interest:" + System.lineSeparator()
       +"year: " + y + "      " + "Initial Capital:" + c + "    " +
        "Interest earned: "+ interest + "    "+
        "Final amount: " + " "+ amount);
			}
			//compound interest
			 if(Compound.isSelected()){
				 for(int x = 1; x <= y; x++) {
			         double compAmount = c * Math.pow((1+ i/100), x);
			         double compInter= compAmount - c;
		         txtfTyping.setText("Compound Interest:" + System.lineSeparator()
		          +"year: " + y + "      " + "Initial Capital:" + c + "    " +
		        "Interest earned: "+ compInter + "    "+
		        "Final amount: " + " "+ compAmount);
		        				}
				 // simple and compound interest
				 if(simple.isSelected() && Compound.isSelected() ){
					for(int x = 1; x <= y; x++) {
					double compAmount = c * Math.pow((1+ i/100), x);
				    double compInter= compAmount - c;
					         
					txtfTyping.setText("Simple Interest:" + System.lineSeparator()
					 +"year: " + y + "      " + "Initial Capital:" + c + "    " +
					 "Interest earned: "+ interest + "    "+
					"Final amount: " + " "+ amount + System.lineSeparator()+"Compound Interest:" + System.lineSeparator()
					 +"year: " + y + "      " + "Initial Capital:" + c + "    " +
				    "Interest earned: "+ compInter + "    "+    "Final amount: " + " "+ compAmount);}
			 }
		
		
			 }
			
		});
	
		//Handle lower textArea events.
		txtfTyping.setOnKeyReleased(aa -> {
			
		});
						
		//Create a layout.
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnQuit, btnCalculate);
		
		GridPane gp = new GridPane();
		//gp.setGridLinesVisible(true);

		//Set the gp padding.
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		
	
		//textfields
		txtCapital = new TextField();
		txtInterestRate = new TextField();
		txtTermYears = new TextField();
		
	
		//Width of text
		
		txtInterestRate.setPrefWidth(250);
		txtTermYears.setPrefWidth(250);
		//gp.add(lblCapital and txtCapital);
		gp.add(lblCapital, 1, 0);
		gp.add(txtCapital, 2, 0, 2, 1);
		//gp.add(lblShowTime, 3, 1);
		
		//gp.add(
		gp.add(lblInterest,1,1);
		gp.add(txtInterestRate,2, 1, 2, 1 );
		
		//gp.add
		gp.add(lblTerm,1,2 );
		gp.add(txtTermYears,2,2);
		gp.add(btnDot,3,2,2, 1);
		//gp.add
		txtfTyping.setPrefHeight(200);
		txtfTyping.setPrefWidth(800);
		gp.add(txtfTyping,1, 3, 3, 1);
		
		
		//Add the hb containing the buttons.
		gp.add(hbButtons, 3, 4);
		
		//add checkboxes
		gp.add(simple, 1, 4);
		gp.add(Compound, 2, 4);
		//Create a scene.
		Scene s = new Scene(gp);
	
						
		//Set the scene.
		pStage.setScene(s);
				
		//Show the stage.
		pStage.show(); 
		}
	
	
	
public void stop(){
		
	}//stop
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();

	}//main

}//class