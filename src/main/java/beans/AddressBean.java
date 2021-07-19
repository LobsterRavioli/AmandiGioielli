package beans;

public class AddressBean {
	
	public AddressBean() {
		this.addressNumber = -1;
		this.userId = -1;
		this.streetAddress = "";
		this.city = "";
		this.zip = "";
		this.province = "";
		this.phone = "";
		this.info = "";
	}
	public int getAddressNumber() 
	{
		return addressNumber;
	}
	public void setAddressNumber(int addressNumber) 
	{
		this.addressNumber = addressNumber;
	}
	public int getUserId() 
	{
		return userId;
	}
	public void setUserId(int userId) 
	{
		this.userId = userId;
	}
	public String getStreetAddress() 
	{
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) 
	{
		this.streetAddress = streetAddress;
	}
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	public String getZip()
	{
		return zip;
	}
	public void setZip(String zip) 
	{
		this.zip = zip;
	}
	public String getProvince() 
	{
		return province;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}
	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	public String getInfo() 
	{
		return info;
	}
	public void setInfo(String info) 
	{
		this.info = info;
	}
	
	@Override
	public String toString() 
	{
		
		return "[addressNumber = " + addressNumber + ", streetAddress=" 
									+ streetAddress + ", city=" + city + ", province=" 
									+ province + ", phone=" + phone +", info=" + info + "]" ;
	}
	
	
	private int addressNumber;
	private int userId;
	private String streetAddress;
	private String city;
	private String zip;
	private String province;
	private String phone;
	private String info;
	

}
