package com.mcgill.cccs425.soap;

import com.mcgill.cccs425.soap.RecieveBinaryDataImpl.MySOAPFault;
import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author Keawe Aquarian 
 * 261073778
 */
//Service Endpoint Interface
@WebService
@MTOM(enabled = true, threshold = 40)
@SOAPBinding(style = Style.DOCUMENT)
public interface RecieveBinaryData {

    //Method to recieve and store a byte[]
    @WebMethod(operationName = "upload")
    public String upload(@WebParam(name = "name") String txt,
            @WebParam(name = "file") DataHandler file);

    //method to return stored byte[]
    @WebMethod(operationName = "download")
    public @XmlMimeType("application/octet-stream")
    DataHandler download(String fileName) throws MySOAPFault;

    //Method to return Fibonacci sequence for a given number n
    @WebMethod(operationName = "fibonacciCaluculation")
    public int[] fibonacciCalculation(int n) throws MySOAPFault;

}
