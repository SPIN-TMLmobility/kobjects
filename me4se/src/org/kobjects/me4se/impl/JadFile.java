/*
 * JAD_Parser.java
 *
 * Created on 6. August 2001, 09:55
 */

package org.kobjects.me4se.impl;

import java.util.*;
import java.io.*;
import javax.microedition.io.*;

/**
 *
 * @author  svastag
 * @version 
 */

public class JadFile  {

    private Hashtable properties = new Hashtable();
    

    /** Creates new JadFile */

    public JadFile (String name) throws IOException {

	BufferedReader reader = new BufferedReader 
	    ((name.indexOf (':') == -1)
	     ? new FileReader (name)
	     : new InputStreamReader (Connector.openInputStream (name)));
	
	while (true) {
	    String line = reader.readLine ();
	    if (line == null) break;
	    int i = line.indexOf (':');
	    
	    if (i != -1)
		properties.put (line.substring (0, i).trim (),
				line.substring (i+1).trim ());
	}

	reader.close ();
    }
    
    
    public String getProperty (String key) {
	
	return (String) properties.get (key);
    }

    
    /**
     *@return an Array of Arrays (String) containing [0] Name, [1] Icon, [2] Class
     */
    public Vector getMIDletList() {
        //Keeps the Arrays below
        Vector midletlist = new Vector();
        // [0] Name, [1] Icon, [2] Class

	for (int i = 1; true; i++) {
            String[] midlets = new String[3];
            String temp = getProperty ("MIDlet-"+i);
	    if (temp == null) break;
	    StringTokenizer tokenizer = new StringTokenizer(temp,",");
	    midlets[0] = tokenizer.nextToken().trim();
	    midlets[1] = tokenizer.nextToken().trim();
	    midlets[2] = tokenizer.nextToken().trim();
	    midletlist.addElement(midlets);
	}

        /* For testing only
        for (int i=0; i<midletlist.size(); i++) {
            for (int j=0; j<3; j++) {
                String array[] = (String[])midletlist.elementAt(i);
                System.out.println(array[j]);
            }
        }
         */
        return midletlist;
    }

    /*
    //For testing only...
    public static void main(String args[]) {
        JAD_Parser jadparser = new JAD_Parser(args[0]);
	}*/
}
