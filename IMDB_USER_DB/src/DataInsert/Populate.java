package DataInsert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBConnection.*;

public class Populate {
	
	 public void populate_movies(String filePath){
		 PreparedStatement movies_sql = null;
		 Connection con = DBConnection.getDBconnection();
		 try {
			FileReader file = new FileReader(filePath);
			BufferedReader br = new BufferedReader(file);
			br.readLine();
			String fileData = null;
			while((fileData = br.readLine())!= null){
				String[] data = fileData.split("\t");
				Integer id 	= Integer.parseInt(data[0]);
				String title = data[1];	
				Integer imdbID	= Integer.parseInt(data[2]);
				String spanishTitle = data[3];	
				String imdbPictureURL	= data[4];
				Integer year = Integer.parseInt(data[5]);
				String rtID = data[6];
				String rtAllCriticsRating = data[7];
				String rtAllCriticsNumReviews = data[8];	
				String rtAllCriticsNumFresh = data[9];
				String rtAllCriticsNumRotten = data[10];	
				String rtAllCriticsScore = data[11];
				String rtTopCriticsRating = data[12];
				String rtTopCriticsNumReviews = data[13];
				String rtTopCriticsNumFresh = data[14];
				String rtTopCriticsNumRotten = data[15];
				String rtTopCriticsScore = data[16];
				String rtAudienceRating 	= data[17];
				String rtAudienceNumRatings = data[18];
				String rtAudienceScore	= data[19];
				String rtPictureURL = data[20];
		
				if(movies_sql == null){
					String sql = "INSERT INTO MOVIES VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					movies_sql = con.prepareStatement(sql);
				}
				movies_sql.setInt(1, id);
				movies_sql.setString(2, title);
				movies_sql.setInt(3, imdbID);
				movies_sql.setString(4, spanishTitle);
				movies_sql.setString(5, imdbPictureURL);
				movies_sql.setInt(6, year);
				movies_sql.setString(7, rtID);
				if(!rtAllCriticsRating.equals("\\N")) movies_sql.setFloat(8, Float.parseFloat(rtAllCriticsRating));
				if(!rtAllCriticsNumReviews.equals("\\N"))movies_sql.setInt(9, Integer.parseInt(rtAllCriticsNumReviews));
				if(!rtAllCriticsNumFresh.equals("\\N"))movies_sql.setInt(10, Integer.parseInt(rtAllCriticsNumFresh));
				if(!rtAllCriticsNumRotten.equals("\\N"))movies_sql.setInt(11, Integer.parseInt(rtAllCriticsNumRotten));
				if(!rtAllCriticsScore.equals("\\N"))movies_sql.setInt(12, Integer.parseInt(rtAllCriticsScore));
				if(!rtTopCriticsRating.equals("\\N"))movies_sql.setFloat(13, Float.parseFloat(rtTopCriticsRating));
				if(!rtTopCriticsNumReviews.equals("\\N"))movies_sql.setInt(14, Integer.parseInt(rtTopCriticsNumReviews));
				if(!rtTopCriticsNumFresh.equals("\\N"))movies_sql.setInt(15, Integer.parseInt(rtTopCriticsNumFresh));
				if(!rtTopCriticsNumRotten.equals("\\N"))movies_sql.setInt(16, Integer.parseInt(rtTopCriticsNumRotten));
				if(!rtTopCriticsScore.equals("\\N"))movies_sql.setInt(17, Integer.parseInt(rtTopCriticsScore));
				if(!rtAudienceRating.equals("\\N"))movies_sql.setFloat(18, Float.parseFloat(rtAudienceRating));
				if(!rtAudienceNumRatings.equals("\\N"))movies_sql.setInt(19, Integer.parseInt(rtAudienceNumRatings));
				if(!rtAudienceScore.equals("\\N"))movies_sql.setInt(20, Integer.parseInt(rtAudienceScore));
				if(!rtPictureURL.equals("\\N"))movies_sql.setString(21, rtPictureURL);
				movies_sql.executeUpdate();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 if(movies_sql != null){
				 try {
					movies_sql.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			 }
		 }
	 }
	 
	 public void populate_tags(String filePath){
		 Connection con = DBConnection.getDBconnection();
		 PreparedStatement movies_sql = null; 
		 try {
				FileReader file = new FileReader(filePath);
				BufferedReader br = new BufferedReader(file);
				br.readLine();
				String fileData = null;
				while((fileData = br.readLine()) != null){
					String[] data = fileData.split("\t");
					Integer tagId 	= Integer.parseInt(data[0]);
					String value = data[1];
					if(movies_sql == null){
						String sql = "INSERT INTO TAGS VALUES(?,?)";
						movies_sql = con.prepareStatement(sql);
					}
					movies_sql.setInt(1, tagId);
					movies_sql.setString(2, value);
					movies_sql.executeUpdate();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				 if(movies_sql != null){
					 try {
						movies_sql.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				 }
			 }	
	 }
	 
	 public void populate_movie_country(String filePath){
		 Connection con = DBConnection.getDBconnection();
		 PreparedStatement businessSQL = null;
		 try {
		    BufferedReader br = new BufferedReader(new FileReader(filePath));
			String strline=null;
			br.readLine();
			while((strline=br.readLine()) != null)
			{
				String lines[]= strline.split("\t");
				
				Integer num1 =  Integer.parseInt(lines[0]);
				String num2 =   (lines.length == 2) ? lines[1] : "";
				
							
				 if (businessSQL == null)
		            {
		                String sql = "INSERT INTO MOVIE_COUNTRY VALUES(?,?)";
		                businessSQL = con.prepareStatement(sql);
		            }
				   businessSQL.setInt(1,num1);
			       businessSQL.setString(2,num2);
			       businessSQL.executeUpdate();
			}
		 } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				 if(businessSQL != null){
					 try {
						businessSQL.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 } 
			 }
	 }
	 
	 public void populate_movie_genres(String filePath){
		 Connection con = DBConnection.getDBconnection();
		 PreparedStatement businessSQL = null;
		 try {
			    BufferedReader br = new BufferedReader(new FileReader(filePath));
				String strline=null;
				br.readLine();
				while((strline=br.readLine()) != null)
				{
					String lines[]= strline.split("\t");
					
					Integer num1 =  Integer.parseInt(lines[0]);
					String num2 =   lines[1];
					
					 if (businessSQL == null)
			            {
			                String sql = "INSERT INTO MOVIE_GENRES VALUES(?,?)";
			                businessSQL = con.prepareStatement(sql);
			            }
					   businessSQL.setInt(1,num1);
				       businessSQL.setString(2,num2);
				       businessSQL.executeUpdate();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				 if(businessSQL != null){
					 try {
						businessSQL.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				 }
				 
			 }
	 }
	 
	 public void populate_movie_location(String filePath){
		 Connection con = DBConnection.getDBconnection();
		 PreparedStatement businessSQL = null;
		 try {
		    BufferedReader br = new BufferedReader(new FileReader(filePath));
			String strline=null;
			br.readLine();
			while((strline=br.readLine()) != null)
			{
				String lines[]= strline.split("\t");
				if(lines.length == 2){
					Integer num1 =  Integer.parseInt(lines[0]);
					String num2 =   lines[1];
					
					 if (businessSQL == null)
			            {
			                String sql = "INSERT INTO MOVIE_LOCATION VALUES(?,?)";
			                businessSQL = con.prepareStatement(sql);
			            }
				   businessSQL.setInt(1,num1);
			       businessSQL.setString(2,num2);
			       businessSQL.executeUpdate();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			 if(businessSQL != null){
				 try {
					businessSQL.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 } 
		 }
	 }
	 
	 public void populate_movie_tags(String filepath)
	 {
		 Connection con = DBConnection.getDBconnection();
		 PreparedStatement businessSQL = null;
		 try{
	        BufferedReader br = new BufferedReader(new FileReader(filepath));
			String strline=null;
			br.readLine();
			while((strline=br.readLine()) != null)
			{
				String lines[]= strline.split("\t");
				
				Integer num1 =  Integer.parseInt(lines[0]);
				Integer num2 =  Integer.parseInt(lines[1]);
				Integer num3 =  Integer.parseInt(lines[2]);
				
				
				 if (businessSQL == null)
		            {
		                String sql = "INSERT INTO MOVIE_TAGS VALUES(?,?,?)";
		                businessSQL = con.prepareStatement(sql);
		            }
				   businessSQL.setInt(1,num1);
			       businessSQL.setInt(2,num2);
			       businessSQL.setInt(3,num3);
			       businessSQL.executeUpdate();
			}
		 } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				 if(businessSQL != null){
					 try {
						businessSQL.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }	 
			 }
		}
	public static void main(String args[]){
		Populate obj = new Populate();
		obj.populate_movies(args[0]);
		obj.populate_tags(args[1]);
		obj.populate_movie_country(args[2]);
		obj.populate_movie_tags(args[3]);
		obj.populate_movie_genres(args[4]);
		obj.populate_movie_location(args[5]);
		System.out.println("done");
	}

}
