create table Recipient(
  id    UUID not null primary key,
  iban  text not null unique,
  name  text not null,
  currency text not null
);