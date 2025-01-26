-- There is at least one user which is you
-- However we support that you also add your family members and friends as users
-- Rankings and other subjective data is stored per user
-- This user has nothing to do with a login or security concept
-- You can select multiple users and thereby build the union/average of their data
CREATE TABLE User (
  Id INTEGER PRIMARY KEY,
  Name TEXT NOT NULL
)

CREATE TABLE UserXArtwork (
  User INTEGER NOT NULL,
  Artwork INTEGER NOT NULL,
  Rating INTEGER NOT NULL,
  Played INTEGER NOT NULL,
  Skipped INTEGER NOT NULL,
  CONSTRAINT PK_UserXArtwork PRIMARY KEY(User, Artwork),
  CONSTRAINT FK_UserXArtwork_User FOREIGN KEY(User) REFERENCES User(Id),
  CONSTRAINT FK_UserXArtwork_Artwork FOREIGN KEY(Artwork) REFERENCES Artwork(Id)
)