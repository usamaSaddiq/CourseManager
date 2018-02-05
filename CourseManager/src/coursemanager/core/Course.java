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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Usama Saddiq
 */
public class Course {
    private HashMap<Integer, Unit> _units;
    private Queue<Integer> _completed;
    private Queue<Integer> _incomplete;
    private int _currentCall;
    private Boolean _flag;
    
    public Course(){
        _units = new HashMap<>();
        _completed = new LinkedList<>();
        _incomplete = new LinkedList<>();        
        _currentCall = 1;        
        _flag = true;
        
        loadUnits();
        loadPreRequisites();
    }
    public HashMap<Integer, Unit> getAllUnits(){
        return this._units;
    }
    
    public void loadUnits(){
        
        //define instance variables
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        Unit currentUnit = new Unit();

        try {
            
            //use the class loader and load the resources as a stream
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("resources/courses.csv");
            
            //create a buffer reader 
            br  = new BufferedReader(new InputStreamReader(is));
            
            //read the first line of courses and ignore
            line = br.readLine();
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String []  courseData = line.split(csvSplitBy);
                
                int key = Integer.parseInt(courseData[0]);
                
                _units.put(key, new Unit());
                
                //get the unit by key and store int he units hashmap
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
    
    public void loadPreRequisites(){
        
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("resources/prerequisites.csv");
            
            br  = new BufferedReader(new InputStreamReader(is));
            
            //read the first line of prerequisites and ignore
            line = br.readLine();
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String []  courseData = line.split(csvSplitBy);
                
                //get the units via ket and then insert the prerequisites
                int key = Integer.parseInt(courseData[0]);
                int value = Integer.parseInt(courseData[1]);
                _units.get(key).insertPreReq(key, value);
                
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
    
    
    //recursive function to explore the hashmap and resolve dependencies for units
    public Boolean exploreMap(Unit current){
    if(current.getPreReqs().isEmpty() || _completed.contains(current.getId())){
        if(!_completed.contains(current.getId())){
            _completed.add(current.getId());
        }
        return true;
    }

    if(current.getPreReqs().contains(_currentCall) || current.getPreReqs().contains(current.getId()) ||
       _incomplete.contains(_currentCall) || _incomplete.contains(current.getId())){
        if(!_incomplete.contains(_currentCall)){
            _incomplete.add(_currentCall);
        }
        return false;
    }

    for(Integer unitId : current.getPreReqs()){
      _flag = exploreMap(_units.get(unitId));
    }
    if(!_completed.contains(current.getId()) && !_incomplete.contains(current.getId())){
        if(_flag){
           _completed.add(current.getId());
        }
        return true;
    }
    if(!_incomplete.contains(current.getId())){
        _incomplete.add(_currentCall);
    }
    return false;
}
    
    //iterate over the units and generate a course structure
    public void generateCourseStructure(){
        Iterator it = _units.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            this._currentCall = (int) pair.getKey();
            exploreMap((Unit)pair.getValue());
        }
    }
    
    public Queue<Integer> getCompletedUnits(){
        return this._completed;
    }
    public Queue<Integer> getIncompleteUnits(){
        return this._incomplete;
    }
    
    //prin the course structure
    public void printCourseStructure(){
        Queue<Integer> completedUnits =  this.getCompletedUnits();
        Queue<Integer> incompleteUnits =  this.getIncompleteUnits();
        
        int subjectNumber = 0;
        Unit currentUnit = new Unit();
        System.out.println("***Welcome to the course manager***");
        System.out.println("Following is the list of units to be completed in order: ");
        while (!completedUnits.isEmpty()){
          subjectNumber = completedUnits.poll();
          currentUnit = _units.get(subjectNumber);
          
          System.out.println(currentUnit.getName());
        }
        
        System.out.println("Following is the list of units which cannot be completed: ");
        while (!incompleteUnits.isEmpty()){
          subjectNumber = incompleteUnits.poll();
          currentUnit = _units.get(subjectNumber);
          
          System.out.println(currentUnit.getName());
        }
    }
}
