create table teams (
   team_id BIGINT NOT NULL AUTO_INCREMENT,
   team_name VARCHAR(30) NOT NULL,
   home_location  VARCHAR(30) NOT NULL,
   PRIMARY KEY (team_id)
);

create table players (
   player_id BIGINT NOT NULL AUTO_INCREMENT,
   team_id BIGINT default NULL,
   player_name VARCHAR(30) NOT NULL,
   age  VARCHAR(30) NOT NULL,
   jersey VARCHAR(30) NOT NULL,
   country VARCHAR(30) NOT NULL,
   PRIMARY KEY (player_id),
   FOREIGN KEY(team_id) REFERENCES teams(team_id)
);

create table matches (
   match_id BIGINT NOT NULL AUTO_INCREMENT,
   match_date DATETIME NOT NULL,
   location VARCHAR(30) NOT NULL,
   PRIMARY KEY (match_id)
);

CREATE TABLE ipldb.team_matches (
    team_id	BIGINT NOT NULL,
    match_id BIGINT NOT NULL,
    PRIMARY KEY (team_id, match_id),
    CONSTRAINT FK_teams FOREIGN KEY (team_id) REFERENCES teams (team_id),
    CONSTRAINT FK_matches FOREIGN KEY (match_id) REFERENCES matches(match_id)
);