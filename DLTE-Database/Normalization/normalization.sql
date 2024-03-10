//create table mobile banking
create table mobile_banking(username varchar(255) not null, upi varchar(255) not null, mobile_number number(10) not null, email varchar(255) not null, wallet_type varchar(255) not null, recharged_date date not null, recharge_provider varchar(255), recharged_to varchar(255) not null, recharged_amount number(10) not null);

//create table for user details
create table user_details(username varchar(255) not null, upi varchar(255) not null, mobile_number number(10) primary key not null, email varchar(255) not null);

//create table wallet details
create table wallter_details(wallet_id number(10) primary key not null, wallet_type varchar(255) not null);

//create table user wallet
create table user_wallet(mobile_number number(10) not null, wallet_id number(10) not null, primary key(mobile_number,wallet_id));

//add foreign key
alter table user_wallet add foreign key(mobile_number) references user_details(mobile_number);
alter table user_wallet add foreign key(wallet_id) references wallter_details(wallet_id);

//create table recharge
create table recharge(recharge_id number(10) primary key not null, recharged_date date not null,recharge_provider varchar(255) not null, recharged_to varchar(255) not null, recharged_amount number(10) not null, mobile_number number(10) not null);
alter table recharge add foreign key(mobile_number) references user_details(mobile_number);

