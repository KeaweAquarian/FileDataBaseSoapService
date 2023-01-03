package com.mcgill.cccs425.soap;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.WebFault;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author Keawe Aquarian 
 * 261073778
 */
@MTOM(enabled = true, threshold = 40)
@WebService(serviceName = "SoapService")
public class RecieveBinaryDataImpl implements RecieveBinaryData {
    
    //Create and instance of the singleton object containing our maps.
   Singleton singleton = Singleton.getInstance();

    //Method to recieve and store byte[]s in memory by their name.
    @Override
    public String upload(@WebParam(name = "Filename") String fileName,
            @WebParam(name = "file") DataHandler file) {

        try {
            
            singleton.putMimeType(fileName, file.getContentType());
            //Create a stream transfer files to new byte[]
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            file.writeTo(byteArray);

            //Add byte[] to map
            singleton.putByte(fileName, byteArray.toByteArray());
            
            byteArray.flush();
            byteArray.close();
        } catch (Exception e) {
            return e.getMessage();
        }

        //success message
        return "File " + fileName + "  received" + " of size " + singleton.getFile(fileName).length;
    }

    //Method to send stored byte[] files.
    @Override
    public @XmlMimeType("application/octet-stream")
    DataHandler download(@WebParam(name = "Filename") String fileName) throws MySOAPFault {

        //Check if requested file is in memory.
        if (!singleton.getMap().containsKey(fileName)) {
            Throwable t = new IllegalArgumentException("File not stored");
            throw new MySOAPFault("Error! File name not found! ", t);
        }
        //Create a new datahandler form stored byte[].
        DataHandler returnData = new DataHandler(singleton.getFile(fileName), singleton.getMimeType(fileName));

        return returnData;

    }

    //method to calculate the fibonacci values 0-n.
    @Override
    public int[] fibonacciCalculation(@WebParam(name = "number") int n) throws MySOAPFault {



        //Check if a valid integer has be supplied
        if (n < 0) {
            Throwable t = new IllegalArgumentException("Value must be a positive integer!");
            throw new MySOAPFault("Incorrect value must be a positive integer! ", t);
        }

                //int[] for values
        int[] result = new int[n];
        int first = 0;
        int second = 1;
        int index = 0;

        //calculatins for fibonacci sequence
        for (int i = 0; i < n; i++) {

            result[i] = first;

            int third = second + first;
            first = second;
            second = third;
            index++;
        }
        return result;

    }

    //Method call customized soap fault.
    @WebFault(name = "MySOAPFault", targetNamespace = "http://www.purwebservice.com")
    public class MySOAPFault extends Exception {

        public MySOAPFault(String message) {
            super(message);
        }

        public MySOAPFault(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
