package handicapApp;

public class Score {
	
	public int shots;
	public int roundNum;
	public int courseID;
	
	public Score() {}

	public Score(int shots, int roundNum, int courseID) {
		this.shots = shots;
		this.roundNum = roundNum;
		this.courseID = courseID;
	}
	
	public int getShots() { return shots; }
	public void setShots(int shots) {this.shots = shots; }
	
	public int getRoundNum() { return roundNum; }
	public void setRoundNum(int roundNum) {this.roundNum = roundNum; }
	
	public int getCourseID() { return courseID; }
	public void setCourseID(int courseID) {this.courseID = courseID; }

}
