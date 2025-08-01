package handicapApp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static final File scoresFile = new File("Scores.json");
	private static final File coursesFile = new File("Courses.json");

	public static double[] arraySort (double[] jumbled) {
        boolean swap = true;
        
        while(swap == true) {
            swap = false;
            
            for(int i = 0; i < (jumbled.length -1); i++) {
                
                if (jumbled[i] > jumbled[i + 1]) {
                    swap = true;
                    double t = jumbled[i];
                    jumbled[i] = jumbled[i + 1];
                    jumbled[i + 1] = t;
                
                }
            }
        }
        return jumbled;
    }
	
	public static double avCalc (int k, double[] j) {
        
		double preAv = 0;
        
		for(int i = 0; i < k; i++) {
            preAv += j[i];
		}
        
        return preAv / k;
    }
	
	public static double difCalc (int i, double r, double s, int h, int x) {
		return (x - r ) * 113 / s;
	}
	
	public static double[] oneTwoAdder (List<Double> list) {
		int l = list.size() / 2 ;
		double[] f = new double[l];
		for (int i = 0; i < list.size() - 1; i += 2) {
		    double sum = list.get(i) + list.get(i + 1);
		    f[i / 2] = sum;
		}
		return f;
	}
	
	public static double[] arrayJoin(double[] one, List<Double> two) {
		double combined[] = new double[one.length + two.size()];
		
		for (int i = 0; i < one.length; i++) {
		    combined[i] = one[i];
		}
		
		for (int i = 0; i < two.size(); i++) {
		    combined[one.length + i] = two.get(i);
		}
		
		return combined;
	}
	
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        List<Score> scores = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        System.out.println("Press 1 to enter a new score\nPress 2 to enter a new course\nPress 3 to check course info\nPress 4 to edit a course\nPress 5 to check current handicap\nPress 6 to view scores\nPress 7 to delete score");
        int selection = input.nextInt();
        input.nextLine();

        switch (selection) {
            case 1:
                System.out.println("Enter score (shots):");
                int shots = input.nextInt();
                input.nextLine();

                System.out.println("Enter course ID or press n for new course: ");
                
                try {
                    if (coursesFile.exists() && coursesFile.length() != 0) {
                        courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                        for (Course item : courses) {
                            System.out.println(item.courseID + ": " + item.name + " " + item.tees);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading courses file: " + e.getMessage());
                }
                
                String courseInput = input.nextLine();
                
                
                if(courseInput.equalsIgnoreCase("n")) {
                	try {
                	    if (scoresFile.exists() && scoresFile.length() != 0) {
                	        scores = mapper.readValue(scoresFile, new TypeReference<List<Score>>() {});
                	    } else {
                	        scores = new ArrayList<>();
                	    }
                	} catch (IOException e) {
                	    System.out.println("Error reading scores file: " + e.getMessage());
                	    scores = new ArrayList<>();
                	}
                	
                	System.out.println("Enter course name:");
                	String name = input.nextLine();
                	
                	System.out.println("Enter tees played:");
                	String tees = input.nextLine();
                	
                	System.out.println("Enter course par:");
                	int par = input.nextInt();
                	
                	System.out.println("Press f for 18 holes or h for 9 holes");
                	String holeInput = input.nextLine();
                	input.nextLine();
                	int holes;
                	if(holeInput.equalsIgnoreCase("f")) {
                		holes = 18;
                	}
                	else if(holeInput.equalsIgnoreCase("h")) {
                		holes = 9;
                	}
                	else {
                		holes = 0;
                		System.out.println("Invalid selection. Defaulted holes to zero. Please fix in course editor!");
                	}
                	
                	System.out.println("Enter course rating:");
                	double rating = input.nextDouble();
                	
                	System.out.println("Enter course slope:");
                	double slope = input.nextDouble();
                	input.nextLine();
                	
                	int courseID = courses.size() + 1;
                	int roundNum = scores.size() + 1;
                	
                	Course newCourse = new Course(name, tees, par, holes, rating, slope, courseID); 
                	courses.add(newCourse);
                	
                	Score newScore = new Score(shots, roundNum, courseID);
                    scores.add(newScore);
                    
                	try {
                	    mapper.writeValue(coursesFile, courses);
                	    System.out.println("Course saved to Courses.json");
                	} catch (IOException e) {
                	    System.out.println("Error writing to courses file: " + e.getMessage());
                	}
                	
                	try {
                	    mapper.writeValue(scoresFile, scores);
                	    System.out.println("Score saved to Scores.json");
                	} catch (IOException e) {
                	    System.out.println("Error writing to scores file: " + e.getMessage());
                	}

                }
                else {
                	try {
                	    if (scoresFile.exists() && scoresFile.length() != 0) {
                	        scores = mapper.readValue(scoresFile, new TypeReference<List<Score>>() {});
                	    } else {
                	        scores = new ArrayList<>();
                	    }
                	} catch (IOException e) {
                	    System.out.println("Error reading scores file: " + e.getMessage());
                	    scores = new ArrayList<>();
                	}
                	
                	int courseID = Integer.parseInt(courseInput);
                	int roundNum = scores.size() + 1;

                    Score newScore = new Score(shots, roundNum, courseID);
                    scores.add(newScore);
                    
                    try {
                	    mapper.writeValue(scoresFile, scores);
                	    scores.add(newScore);
                	    System.out.println("Score saved to Scores.json");
                	} catch (IOException e) {
                	    System.out.println("Error writing to scores file: " + e.getMessage());
                	}
                }
                break;
            case 2:
            	System.out.println("Enter course name:");
            	String name = input.nextLine();
            	
            	System.out.println("Enter tees played:");
            	String tees = input.nextLine();
            	
            	System.out.println("Enter couse par:");
            	int par = input.nextInt();
            	
            	System.out.println("Press f for 18 holes or h for 9 holes");
            	String holeInput = input.nextLine();
            	input.nextLine();
            	int holes;
            	if(holeInput.equalsIgnoreCase("f")) {
            		holes = 18;
            	}
            	else if(holeInput.equalsIgnoreCase("h")) {
            		holes = 9;
            	}
            	else {
            		holes = 0;
            		System.out.println("Invalid selection. Defaulted holes to zero. Please fix in course editor!");
            	}
            	
            	System.out.println("Enter course rating:");
            	double rating = input.nextDouble();
            	
            	System.out.println("Enter course slope:");
            	double slope = input.nextDouble();
            	input.nextLine();
            	
            	try {
                    if (coursesFile.exists() && coursesFile.length() != 0) {
                        courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                    }
                } catch (IOException e) {
                    System.out.println("Error reading courses file: " + e.getMessage());
                }
            	
            	int courseID = courses.size() + 1;
            	
            	Course newCourse = new Course(name, tees, par, holes, rating, slope, courseID); 
            	courses.add(newCourse);
            	
            	try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(coursesFile, courses);
                    System.out.println("Item saved to Courses.json");
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
                break;
            case 3:
            	System.out.println("Enter course ID of course to view:");
                
                try {
                    courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                    for (Course item : courses) {
                        System.out.println(item.courseID + ": " + item.name + " " + item.tees);
                    }
                } catch (Exception e) {
                    System.out.println("Error loading courses: " + e.getMessage());
                }
                
                int courseInput2 = input.nextInt();
                
                try {
                    courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                    for (int i = 0; i < courses.size(); i++) {
                    	Course c = courses.get(i);
                        if(c.courseID == courseInput2) {
                            System.out.println(c.name + " " + c.tees + ": Par " + c.par + ", Rating " +c.rating + ", Slope " +c.slope);
                        	break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error loading courses: " + e.getMessage());
                }
                
                break;
                case 4:
                System.out.println("Enter course ID of course to edit:");
                
                try {
                    courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                    for (Course item : courses) {
                        System.out.println(item.courseID + ": " + item.name + " " + item.tees);
                    }
                } catch (Exception e) {
                    System.out.println("Error loading courses: " + e.getMessage());
                }
                
                int courseInput3 = input.nextInt();
                Course courseToEdit = null;
                
                for (int i = 0; i < courses.size(); i++) {
                	Course c = courses.get(i);
                    if(c.courseID == courseInput3) {
                    	courseToEdit = c;
                    	break;
                    }
                }
                
                input.nextLine();
                System.out.println("Press n to edit name:\nPress p to edit par:\nPress h to edit holes:\nPress r to edit rating:\nPress s to edit slope:");
                String editInput = input.nextLine();
                
                switch (editInput) {
                	case "n":
                		System.out.println("Enter new name:");
                		courseToEdit.name = input.nextLine();
                		break;
                	case "p":
                		System.out.println("Enter new par:");
                		courseToEdit.par = input.nextInt();
                		break;
                	case "h":
                		System.out.println("Press f for 18 holes or h for 9 holes");
                    	String holeInput2 = input.nextLine();
                    	if(holeInput2.equalsIgnoreCase("f")) {
                    		courseToEdit.holes = 18;
                    	}
                    	else if(holeInput2.equalsIgnoreCase("h")) {
                    		courseToEdit.holes = 9;
                    	}
                    	else {
                    		courseToEdit.holes = 0;
                    		System.out.println("Invalid selection. Defaulted holes to zero. Please fix in course editor!");
                    	}
                		break;
                	case "r":
                		System.out.println("Enter new rating:");
                		courseToEdit.rating = input.nextDouble();
                		break;
                	case "s":
                		System.out.println("Enter new slope:");
                		courseToEdit.slope = input.nextDouble();
                		break;
                	default:
                		System.out.println("Invalid choice");
                		break;
                }
                
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(coursesFile, courses);
                    System.out.println("Course updated and saved.");
                } catch (IOException e) {
                    System.out.println("Error saving updated course: " + e.getMessage());
                }
                
                break;
            case 5:
            	try {
                    if (scoresFile.exists() && scoresFile.length() != 0) {
                        scores = mapper.readValue(scoresFile, new TypeReference<List<Score>>() {});
                    }
                } catch (IOException e) {
                    System.out.println("Error reading courses file: " + e.getMessage());
                }
            	
            	try {
                    if (coursesFile.exists() && coursesFile.length() != 0) {
                        courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                    }
                } catch (IOException e) {
                    System.out.println("Error reading courses file: " + e.getMessage());
                }
            	
            	int index18 = 0;
            	int index9 = 0;
            	List<Double> rawDifferentials18 = new ArrayList<>();
            	List<Double> rawDifferentials9 = new ArrayList<>();
            	for (int i = 0; i < 20 ; i++) {
            		Course course = null;
            		for (Course c : courses) {
            		    if (c.courseID == scores.get(i).courseID) {
            		        course = c;
            		        break;
            		    }
            		}

            		if (course == null) {
            		    System.out.println("Course with ID " + scores.get(i).courseID + " not found.");
            		    continue; 
            		}
            		
            		double r = course.rating;
            		double s = course.slope;
            		int h = course.holes;
            		
            		if(h == 18) {
            			rawDifferentials18.add(difCalc(i, r, s, h, scores.get(i).shots));
            		}
            		else if (h == 9 ){
            			rawDifferentials9.add(difCalc(i, r, s, h, scores.get(i).shots));
            		}
            		else {
            			System.out.println("Hole count not detected");
            			System.out.println(course.courseID);
            		}
            	}
            	
            	double[] combinedDifferentials9 = oneTwoAdder(rawDifferentials9);
            	
            	double[] rawDifferentials = arrayJoin(combinedDifferentials9, rawDifferentials18);
            	
            	double sortedDifferentials[] = arraySort(rawDifferentials);
            	
            	int take = Math.min(sortedDifferentials.length, 8);
            	
            	double[] top8Differentials = new double[take];

            	for (int i = 0; i < take; i++) {
            	    top8Differentials[i] = sortedDifferentials[i];
            	}
            	
            	double av8 = avCalc(take, top8Differentials);
            	double handicap = av8 * .96;
            	
            	System.out.println(handicap);
                
                break;
            case 6:
            	try {
                    scores = mapper.readValue(scoresFile, new TypeReference<List<Score>>() {});
                    for (int i = 0; i < scores.size(); i++) {
                    	courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                    	
                    	Score s = scores.get(i);
                    	Course c = null;
                    	for (Course course : courses) {
                    	    if (course.courseID == s.courseID) {
                    	        c = course;
                    	        break;
                    	    }
                    	}
                    	if (c != null) {
                    	    System.out.println(s.shots + " at " + c.name);
                    	} else {
                    	    System.out.println(s.shots + " at Unknown Course (ID: " + s.courseID + ")");
                    	}
                    }
                } catch (Exception e) {
                    System.out.println("Error loading courses: " + e.getMessage());
                }
            	break;
            case 7:
            	try {
                    scores = mapper.readValue(scoresFile, new TypeReference<List<Score>>() {});
                    for (int i = 0; i < scores.size(); i++) {
                    	courses = mapper.readValue(coursesFile, new TypeReference<List<Course>>() {});
                    	
                    	Score s = scores.get(i);
                    	Course c = null;
                    	for (Course course : courses) {
                    	    if (course.courseID == s.courseID) {
                    	        c = course;
                    	        break;
                    	    }
                    	}
                    	if (c != null) {
                    	    System.out.println(s.roundNum + ": " + s.shots + " at " + c.name);
                    	} else {
                    	    System.out.println(s.shots + " at Unknown Course (ID: " + s.courseID + ")");
                    	}
                    }
                    System.out.println("Enter round number to delete:");
                	int roundDelete = input.nextInt();
                	scores.removeIf(score -> score.roundNum == roundDelete);
                	mapper.writeValue(scoresFile, scores);

                    System.out.println("Deleted score for round " + roundDelete);
                } 
            	catch (Exception e) {
                    System.out.println("Error loading courses: " + e.getMessage());
                }
            break;
            default:
                System.out.println("Invalid selection");
        }
    }
}
