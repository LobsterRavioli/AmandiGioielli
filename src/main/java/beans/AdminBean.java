package beans;

public class AdminBean {
    private int id;
    private String email;
    private String password;

    public int getAdminId() {
	return id;
    }

    public void setId(int adminId) {
	this.id = adminId;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
