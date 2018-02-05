
package coursemanager;

import coursemanager.core.Course;
import coursemanager.core.Unit;
import java.util.HashMap;
import java.util.Queue;

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
                              
        computerScience.generateCourseStructure();
        
        computerScience.printCourseStructure();
    }   
}
