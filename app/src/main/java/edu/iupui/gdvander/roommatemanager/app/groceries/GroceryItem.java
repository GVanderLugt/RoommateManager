package edu.iupui.gdvander.roommatemanager.app.groceries;

/**
 * Created by Gerrit on 9/6/2014.
 * Title: GroceryItem.java
 * Purpose: Object to store grocery item data to be used in the GroceriesFragment's listView
 */
public class GroceryItem {
    protected int itemID;
    protected String name;
    protected int status = 0;
    protected int userID;

    public GroceryItem(int itemID, String name, int status, int userID){
        this.itemID = itemID;
        this.name = name;
        this.status = status;
        this.userID = userID;
    }

    public int getItemID(){
        return itemID;
    }

    public String getName(){
        return name;
    }

    public int getStatus(){
        return status;
    }

    public int getUserID(){
        return userID;
    }

    public void changeStatus(){
        //Change the status of the item between 1 and 0
        if(status == 0){
            status = 1;
        }
        else if(status == 1){
            status = 0;
        }
        else{
            status = 0;
        }
    }
}
