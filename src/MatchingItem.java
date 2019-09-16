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
    private boolean exclusion;

    /*
     * Methodes
     */
    public MatchingItem()
    {
        name = "----";
        exception = null;
        exclusion = false;
    }

    public MatchingItem(String name)
    {
        this.name = name;
        exception = null;
        exclusion = false;
    }

    //Get/Set Methodes
    public void setName(String name){this.name=name;}
    public void setException(MatchingItem item){exception = item; exclusion=true;}

    public String getName(){return name;}
    public MatchingItem getException(){return exception;}
    public boolean getExclusion(){return exclusion;}

    //More Methodes
    public void deleteException()
    {
        exception = new MatchingItem();
        exclusion = false;
    }
}
