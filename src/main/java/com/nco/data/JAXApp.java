package com.nco.data;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXApp {
	
    public static void main(String args[]) throws Exception {
    	xmlToObject();
    }
    
    private static void xmlToObject() throws Exception {
    	try {  
            File file = new File("src\\main\\java\\com\\nco\\data\\vendor_magnatune_song_info.xml");  
            JAXBContext jaxbContext = JAXBContext.newInstance(Songlist.class);  
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
            Songlist que= (Songlist) jaxbUnmarshaller.unmarshal(file); 
            
            List<Song> list=que.getSong();  
            
            for(Song ans:list)  {
            	System.out.println(ans.getArtist());
            }
              
          } catch (JAXBException e) {  
            e.printStackTrace();  
          }  
       
        }  

}