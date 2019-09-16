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
        System.out.println(index);
        items[index]=item;
        if(index<16)
        {
            index++;
        }
    }

    public void deleteItem(MatchingItem item)
    {
        // We look for the item
        for(int i=0; i<index; i++)
        {
            if(items[i]==item)
            {
                removeItem(i);
                break;
            }
        }
    }

    public MatchingItem[][] matchItems()
    {
        MatchingItem[] aux = items;
        MatchingItem[][] pairs = new MatchingItem[8][2];
        MatchingItem selected = new MatchingItem();

        int selectedItems = 0;
        int auxIndex = index;
        int pairIndex = 0;
        int i,j; // Loop variables

        boolean found = false; // Ensures that the pair of each one is been found

        // We fill the matrix to avoid fails
        for(i=0; i<8; i++)
        {
            for(j=0;j<2;j++)
            {
                pairs[i][j]=new MatchingItem();
            }
        }

        // Now we start selecting items until reaching the index
        while(selectedItems<index)
        {
            // We select one random item
            int number = (int)(Math.random()*auxIndex);
            pairs[pairIndex][0] = aux[number];
            selectedItems++;

            // We delete the selected item from the array
                if(auxIndex>0){
                    i+=1;
                    // We move the rest of them and delete de last one
                    for(j=i;j<16;j++)
                    {           
                        aux[j-1]=aux[j];
                        aux[j]=new MatchingItem();
                        }
                    //Finally we reduce the index and leave the loop
                    auxIndex--;
                }
            

            //Now we look for its pair
            found=false; // We reset the condition
            while(!found)
            {
                number = (int)(Math.random()*auxIndex);
                // If the element has any exclusion we ensure that we take other
                if(pairs[pairIndex][0].getExclusion() && pairs[pairIndex][0]!= aux[number])
                {
                    // We look at the attributes to find other one
                    if(aux[number]!=pairs[pairIndex][0].getException() && pairs[pairIndex][0].getException()!=aux[number])
                    {
                        // We automatically set the element
                        pairs[pairIndex][1]=aux[number];
                        pairIndex++;
                        found=true; 
                    }
                }
                else
                    {
                        // We automatically set the element
                        pairs[pairIndex][1]=aux[number];
                        pairIndex++;
                        found=true;                        
                    }
                // We delete the selected item from the array
                if(auxIndex>0){
                    i+=1;
                    // We move the rest of them and delete de last one
                    for(j=i;j<16;j++)
                    {           
                        aux[j-1]=aux[j];
                        aux[j]=new MatchingItem();
                        }
                    //Finally we reduce the index and leave the loop
                    auxIndex--;
                }
                
            }
            // We modify our index
            selectedItems++;
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
