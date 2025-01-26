-- Genres form a tree/graph with Audio and Video as children of root, Music as child of Audio, Hard-Rock as child of Rock, etc.
CREATE TABLE Genre (
  Id INTEGER PRIMARY KEY,
  Name TEXT NOT NULL UNIQUE,
  Parent INTEGER,
  Synonyms TEXT,
  CONSTRAINT FK_Genre_Parent FOREIGN KEY(Parent) REFERENCES Genre(Id)
)

-- In addition to Parent a Genre can have many Relatives as secondary parents
CREATE TABLE GenreXRelatives (
  Genre INTEGER NOT NULL,
  Relativ INTEGER NOT NULL,
  CONSTRAINT PK_GenreXRelatives PRIMARY KEY(Genre, Relativ),
  CONSTRAINT FK_GenreXRelatives_Genre FOREIGN KEY(Genre) REFERENCES Genre(Id),
  CONSTRAINT FK_GenreXRelatives_Relativ FOREIGN KEY(Relativ) REFERENCES Genre(Id)
)