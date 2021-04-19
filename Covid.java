/*
This class uses getter methods.
They retrieve data from the variable
*/

package jdbcdemo;

public class Covid {

	private int OBJECTID,  ConfirmedCovidCases, PopulationProportionCovidCases;
	private String TimeStamp, CountyName;
	
	//Constructors
	public Covid(int OBJECTID, String CountyName, String TimeStamp, int ConfirmedCovidCases, int PopulationProportionCovidCases ) {
		
		this.OBJECTID = OBJECTID;
		this.CountyName = CountyName;
		this.TimeStamp = TimeStamp;
		this.ConfirmedCovidCases = ConfirmedCovidCases;
		this.PopulationProportionCovidCases = PopulationProportionCovidCases;
	}
	
	//OBJECTID
	public int getOBJECTID() {
		return OBJECTID;
	}
	
	//CountyName
	public String getCountyName() {
		return CountyName;
	}
	
	//TimeStamp
	public String getTimeStamp() {
		return TimeStamp;
	}
	
	//ConfirmedCovidCases
	public int getConfirmedCovidCases() {
		return ConfirmedCovidCases;
	}
	
	//PopulationProportionCovidCases
	public int getPopulationProportionCovidCases() {
		return PopulationProportionCovidCases;
	}
	
}
