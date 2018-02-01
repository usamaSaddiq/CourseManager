/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanager.CoreFunctionality;

import java.util.HashMap;

/**
 *
 * @author Evil Genious
 */
public class Course {
    public HashMap<Integer, Unit> _units;
    
    public Course(){
        _units = new HashMap<Integer ,Unit>();
    }
    public void loadUnits(){
        
    }
    public void loadPreReqs(){
        
    }
}
