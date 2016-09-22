import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.jsoup.HttpStatusException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import javax.swing.DefaultListModel;

import javax.swing.*;

import java.awt.*;

/*
 * Classes                                               
 */
public class ElementLister  {

	/**
	 * 
	 */
	String html,tag;
	Document doc;
	Elements links;
	File input;
	ArrayList<String> tagList;
	DefaultListModel<String>  LM;
	
	public ElementLister(String URL, String toFind )
	{
		{
			html= URL;
			tag = toFind;
			tagList = new ArrayList<String>();
			try {
				doc=Jsoup.connect(html).get();
				System.out.println("Recieved: " + html);
				links = doc.select(toFind);
				
				for (Element link : links)
			    {
			        tagList.add(link.text());	         
			    }
			    if (tagList.size()==1)
				   {    
				    System.out.println("Found "+ tagList.size()+ " instance of " + tag);
				   }
				   else 
					  {    
				    System.out.println("Found "+ tagList.size()+ " instances of " + tag);
				   }
			}catch ( HttpStatusException e ) {
				
				e.printStackTrace();
				System.out.println("Error in connecting to " + html +". Please change and try again");
				JOptionPane.showMessageDialog(null, null, "Error in connecting to " + html +". Please change and try again",1);
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println("IOException");
				System.out.println(e.getMessage());
			} 
			
		}
		
	}
	
	public ElementLister(File fileName, String toFind )
	{
		//html= URL;
		tag = toFind;
		tagList = new ArrayList<String>();
		
		input = new File("URL");
		
		try {
			doc = Jsoup.parse(input,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String getTagCount() {
		
		if (tagList.size()==1)
		   {    
			return ("Found "+ tagList.size()+ " instance of " + tag);
		   }
		   else 
			  {    
			   return ("Found "+ tagList.size()+ " instances of " + tag);
		   }
	}
	public int getTagQuantity(){
		return tagList.size();
	}

	public String getSelectedTag(int selector){
		
		if (validateTagListContents() == true)
				{
					return tagList.get(selector);
				}
		
		else{
			return("Nothing");
		}
			
	}
	public String getElement(){
		return tag;
	}
	
	public void setElement(String toFind){
		
		tag = toFind;
		
	}
	public void genListModel()
	{
	  LM = new DefaultListModel<String>();
	  
	  if (validateTagListContents() == true)
	  {
		  for (int i = 0; i< tagList.size(); i++)
		  {
			  LM.addElement(tagList.get(i));
		  }//end for
	  }//end if
	
	 }
	/**/
	public String getHtml(){
		return html;
	}

	public void setHtml(String URL){
		
		html = URL;
		
	}


	public ArrayList<String> getEleList(){
		return tagList;
		
	}

	public void printToUL(){

		PrintWriter out;
		int i=0;

		String liStart= "<li>";
		String liEnd= "</li>";			
				
		try {
			out = new PrintWriter (new BufferedWriter(new FileWriter("FoundElements.html")));
		
		if (tagList.isEmpty()==false)
		{
			out.println("<ul>");
		    for (Element link : links)
		    {
		    	 out.print(liStart);
		         out.print(tagList.get(i));
		         out.println(liEnd);
		         i++;		         
		    }
			
		    out.println("</ul>");
		}
		else {
			out.println("There were no elements to display");
		}
		
		//close stream
		out.close();

		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();

		}
				
		
	}
	
	public void printToText(String outFile){
	
		PrintWriter out;
		String fileName= outFile;
		int i=0;
		try {
			out = new PrintWriter (new BufferedWriter(new FileWriter(fileName)));
		
		if (tagList.isEmpty()==false)
		{
		    for (int j = 0; j < links.size(); j++) {
				Element link = links.get(j);
				out.println(tagList.get(i));
		         i++;
			}
			
		}
		 
		//close stream
		out.close();

		} catch (IOException e) {

			e.printStackTrace();
			e.getMessage();
			
		}
				
		
	}
	
	public void printToScreen(){
	   
		if( validateTagListContents() == true){
	
			for (Element link : links)
	    {
	        System.out.println("Element " +tag +": " + link.text()); 
		         		         
	    }
		
		}
		else
		{
			System.out.println("There were no elements found to display.");
		}
		
		
	}
/*	private boolean validateURL()
	{
	//Fix up	 
	    String urlRegex = "\\b(https?|ftp|file|ldap)://"
	            + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]"
	            + "*[-A-Za-z0-9+&@#/%=~_|]";
	    
	//    assertTrue("http://www.leveluplunch.com".matches(urlRegex));
	    
		 return false;
	}
	*/
	private boolean validateTagListContents() 
	{//Checks if the tagList is empty
		// Used when printing
		if(tagList.isEmpty()==true)// if its true then there
								//is nothing in the array
			return false;
			else
				return true;
					
	}
	
}
