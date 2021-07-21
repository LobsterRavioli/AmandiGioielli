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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (newsletter ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	public boolean equals(UserBean bean)
	{
		if (bean == null)
			return false;

		return (this.email.equals(bean.getEmail()) && this.password.equals(bean.getPassword()));
	}

}
