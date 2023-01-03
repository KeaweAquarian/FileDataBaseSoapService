/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcgill.cccs425.soap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author keawe
 */

//A Singleton class to create one instance of a map to hold the files and a map to hold the content type.
    public class Singleton{

    //Map to hold byte[] files in memory.
    private static Map<String, byte[]> files = new HashMap<>();
    //Map to hold the Mime types
    private static Map<String, String> fileMimeType = new HashMap<>();

    private static Singleton instance = null;

    private Singleton() {}

    //Synchronized method to create instance.
    public synchronized static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    //Method to add array to map.
    public void putByte(String fileName, byte[] file) {
        files.put(fileName, file);       
    }
    
    //Method to add mime type to map.
        public void putMimeType(String fileName, String fileType) {
        fileMimeType.put(fileName, fileType);       
    }
        
        
       //Method to get mime type for file.
         public String getMimeType(String fileName) {
        return fileMimeType.get(fileName);       
    }
         
         //Method to get a file from map
         public byte[] getFile(String fileName) {
        return files.get(fileName);
    }
         //Method to get entire files map.
        public Map<String, byte[]> getMap() {
        return files;
    }
}
