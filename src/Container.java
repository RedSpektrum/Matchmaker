import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nacho
 */
public class Container {
     /*
     * Attributes
     */
    private MatchingItem[] items;
    private int index;

    /*
     * Methodes
     */
    public Container()
    {
        items = new MatchingItem[16];
        index = 0;

        // We fill the array with empty items to avoid fails
        for(int i=0; i<16; i++)
        {
            items[i] = new MatchingItem();
        }
    }

    public void addItem(MatchingItem item)
    {
        items[index]=item;
        if(index<16)
        {
            index++;
        }
    }

    public MatchingItem[][] matchItems()
    {
        MatchingItem[] aux = items.clone();
        MatchingItem[][] pairs = new MatchingItem[8][2];
        MatchingItem selected = new MatchingItem();

        int number;
        int selectedItems = 0;
        int auxIndex = index;
        int pairIndex = 0;
        int i,j; // Loop variables
        boolean same, excluded; // Ensures that the pair arent the same nor exclusion
        boolean found = false; // Ensures that the pair of each one is been found
        
        // ------------------------------------------------------------- //
        // -------------------------- AUTOFILL ------------------------- //        
        for(i=0; i<8; i++)
        {
            for(j=0; j<2; j++)
            {
                pairs[i][j]= new MatchingItem();
            }
        }        
            
        // ------------------------------------------------------------- //
        // ------------------------- MAIN LOOP ------------------------- // 
        while(selectedItems<index)
        {
                // ------------------------------------------------------------- //
                // ---------------------- FIRST ELEMENT ------------------------ //
                // We select for first the first element of the vector
                if(auxIndex>0){
                pairs[pairIndex][0] = aux[0];
                selectedItems++;            
                // In case that there weren't any item there its changed by an empty one
                aux[0] = new MatchingItem();
            
                // and then we delete it
                for(i=1; i<auxIndex; i++)
                {
                    aux[i-1]=aux[i]; // We shift the elements to replace them as a queue
                }
                auxIndex--;
            
                // If there is any element left we look for it
                if(auxIndex>0)
                {
                    // ------------------------------------------------------------- //
                    // ----------------------- NEXT ELEMENT ------------------------ //
                    found=false;
                    while(!found)
                    {
                        number=99;
                        while(number>auxIndex-1)
                        {
                        number = (int)(Math.random()*(auxIndex));
                        }
                        MatchingItem item = aux[number];
                        same = (item == pairs[pairIndex][0]);
                        // They arent the same we look for their exclusions
                        if(!same)
                        {
                            // If they have exclusion we care about they not to be their respect one
                            if(item.getExclusion() ||  pairs[pairIndex][0].getExclusion())
                            {
                                excluded = (item.getException()== pairs[pairIndex][0] ||  pairs[pairIndex][0].getException()==item );
                                // If they aren't the excluded ones they are selected
                                if(!excluded)
                                {
                                    pairs[pairIndex][1]=item;
                                    found = true;
                                }
                            }
                            // If they haven't any exclusion its automatically selected
                            else
                            {
                                pairs[pairIndex][1]=item;
                                found=true;
                            }                        
                            // If we found the item we reduce the queue elements
                            if(found)
                            {
                                pairIndex++;
                                selectedItems++;
                                aux[number] = new MatchingItem();
                                // Finally we delete the item
                                for(i=1; i<auxIndex; i++)
                                {
                                    aux[i-1]=aux[i]; // We shift the elements to replace them as a queue
                                }
                                auxIndex--;                    
                            }
                        }
                    }
                }
                else
                {
                    pairs[pairIndex][1]=new MatchingItem();
                    selectedItems++;
                    pairIndex++;
                }
            }
        }
       

        return pairs;
    }

    public void removeItem(int i)
    {
        if(index>0){
            i+=1;
            // W move the rest of them and delete de last one
            for(int j=i;j<16;j++)
            {           
                items[j-1]=items[j];
                items[j]=new MatchingItem();
            }
            //Finally we reduce the index and leave the loop
            index--;
        }
    }
    
    public void refresh(JComboBox box)
    {
        box.removeAllItems();
        for(int i=0; i<16; i++)
        {
           box.addItem(items[i].getName());          
        }
        box.setSelectedIndex(15);
    }
    
    public void refresh(JList list)
    {
        DefaultListModel model = new DefaultListModel<String>();
        for(int i=0; i<16; i++)
        {
           model.addElement(items[i].getName());          
        }
        list.removeAll();
        list.setModel(model);
    }
    
    public void unSelectAll()
    {
        for(int i=0; i<16; i++)
        {
           items[i].unselect();
        }
    }
    
    // Get/set methodes
    public int getIndex(){return index;}
    public MatchingItem getItem(int i){return items[i];}
    public void setItem(int i, MatchingItem item){items[i]= item;}
    public MatchingItem[] getItems(){return items;}
    
}
