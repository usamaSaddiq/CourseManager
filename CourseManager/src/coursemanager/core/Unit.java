package coursemanager.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Usama Saddiq
 */
public class Unit {
    private int _id;
    private String _name;
    HashMap<Integer,List<Integer>> _preReqs;
    
    public Unit(){
        _id = 0;
        _name = "";
        _preReqs = new HashMap<>();
    }
    public void setId(int id){
        if(id > 0){
            this._id = id;
        }
        else {
            System.out.println("Id must be greater than zero");
        }
    }
    
    public void setName(String name){
        this._name = name;
    }
    
     public void insertPreReq(int key,int value){
         
        this._preReqs.computeIfAbsent(key, k->new ArrayList<>()).add(value);
    }
    
    public int getId(){
        return this._id;
    } 
    public String getName(){
        return this._name;
    }
    public HashMap<Integer,List<Integer>> getPreReqs(){
        return this._preReqs;
    }
}
