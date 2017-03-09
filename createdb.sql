CREATE TABLE MOVIES 
(id integer PRIMARY KEY,	
title	varchar2(200) NOT NULL,
imdbID	integer,
spanishTitle	varchar2(100),
imdbPictureURL	varchar2(1500),
year	integer,
rtID	varchar2(100),
rtAllCriticsRating float,	
rtAllCriticsNumReviews	integer,
rtAllCriticsNumFresh integer,
rtAllCriticsNumRotten	integer,
rtAllCriticsScore integer,
rtTopCriticsRating float,	
rtTopCriticsNumReviews integer,	
rtTopCriticsNumFresh integer,
rtTopCriticsNumRotten integer,
rtTopCriticsScore integer,	
rtAudienceRating float,	
rtAudienceNumRatings integer,	
rtAudienceScore	integer,
rtPictureURL varchar2(1500)
)

CREATE TABLE TAGS (
tagID integer PRIMARY KEY,
value varchar2(60)
)

CREATE TABLE MOVIE_COUNTRY (
movieID	integer,
country	varchar2(60),
FOREIGN KEY (movieID) REFERENCES MOVIES(ID)
)

CREATE TABLE MOVIE_GENRES (
movieID	integer,
genre varchar2(60),
FOREIGN KEY (movieID) REFERENCES MOVIES(ID)
)

CREATE TABLE MOVIE_LOCATION (
movieID	integer,
location1 varchar2(100),	
FOREIGN KEY (movieID) REFERENCES MOVIES(ID)
)

CREATE TABLE MOVIE_TAGS (
movieID	integer,
tagID integer,
tagWeight integer,
FOREIGN KEY (movieID) REFERENCES MOVIES(ID),
FOREIGN KEY (tagID) REFERENCES TAGS(tagID )
)
