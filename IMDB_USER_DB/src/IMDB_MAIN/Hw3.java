package IMDB_MAIN;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import DBConnection.*;

public class Hw3 {
	Connection con = null;
	static String query1 = "",query2 = "",query3="",query4="";
	static String finalQuery = "";
	Hw3(){
		con = DBConnection.getDBconnection();
	}
	
	//get movie genres
	public ArrayList<String> getGenres(){
		ArrayList<String> genres = new ArrayList<String>();
		String sql = "Select distinct genre from movie_genres";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				genres.add(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genres;
	}
	
	//get movie countries based on genres selected
	public ArrayList<String> getCountry(List<String> genres, String parameter){
		ArrayList<String> countries = new ArrayList<String>();
		query1="";query2 = "";query3="";query4="";
		String operation = "";
		String condition = (parameter.equals("AND") ? " INTERSECT " : "UNION");
		int count = 0;
		for(String genre : genres){
			count++;
			if(count == 1)
				operation = operation + "Select movieID from movie_genres where genre = '"+genre+"'";
			else
				operation = operation +" "+ condition + " Select movieID from movie_genres where genre = '"+genre+"'";
		}
		query1 = operation;
		String sql = "Select distinct country from MOVIE_COUNTRY where movieID IN ("+operation+")";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				countries.add(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}
	
	//get movie filming locations based on genres and country
	public ArrayList<String> getLocation(List<String> countries, List<String> genres, String parameter){
		query2 = "";query3="";query4="";
		ArrayList<String> locations = new ArrayList<String>();
		String sql1 = "(", sql2 = "(";
		int count = 0;
		String condition = (parameter.equals("AND") ? "INTERSECT" : "UNION");
		
		for(String country : countries){
			count++;
			if(count == 1)
				sql1 = sql1 + "Select movieID from MOVIE_COUNTRY where country = '"+country+"'";
			else
				sql1 = sql1 +" "+ condition + " Select movieID from MOVIE_COUNTRY where country = '"+country+"'";
		}
		sql1 = sql1+")";
		count = 0;
		for(String genre : genres){
			count++;
			if(count == 1)
				sql2 = sql2 + "Select movieID from movie_genres where genre = '"+genre+"'";
			else
				sql2 = sql2 +" "+ condition + " Select movieID from movie_genres where genre = '"+genre+"'";
		}
		sql2 = sql2+")";
		String sql3 = "Select distinct location1 from MOVIE_LOCATION where movieID IN ("+sql1+" INTERSECT "+sql2+")";
		query2 = sql1.isEmpty() ? query1 : "("+query1+")" + " INTERSECT "+sql1;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql3);
			while(rs.next()){
				locations.add(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locations;
	} 
	
	//get the filtered movie id's based on the selected genres, country & locations 
	public void getFilteredMovieIDs(List<String> locations, String parameter){
		String sql = "";
		query3="";query4="";
		int count = 0;
		String condition = (parameter.equals("AND") ? "INTERSECT" : "UNION");
		for(String location : locations){
			count++;
			if(count == 1)
				sql = sql + "select distinct movieID from MOVIE_LOCATION where location1 = '"+location+"'";
			else
				sql = sql + condition+ " select distinct movieID from MOVIE_LOCATION where location1 = '"+location+"'";
		}
		query3 = sql.isEmpty() ? query2 : query2 + " INTERSECT ("+sql+")";
	}
	
	//get the final query to display result
	public String getQuery(String parameter, List<String> tagValues){
		
		String sql = "";
		int count = 0;
		String condition = (parameter.equals("AND") ? "INTERSECT" : "UNION");
		
		if(query4.isEmpty()){
			if(query2.isEmpty()){
				query4 = query1;
			}else if(query3.isEmpty()){
				query4 = query2;
			}else if(query4.isEmpty()){
				query4 = query3;
			}
		}
	
		for(String tagValue : tagValues){
			count++;
			if(count == 1)
				sql = sql + "select movieID from MOVIE_TAGS mt, TAGS t where mt.tagID = t.tagID and t.value = '"+tagValue+"'";
			else
				sql = sql + " "+condition +" "+ "select movieID from MOVIE_TAGS mt, TAGS t where mt.tagID = t.tagID and t.value = '"+tagValue+"'";
		}
		
		sql = sql.isEmpty() ? query4 : query4 + " INTERSECT (" + sql+")";
        query4 = sql;
		String sqlFinal = "select distinct m.title, mg.genres, m.year, mc.country, ml.locations, "
				+"m.rtAllCriticsRating, m.rtTopCriticsRating, m.rtAudienceRating,"
				+ "m.rtAllCriticsNumReviews, m.rtTopCriticsNumReviews, m.rtAudienceNumRatings "
				+ "from movies m,movie_country mc,"
				+ "(select movieID,"
			    +"ltrim(max(sys_connect_by_path"
			      +" (genre, ',' )), ',')"
			       + "genres "
			  +"from (select distinct t.genre, t.movieID, row_number() over "
			           +"(partition by movieID "
			            +"order by genre) rn "
			         +"from (select distinct genre, movieID FROM MOVIE_GENRES) t "
			          +")"
			  +"start with rn = 1 "
			  +"connect by prior rn = rn-1 "
			  +"and prior movieID = movieID "
			  +" group by movieID "
			  +" order by movieID) mg, "
				+ "(select movieID,"
				    +"ltrim(max(sys_connect_by_path"
				      +" (location1, ',' )), ',')"
				       + "locations "
				  +"from (select distinct t.location1, t.movieID, row_number() over "
				           +"(partition by movieID "
				            +"order by location1) rn "
				         +"from (select distinct location1, movieID FROM MOVIE_LOCATION) t "
				          +")"
				  +"start with rn = 1 "
				  +"connect by prior rn = rn-1 "
				  +"and prior movieID = movieID "
				  +" group by movieID "
				  +" order by movieID) ml "
				+ "where m.id = mg.movieID and m.id = mc.movieID "
				+ "and m.id = ml.movieID ";
		sqlFinal = sql.isEmpty() ? sqlFinal : sqlFinal + "and m.id IN ("+sql+")";
		finalQuery = sqlFinal;
		return sqlFinal;
	}
	
	//display the corresponding tag values for the search criteria selected
	public List<String> getTagValues(String rating, String rating_val, String review, String review_val, String from, String to, 
			String tag_wt, String tag_wt_val, String parameter){
		
		query4 = "";
		String sql = "",sql1 = "", sql2 = "", sql3 = "", sql4 = "", sql5 = "";
		
		String condition = (parameter.equals("AND") ? "INTERSECT" : "UNION");
		
		if(!rating_val.isEmpty() && !review_val.isEmpty()){
			String temp = "((select id from MOVIES where rtAllCriticsRating "+rating+" "+rating_val+") "
					+ condition + " (select id from MOVIES where rtAllCriticsNumReviews "+review+" "+review_val+"))";
			sql = sql.isEmpty() ? temp : sql + " INTERSECT "+temp;
		}else if(!rating_val.isEmpty()){
			sql1 = "(select id from MOVIES where rtAllCriticsRating "+rating+" "+rating_val+")";
			sql = sql.isEmpty() ? sql1 : sql + " INTERSECT "+sql1;
		}else if(!review_val.isEmpty()){
			sql2 = "(select id from MOVIES where rtAllCriticsNumReviews "+review+" "+review_val+")";
			sql = (sql == "" ) ? sql2 : sql + " INTERSECT "+sql2;
		}
		
		if(!from.isEmpty()){
			sql3 = "(select id from MOVIES where year >= "+from+")";
			sql = (sql == "" ) ? sql3 : sql + " INTERSECT "+sql3;
		}
		
		if(!to.isEmpty()){
			sql4 = "(select id from MOVIES where year <= "+to+")";
			sql = (sql == "" ) ? sql4 : sql + " INTERSECT "+sql4;
		}
		
		if(!tag_wt_val.isEmpty()){
			sql5 = "(select movieID from MOVIE_TAGS where tagWeight "+tag_wt+" "+tag_wt_val+")";
			sql = (sql == "" ) ? sql5 : sql + " INTERSECT "+sql5;
		}
		
		if(query2.isEmpty()){
			query4 = query1;
		}else if(query3.isEmpty()){
			query4 = query2;
		}else {
			query4 = query3;
		}
		
		sql = sql.isEmpty() ? query4 : query4 + " INTERSECT " + sql;
		query4 = sql;
		List<String> result = new ArrayList<String>();
		Statement stmt = null;
		String sqlTags = "select distinct value from TAGS t, MOVIE_TAGS mt where t.tagID = mt.tagID";
		sqlTags = query4.isEmpty() ? sqlTags : sqlTags + " and mt.movieID IN ("+query4+")";
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlTags);
			while(rs.next()){
				result.add(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//get the final result
	public List<FinalResult> getFinalResult(){
		List<FinalResult> result = new ArrayList<FinalResult>();
		Statement stmt = null;
		String sql = finalQuery;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				double avgRating = (rs.getDouble(6) + rs.getDouble(7) + rs.getDouble(8))/3;
				 DecimalFormat df1 = new DecimalFormat("#.00");
				 avgRating = Double.parseDouble(df1.format(avgRating));
				double avgReviews = (rs.getDouble(9) + rs.getDouble(10) + rs.getDouble(11))/3;
				DecimalFormat df2 = new DecimalFormat("#.00");
				avgReviews = Double.parseDouble(df2.format(avgReviews));
				FinalResult fr = new FinalResult(rs.getString(1),rs.getString(2), rs.getInt(3), 
						         rs.getString(4),rs.getString(5), avgRating, avgReviews);
				result.add(fr);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//clear variables
	public void clear(){
		query1 = "";
		query2 = "";
		query3 = "";
		query4 = "";
		finalQuery = "";
	}
}
