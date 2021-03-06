package com.mierdin.NetworkMonitor.WSOps;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import com.mierdin.NetworkMonitor.Classes.ConfigLoader;

import android.util.Log;

public class wakeUpClass {
    public static Boolean sendWOL(String ipAddr, String macAddr) throws Exception, IOException, XmlPullParserException 
    {	 
   	 	String METHOD_NAME = "wakeUp";   
   	    String NAMESPACE = "urn:wakeUp";    
   	    String URL = ConfigLoader.pathToWS;
   	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	        request.newInstance ();
	        request.addProperty("ipStr", ipAddr);
	        request.addProperty("macStr", macAddr);
	        
           Log.d("NetworkMonitor", "Created KSOAP object");     
           Log.d("NetworkMonitor", "Set properties ");
           SoapSerializationEnvelope envelope = 
               new SoapSerializationEnvelope(SoapEnvelope.VER11);
           envelope.bodyOut = request;
           envelope.encodingStyle = "utf-8";
           envelope.enc = SoapSerializationEnvelope.ENC2001;
           envelope.xsd = SoapEnvelope.XSD;
           envelope.xsi = SoapEnvelope.XSI;
           
           envelope.dotNet = false;
           envelope.setOutputSoapObject(request);
           Log.d("NetworkMonitor", "Sending WS request");
           AndroidHttpTransport transport = new AndroidHttpTransport(URL);
           transport.call(URL, envelope);
           Log.d("NetworkMonitor", "Waiting for response");
           //java.util.Vector<Object> result = (java.util.Vector<Object>)envelope.getResponse();
           
           @SuppressWarnings("unused")
		   SoapPrimitive result = (SoapPrimitive)envelope.getResponse();
          
           return true;
    }  
}
