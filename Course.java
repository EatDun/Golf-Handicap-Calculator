package handicapApp;

public class Course {

	public String name;
	public String tees;
	public int par;
	public int holes;
	public double rating;
	public double slope;
	public int courseID;
	
	public Course(String name, String tees, int par, int holes, double rating, double slope, int courseID) {
		this.name = name;
		this.tees = tees;
		this.par = par;
		this.holes = holes;
		this.rating = rating;
		this.slope = slope;
		this.courseID = courseID;
	}
	
	public void courseParAjdust(int par) {
		this.par = par;
	}

	public Course() {}
	
	public String getName() { return name; }
	public void setName(String name) {this.name = name;}
	
	public String getTees() { return tees; }
	public void setTees(String tees) {this.tees = tees;}
	
	public int getPar() { return par; }
	public void setPar(int par) {this.par = par;}
	
	public int getHoles() { return holes; }
	public void setHoles(int holes) {this.holes = holes;}

	public double getRating() { return rating; }
	public void setRating(double rating) {this.rating = rating;}
	
	public double getSlope() { return slope; }
	public void setSlope(double slope) {this.slope = slope;}
	
	public int getCourseID() { return courseID; }
	public void setCourseID(int courseID) {this.courseID = courseID;}
}
