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
    ArrayList<Integer> _preReqs;
    
    public Unit(){
        _id = 0;
        _name = "";
        _preReqs = new ArrayList<>();
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
         
        //computes if a key already exists otherwise inserts a new one and adds values
        this._preReqs.add(value);
    }
    
    public int getId(){
        return this._id;
    } 
    public String getName(){
        return this._name;
    }
    public ArrayList<Integer> getPreReqs(){
        return this._preReqs;
    }
}
