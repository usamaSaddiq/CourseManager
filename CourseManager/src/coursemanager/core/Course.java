/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanager.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Evil Genious
 */
public class Course {
    public HashMap<Integer, Unit> _units;
    
    public Course(){
        _units = new HashMap<>();
        loadUnits();
    }
    public void loadUnits(){
        _units = new HashMap<>();
        
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        Unit currentUnit = new Unit();

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("resources/courses.csv");
            
            br  = new BufferedReader(new InputStreamReader(is));
            
            //read the first line of courses and ignore
            line = br.readLine();
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String []  courseData = line.split(csvSplitBy);
                
                int key = Integer.parseInt(courseData[0]);
                
                _units.put(key, new Unit());
                
                currentUnit = _units.get(key);
                
                currentUnit.setId(key);
                currentUnit.setName(courseData[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }  
    }
}
