// kAWT - Kilobyte Abstract Window Toolkit
//
// Copyright (C) 1999-2000 by Michael Kroll & Stefan Haustein GbR, Essen
//
// Contact: kawt@kawt.de
// General Information about kAWT is available at: http://www.kawt.de
//
// Using kAWT for private and educational and in GPLed open source
// projects is free. For other purposes, a commercial license must be
// obtained. There is absolutely no warranty for non-commercial use.
//
//
// 1. BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO
//    WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE
//    LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT
//    HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM "AS IS" WITHOUT
//    WARRANTY OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT
//    NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
//    FITNESS FOR A PARTICULAR PURPOSE.  THE ENTIRE RISK AS TO THE
//    QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU.  SHOULD THE
//    PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY
//    SERVICING, REPAIR OR CORRECTION.
//   
// 2. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN
//    WRITING WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY
//    MODIFY AND/OR REDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, BE
//    LIABLE TO YOU FOR DAMAGES, INCLUDING ANY GENERAL, SPECIAL,
//    INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR
//    INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
//    DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU
//    OR THIRD PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY
//    OTHER PROGRAMS), EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN
//    ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
//   
//    END OF TERMS AND CONDITIONS
// 

package org.me4se.impl;

import javax.microedition.rms.*;
import javax.microedition.midlet.ApplicationManager;
import java.util.*;
import java.io.*;
import org.me4se.*;


public abstract class RecordStoreImpl extends RecordStore {

    public static Hashtable recordStores = new Hashtable ();
    public static RecordStoreImpl metaStore = newInstance ();
    
    protected Vector listeners;
    
    String recordStoreName;
    int refCount;


    public void addRecordListener (RecordListener listener) {
	if (listeners == null)
	    listeners = new Vector ();
	
//	listeners.add (listener);
	listeners.insertElementAt( listener , listeners.size() );
    }

    
    public void removeRecordListener (RecordListener listener) {
	if (listeners != null)
	    listeners.remove (listener);
    }
    
       
    public abstract void deleteRecordStoreImpl () throws RecordStoreException ;


    void checkOpen () throws RecordStoreNotOpenException {
	if (refCount == 0) 
	    throw new RecordStoreNotOpenException 
		("RecordStore not open: "+recordStoreName);
    } 


    void checkId (int index) throws RecordStoreException {
	checkOpen ();
	if (index < 1 || index >= getNextRecordID ()) 
	    throw new InvalidRecordIDException 
		("Id "+index+" not valid in recordstore "+recordStoreName);
    }



    public static RecordStoreImpl newInstance () {
	return new org.me4se.impl.RecordStoreImpl_file ();
    }

    
    public abstract String [] listRecordStoresImpl ();

    public abstract void open (String recordStoreName, 
			       boolean create) throws RecordStoreNotFoundException;
}
