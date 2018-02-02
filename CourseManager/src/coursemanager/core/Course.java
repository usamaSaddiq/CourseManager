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
    
    public Boolean exploreMap(Unit current){
    if(current.getPreReqs().isEmpty() || _completed.contains(current.getId())){
        if(!_completed.contains(current.getId())){
            _completed.add(current.getId());
        }
        return true;
    }

    if(current.getPreReqs().contains(_currentCall) || _incomplete.contains(_currentCall)){
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
    
    public void generateCourseStructure(){
        Iterator it = _units.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            this._currentCall = (int) pair.getKey();
            exploreMap((Unit)pair.getValue());
        }
    }
}
