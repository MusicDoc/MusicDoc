-- A Media is something like a Single, Album, CD, CD-Box (with multiple CD-Discs), Audiobook with many episodes, DVD with many seasons, Venyl-record, etc.
-- To build a group of medias their sub-medias (discs, sides, seasons, episodes) reference the group as their Parent and use Num for ordering.
-- So "Greatest Hits" from "Bon Jovi" can be a Media with "Disc 1" and "Disc 2" as sub-medias
-- You can even have multi-levels of parent/child releations for Root/Season/Episode/Disc/Unit
-- In case a column is null and Parent is not null, then the column value will be logically inherited.
-- However, for the Name special rules apply and it will be composed automatically appending to the parent Name
-- If multiple MediaFile's refer to the same Media that has IsUnit = TRUE (1) then all these MediaFile's from a unit that is either played in order or not at all.
-- E.g. a classical CD may contain two symphonies that each shall be treated as unit or the tracks of an audiobook are treated as unit
-- Otherwise such tracks could be played in random order by shuffle mode, etc.
-- The Genre is an optional default genere for pre-selection (UX)
CREATE TABLE Media (
  Id INTEGER PRIMARY KEY,
  Name TEXT NOT NULL,
  Artist INTEGER,
  Parent INTEGER,
  Genre INTEGER,
  Num INTEGER,
  IsUnit INTEGER,
  Year INTEGER,
  CONSTRAINT FK_Media_Parent FOREIGN KEY(Parent) REFERENCES Media(Id),
  CONSTRAINT FK_Media_Artist FOREIGN KEY(Artist) REFERENCES Artist(Id),
  CONSTRAINT FK_Media_Genre FOREIGN KEY(Genre) REFERENCES Genre(Id)
)