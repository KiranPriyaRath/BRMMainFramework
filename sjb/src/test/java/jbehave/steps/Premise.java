package jbehave.steps;
import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Steps;
import pages.OrdersPage;
import pages.PremisePage;

public class Premise {
	@Steps
	private PremisePage Premisepage;
	
	
	@When("Submit the FL Order '$rowname'")
	    public void FLModifyOrderFormEntry(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException, SQLException {
		Premisepage.FLModifyOrderFormEntry(Action);
	}
	@When("Create New Premise '$rowname'")
    public void  CreateNewPremise(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException, SQLException, AWTException {
	Premisepage.CreateNewPremise(Action);
	}
	@When("Create New Premise ALL Premise Tab '$rowname'")
    public void  CreateNewPremiseviaALLPremiseTab(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException, SQLException, AWTException {
	Premisepage.CreateNewPremiseviaALLPremiseTab(Action);
	}
	
	@When("Book Appointment '$rowname'")
    public void  BookAppointment(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException, SQLException, AWTException {
	Premisepage.BookAppointment(Action);
	}

	@When("Filter Promotion '$rowname'")
    public void  FilterPromotion(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException, SQLException, AWTException {
	Premisepage.FilterPromotion(Action);
	Premisepage.BookAppointment(Action);
	}
	
	@When("Filter Promotion No Book Appointment'$rowname'")
    public void  FilterPromotionNoBook(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException, SQLException {
	Premisepage.FilterPromotion(Action);
	}
}
