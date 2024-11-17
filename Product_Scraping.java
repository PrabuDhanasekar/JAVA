package learn;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Product_Scraping{
   public static void AmazonIteamSearch(String url) throws ClassNotFoundException, SQLException {

	   Class.forName("com.mysql.cj.jdbc.Driver");
	   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectDB","root","Prabu007*");
	   Statement statement = con.createStatement();
	   
	   
       try {
           Document doc = Jsoup
                   .connect("https://www.amazon.in/s?k="+url)
                   .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36")
                   .header("Accept-Language", "*")
                   .get();
          
           Elements firstHeading= doc.selectXpath("//div[@class='a-section']");
           
           for(Element element:firstHeading) {
            	   String title = element.select("h2>a>span").text();
            	   int index = title.indexOf(" (");
                   title = (index != -1) ? title.substring(0, index) : title;
                   
                   index = title.indexOf(",");
                   title = (index != -1) ? title.substring(0, index) : title;
                   String price = element.select("span.a-price-whole").text();
                   price = price.replace(",", "");
                   
            	   if (!title.isEmpty() && !price.isEmpty()) {
                       String sql = "INSERT INTO amazon(ProductName, Price) VALUES ('" + title + "', '" + price + "')";
                       statement.executeUpdate(sql);
//                       System.out.println(price);
//                       System.out.println(title);
                   }
           }

       }
       catch(IOException e)
       {
           throw new RuntimeException(e);
       }
       con.close();

   }
   public static void FlipkartIteamSearch(String url) throws ClassNotFoundException, SQLException {

	   Class.forName("com.mysql.cj.jdbc.Driver");
	   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectDB","root","Prabu007*");
	   Statement statement = con.createStatement();

       try {
           Document doc = Jsoup
                   .connect("https://www.flipkart.com/search?q="+url)
                   .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36")
                   .header("Accept-Language", "*")
                   .get();
          
           Elements firstHeading= doc.selectXpath("//div[@class='tUxRFH']");
           
           for(Element element:firstHeading) {
            	   String title = element.select("div.KzDlHZ").text();
            	   int index = title.indexOf(" (");
                   title = (index != -1) ? title.substring(0, index) : title;
                   
                   index = title.indexOf(",");
                   title = (index != -1) ? title.substring(0, index) : title;
                   
            	   String price = element.select("div._4b5DiR").text();
            	   price = price.substring(1);   
            	   price = price.replace(",", "");
            	   
            	   if (!title.isEmpty() && !price.isEmpty()) {
                       String sql = "INSERT INTO flipkart (ProductName, Price) VALUES ('" + title + "', '" + price + "')";
                       statement.executeUpdate(sql);
//                       System.out.println(price);
//                       System.out.println(title);
                   }
           }

       }
       catch(IOException e)
       {
           throw new RuntimeException(e);
       }
       con.close();

   }
   public static void ClearData(String arg[]) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectDB","root","Prabu007*");
		   Statement statement = con.createStatement();
		   String sql = "truncate table amazon";
		   statement.executeUpdate(sql);
		   String sql1 = "truncate table flipkart";
		   statement.executeUpdate(sql1);
          con.close();
	}

   public static void GetData() throws SQLException, ClassNotFoundException {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectDB", "root", "Prabu007*");

	    // Query for Amazon
	    String queryAmazon = "SELECT * FROM amazon ORDER BY Price ASC LIMIT 1";
	    PreparedStatement statementAmazon = con.prepareStatement(queryAmazon);
	    ResultSet resultAmazon = statementAmazon.executeQuery();

	    String amazonName = null;
	    int amazonPrice = Integer.MAX_VALUE; // Default high value for comparison

	    if (resultAmazon.next()) {
	        amazonName = resultAmazon.getString("ProductName");
	        amazonPrice = Integer.parseInt(resultAmazon.getString("Price").replace(",", ""));
	        System.out.println("Amazon Product Name: " + amazonName);
	        System.out.println("Amazon Lowest Price: " + amazonPrice);
	    } else {
	        System.out.println("No rows found in Amazon table.");
	    }

	    // Query for Flipkart
	    String queryFlipkart = "SELECT * FROM flipkart ORDER BY Price ASC LIMIT 1";
	    PreparedStatement statementFlipkart = con.prepareStatement(queryFlipkart);
	    ResultSet resultFlipkart = statementFlipkart.executeQuery();

	    String flipkartName = null;
	    int flipkartPrice = Integer.MAX_VALUE; // Default high value for comparison

	    if (resultFlipkart.next()) {
	        flipkartName = resultFlipkart.getString("ProductName");
	        flipkartPrice = Integer.parseInt(resultFlipkart.getString("Price").replace(",", ""));
	        System.out.println("Flipkart Product Name: " + flipkartName);
	        System.out.println("Flipkart Lowest Price: " + flipkartPrice);
	    } else {
	        System.out.println("No rows found in Flipkart table.");
	    }

	    // Compare prices and determine the best
	    if (amazonPrice == flipkartPrice) {
	        System.out.println("Lowest price is equal: " + amazonPrice);
	    } else if (amazonPrice < flipkartPrice) {
	        System.out.println("Lowest price is Amazon: " + amazonPrice + " (Product: " + amazonName + ")");
	    } else {
	        System.out.println("Lowest price is Flipkart: " + flipkartPrice + " (Product: " + flipkartName + ")");
	    }

	    con.close();
	}

   public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
	   Scanner myInput = new Scanner(System.in);
	   int i;
	   int userSearch = 1;
	   while(userSearch == 1) {
		   System.out.println("Enter the input to search:");
		   String url = myInput.nextLine(); 
		   System.out.println("You are searching this "+url);
		   Product_Scraping.FlipkartIteamSearch(url);
		   Product_Scraping.AmazonIteamSearch(url);
		   Product_Scraping.GetData();
		   
			   
		   System.out.println("end\n");
		   
		   System.out.println("Delete the table data enter '1' (or) Not Delete the table data enter '2'");
		   i = myInput.nextInt();
		   if(i==1) {
			   Product_Scraping.ClearData(args);
			   System.out.println("Data is Deleted");
		   }else {
			   System.out.println("Data is not Deleted");
		   }
		   System.out.println("Your search any another Product:\nYes means enter '1'\nNo means enter '0'");
		   userSearch = myInput.nextInt();
		   myInput.nextLine();
		   
	   }
	   
	   
	
   }
}
