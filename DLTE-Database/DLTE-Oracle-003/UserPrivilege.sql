//Create atleast 5 users and grant following privileges

user1 - select

user2 - delete

user3 - select

user4 - insert

user5 - update


create user Sinchana identified by sinchana;
grant select on transaction to Sinchana;
connect Sinchana/sinchana;
select * from transaction;

create user Sahana identified by sahana;
grant delete on transaction to Sahana;
connect Sahana/sahana;
delete from transaction transaction_remarks='Family';

create user Ninadha identified by ninadha;
grant select on transaction to Ninadha;
connect Ninadha/ninadha;
select * from transaction;

create user Venu identified by venu;
grant insert on transaction to Venu;
connect Venu/venu;
insert into transaction values(6,'03March2024','Sherly',1000, 'Bills');

create user Zuni identified by zuni;
grant update on transaction to Zuni;
connect Zuni/zuni;
update transaction set transaction_to='Ramesh' where transaction_id=3;