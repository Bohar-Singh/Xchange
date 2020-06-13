package boca.flight;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;

import Utilities.BaseLib;

public class CheckSearchFlightBOCA extends BaseLib {
	
	@BeforeSuite
	public void setUpFiles() throws IOException
	{
		loadFiles();
	}
	
	

}
