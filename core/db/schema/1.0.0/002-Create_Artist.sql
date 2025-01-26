-- An Artist can be a single person (composer, singer, director, actor, drummer, etc.), an entire band of musicians, or a movie company
-- To build a group of artists their members reference the group as their Parent
-- The Genre is an optional default genere for pre-selection (UX)
CREATE TABLE Artist (
  Id INTEGER PRIMARY KEY,
  Name TEXT NOT NULL,
  Genre INTEGER,
  Role INTEGER,
  Parent INTEGER,
  Year INTEGER,
  CONSTRAINT FK_Artist_Parent FOREIGN KEY(Parent) REFERENCES Artist(Id),
  CONSTRAINT FK_Artist_Genre FOREIGN KEY(Genre) REFERENCES Genre(Id)
)