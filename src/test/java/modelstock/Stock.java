package modelstock;

public class Stock {
	private String code;
	private String type;
	private String floor;
	private String isin;
	private String status;
	private String companyName;
	private String companyNameEng;
	private String shortName;
	private String listedDate;
	private String companyId;
	private String delistedDate;
	public String getCode() {
		return code;
	}
	public String getType() {
		return type;
	}
	public String getFloor() {
		return floor;
	}
	public String getIsin() {
		return isin;
	}
	public String getStatus() {
		return status;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCompanyNameEng() {
		return companyNameEng;
	}
	public String getShortName() {
		return shortName;
	}
	public String getListedDate() {
		return listedDate;
	}
	public String getCompanyId() {
		return companyId;
	}
	public String getDelistedDate() {
		return delistedDate;
	}
	public Stock(String code, String type, String floor, String isin, String status, String companyName,
			String companyNameEng, String shortName, String listedDate, String companyId, String delistedDate) {
		super();
		this.code = code;
		this.type = type;
		this.floor = floor;
		this.isin = isin;
		this.status = status;
		this.companyName = companyName;
		this.companyNameEng = companyNameEng;
		this.shortName = shortName;
		this.listedDate = listedDate;
		this.companyId = companyId;
		this.delistedDate = delistedDate;
	}
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void showInfo() {
		System.out.println("Company Id           : " + companyId);
		System.out.println("Company Name         : " + companyName);
		System.out.println("Company English Name : " + companyNameEng);
		System.out.println("Company Short Name   : " + shortName);
		System.out.println("Company Code         : " + code);
		System.out.println("Company Type         : " + type);
		System.out.println("Company Floor        : " + floor);
		System.out.println("Company Isin         : " + isin);
		System.out.println("Company ListedDate   : " + listedDate);
		System.out.println("Company DelistedDate : " + delistedDate);
		System.out.println("----------------------------------------");
	}
	
}
