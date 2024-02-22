create table Transaction(transaction_id number primary key,transaction_date date not null, transaction_to varchar(255) not null, transaction_amount number not null, transaction_remarks varchar(255) not null);

insert into Transaction values(1,'18-Feb-2024','Sinchana','5000','Friend');
insert into Transaction values(2,'19-Feb-2024','Sahana','2500','Family');
insert into Transaction values(3,'08-Feb-2024','Sherly','8000','Emergency');
insert into Transaction values(4,'20-Feb-2024','Zoya','3000','Friend');
insert into Transaction values(5,'11-Feb-2024','Duke','1000','Education');

select * from Transaction where transaction_date between '08-Feb-2024' and '20-Feb-2024';
select min(transaction_amount) from Transaction;
select max(transaction_amount) from Transaction;
select count(transaction_to) from Transaction where transaction_to='Sahana';
select * from Transaction where transaction_remarks='Friend';