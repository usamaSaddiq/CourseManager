
package coursemanager;

import coursemanager.core.Course;

/**
 *
 * @author Usama Saddiq
 */
public class CourseManager {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        Course computerScience = new Course();
        
        computerScience.getAllUnits();
        
        computerScience.generateCourseStructure();
    }   
}
