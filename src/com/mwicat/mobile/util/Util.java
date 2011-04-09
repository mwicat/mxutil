/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwicat.mobile.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import de.enough.polish.util.TextUtil;


/**
 *
 * @author mwicat
 */
public class Util {
	
	public static final char NEWLINE = '\n';
	public static final char NUL = '\0';
	

    public static final Vector EMPTY_VECTOR = new Vector(0);

    public static int toInt(Object o) {
        Integer i = (Integer) o;
        return i.intValue();
    }
    
    private static String readUntilInternal(final Reader r, char ch)  throws IOException {
        final StringBuffer sb = new StringBuffer();
        int c = r.read();
        while (!(c == -1 || c == ch)) {
            sb.append((char) c);
            c = r.read();
        }
        return sb.toString();    	
    }
    
    

    public static String readUntil(final Reader r, char ch) {
        try {
            return readUntilInternal(r, ch);
        } catch (final IOException e) {
            return null;
        }
    }

    public static String readLine(final Reader r) {
        return readUntil(r, NEWLINE);
    }

    

    public static Integer hashPair(int x, int y) {
        return new Integer((x << 8) | y);
    }

    public static Long hashPair(Object x, Object y) {
        return new Long(x.hashCode() << 32 | y.hashCode());
    }


    public static long getTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static boolean equals(Vector v1, Vector v2) {
        if (v1.size() != v2.size()) {
            return false;
        }

        for (int i = 0; i < v1.size(); i++) {
            if (v1.elementAt(i) != v2.elementAt(i)) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean equals(Hashtable ht1, Hashtable ht2) {
        if (ht1.size() != ht2.size()) {
            return false;
        }

    	for (Enumeration e = ht1.keys() ; e.hasMoreElements() ;) {
            Object k = (String)e.nextElement();
            Object v = (String)ht1.get(k);
            if (!ht2.containsKey(k)) {
            	return false;
            }
            Object v2 = ht2.get(k);
            if (!(v == v2 || v != null && v.equals(v2))) {
            	return false;
            }
        }


        return true;
    }
    

    public static Vector vectorSplit(String orig, char sep) {
        String[] fields = TextUtil.split(orig, sep);
        Vector v = new Vector(fields.length);
        for (int i = 0; i < fields.length; i++) {
            v.addElement(fields[i]);
        }
        return v;
    }

    public static String arrayToString(Object[] arr) {
        StringBuffer sb = new StringBuffer("[");
        int i;
        for (i = 0; i < arr.length - 1; i++) {
            sb.append(arr[i] + ", ");
        }
        sb.append(arr[i] + "]");
        return sb.toString();
    }
    
    public static String ltrim(String s) {
    	int i;
    	for (i = 0; i < s.length() && s.charAt(i) <= '\u0020'; i++);
    	return s.substring(i);
    }
}
