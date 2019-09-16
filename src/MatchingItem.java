/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nacho
 */
public class MatchingItem {
     /*
     * Attributes
     */
    private String name;
    private MatchingItem exception;
    private boolean exclusion, selected;

    /*
     * Methodes
     */
    public MatchingItem()
    {
        name = "----";
        exception = null;
        exclusion = false;
        selected = false;
    }

    public MatchingItem(String name)
    {
        this.name = name;
        exception = null;
        exclusion = false;
        selected = false;
    }

    //Get/Set Methodes
    public void setName(String name){this.name=name;}
    public void setException(MatchingItem item){exception = item; exclusion=true;}
    public void select(){selected=true;}
    public void unselect(){selected=false;}

    public String getName(){return name;}
    public MatchingItem getException(){return exception;}
    public boolean getExclusion(){return exclusion;}
    public boolean isSelected(){return selected;}

    //More Methodes
    public void deleteException()
    {
        exception = new MatchingItem();
        exclusion = false;
    }
}
