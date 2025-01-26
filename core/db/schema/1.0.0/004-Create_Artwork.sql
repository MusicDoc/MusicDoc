-- An Artwork is an abstract piece of art. It is instantiated by a MediaFile.
-- However, multiple variants of the same Artwork can exist as different MediaTack's (e.g. remixes, identical or different versions of different albums)
-- Also, an Artwork can be derived from another Artwork (such as a cover version)
-- E.g. "Crying in the rain" from a-ha is actually a cover of the same song composed by Carole King originally performed by the Everly Brothers.
-- Like with other parent/child hierarchies columns that are null will be logically inherited if Parent is not null except for Filename
CREATE TABLE Artwork (
  Id INTEGER PRIMARY KEY,
  Name TEXT NOT NULL,
  Parent INTEGER,
  Genre INTEGER,
  Score TEXT,
  Duration INTEGER,
  Filename TEXT, --path to the media file on your disc
  CONSTRAINT FK_Artwork_Parent FOREIGN KEY(Parent) REFERENCES Artwork(Id),
  CONSTRAINT FK_Artwork_Genre FOREIGN KEY(Genre) REFERENCES Genre(Id)
)

-- Enum for ArtworkXArtist.Role, examples are composer, lyricist, producer, performer (e.g. band or orchestra), actor, etc.
CREATE TABLE ArtworkRole (
  Id INTEGER PRIMARY KEY,
  Name TEXT NOT NULL UNIQUE
)

-- cross-table for link from Artwork to Artist classified with Role
-- even allows multiple composers or directors for the same Artwork
CREATE TABLE ArtworkXArtist (
  Artwork INTEGER NOT NULL,
  Artist INTEGER NOT NULL,
  Role INTEGER NOT NULL,
  CONSTRAINT PK_ArtworkXArtist PRIMARY KEY(Artwork, Artist, Role),
  CONSTRAINT FK_ArtworkXArtist_Artwork FOREIGN KEY(Artwork) REFERENCES Artwork(Id),
  CONSTRAINT FK_ArtworkXArtist_Artist FOREIGN KEY(Artist) REFERENCES Artist(Id),
  CONSTRAINT FK_ArtworkXArtist_Role FOREIGN KEY(Role) REFERENCES ArtworkRole(Id)
)

-- FullTextSearch index for Artwork
-- in case structual data gets changed (Genre/Artist/Album renamed) full reindex is required
CREATE VIRTUAL TABLE ArtworkFts USING fts5(
  Name,
  Artist,
  Album,
  Lyrics,
  Genre,
  content='',
  contentless_delete=1
)
