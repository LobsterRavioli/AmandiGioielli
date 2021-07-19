package beans;

public class UserBean
{
	private int id;
	private String phone;
	private String email;
	private boolean newsletter;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;

	
    public UserBean()
    {
    	id = -1;
    	firstName = "";
    	lastName = "";
    	email = "";
    	password = "";
    	newsletter = false;
    	phone = "";
    	active = true;
    }
	
    public String getFirstName() 
    {
       return firstName;
	}

    public void setFirstName(String newFirstName) 
    {
       firstName = newFirstName;
	}

    public String getLastName() 
    {
       return lastName;
	}

    public void setLastName(String newLastName) 
    {
       lastName = newLastName;
    }
			
    public String getPassword() 
    {
       return password;
	}

    public void setPassword(String newPassword) 
    {
       password = newPassword;
	}
		
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public boolean getNewsletter() 
	{
		return newsletter;
	}

	public void setNewsletter(boolean newsletter)
	{
		this.newsletter = newsletter;
	}

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean isActive) 
	{
		this.active = isActive;
	}	
}

