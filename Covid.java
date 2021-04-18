package jdbcdemo;

public class Covid {

	private int OBJECTID, ORIGID,  ConfirmedCovidCases, PopulationProportionCovidCases;
	private String TimeStamp;
	
	public Covid(int OBJECTID, int ORIGID, String TimeStamp, int ConfirmedCovidCases, int PopulationProportionCovidCases ) {
		
		this.OBJECTID = OBJECTID;
		this.ORIGID = ORIGID;
		this.TimeStamp = TimeStamp;
		this.ConfirmedCovidCases = ConfirmedCovidCases;
		this.PopulationProportionCovidCases = PopulationProportionCovidCases;
	}
	
	public int getOBJECTID() {
		return OBJECTID;
	}
	
	public int getORIGID() {
		return ORIGID;
	}
	
	public String getTimeStamp() {
		return TimeStamp;
	}
	
	public int getConfirmedCovidCases() {
		return ConfirmedCovidCases;
	}
	
	public int getPopulationProportionCovidCases() {
		return PopulationProportionCovidCases;
	}
	
}
