// ME4SE - A MicroEdition Emulation for J2SE 
//
// Copyright (C) 2001 Stefan Haustein, Oberhausen (Rhld.), Germany
//
// Contributors:
//
// STATUS: 
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License as
// published by the Free Software Foundation; either version 2 of the
// License, or (at your option) any later version. This program is
// distributed in the hope that it will be useful, but WITHOUT ANY
// WARRANTY; without even the implied warranty of MERCHANTABILITY or
// FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
// License for more details. You should have received a copy of the
// GNU General Public License along with this program; if not, write
// to the Free Software Foundation, Inc., 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

package javax.microedition.lcdui;

import java.util.*;

public class Form extends Screen {

    /*  class ItemPanel extends java.awt.Panel  {
	   
	ItemPanel () {
	    super (new java.awt.GridBagLayout ());
	}

	public java.awt.Dimension getMinimumSize () {
	    java.awt.Dimension d = super.getMinimumSize ();

	    d.width = scroll.getSize ().width 
		- scroll.getInsets ().left - scroll.getInsets ().right;

	    return d;
	}
	
	public java.awt.Dimension getPreferredSize () {
	    return getMinimumSize ();
	}
    }
    */

    Vector items = new Vector ();
 

    java.awt.ScrollPane scroll = new java.awt.ScrollPane (); 

    //	(java.awt.ScrollPane.SCROLLBARS_NEVER);
    //java.awt.Scrollbar bar = new java.awt.Scrollbar ();

    java.awt.Panel itemPanel = new java.awt.Panel (new java.awt.GridBagLayout ());

 
    static java.awt.GridBagConstraints lc = new java.awt.GridBagConstraints ();
    static java.awt.GridBagConstraints fc = new java.awt.GridBagConstraints ();


    public Form (String title) {
	super (title);

	scroll.add (itemPanel);
	panel.add ("Center", scroll);
	//	panel.add ("East", bar);

	lc.fill = java.awt.GridBagConstraints.HORIZONTAL;
	fc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    }

    void validate () {
	/*	bar.setMaximum (itemPanel.getMinimumSize ().height);

	bar.setVisibleAmount 
	    (scroll.getSize ().height 
	     - scroll.getInsets ().top 
	     - scroll.getInsets ().bottom);

	bar.setVisible (bar.getMaximum () > bar.getVisibleAmount ());
	*/
	itemPanel.invalidate ();
	panel.validate ();
    }

    
    public int append (Item item) {
	if (item.form != null) throw new IllegalStateException ();
	item.form = this;

	lc.gridy = items.size () * 2;
	itemPanel.add (item.label, lc);

	fc.gridy = lc.gridy+1;
	itemPanel.add (item.getField (), fc);
	items.addElement (item);
	validate ();

	return items.size ()-1;
    }
	
    public int append (String s) {
	return append (new StringItem ("", s));
    }
}
