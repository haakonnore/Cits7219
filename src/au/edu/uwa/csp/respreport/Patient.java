package au.edu.uwa.csp.respreport;

public class Patient {
	  private long id;
	  private String title;
	  private String firstName;
	  private String lastName;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getFirstName() {
	    return firstName;
	  }

	  public void setFirstName(String comment) {
	    this.firstName = comment;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return firstName + " " + lastName;
	  }

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	} 