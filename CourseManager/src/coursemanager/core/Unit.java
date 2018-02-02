package coursemanager.core;

import java.util.HashMap;

/**
 *
 * @author Evil Genious
 */
public class Unit {
    private int _id;
    private String _name;
    HashMap<Integer,Integer> _preReqs;
    
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
    
     public void setPreReqs(HashMap<Integer, Integer> preReqs){
        this._preReqs = new HashMap <Integer, Integer> (preReqs);
    }
    public int getId(){
        return this._id;
    } 
    public String getName(){
        return this._name;
    }
    public HashMap<Integer,Integer> getPreReqs(){
        return this._preReqs;
    }
}
