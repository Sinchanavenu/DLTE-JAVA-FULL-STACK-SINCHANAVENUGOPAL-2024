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

//Create view's to perform all these analysis

Filter based on given ranges of date
least amount transferred
maximum amount transferred
number of transaction made to particular beneficiary
filter based on particular remarks

UPDATED CODE WITH VIEWS

select * from transaction;
create view range_date as select * from transaction where transaction_date between '08-Feb-2024' and '19-Feb-2024';
select * from range_date;

create view min_transaction as select min(transaction_amount) as min_amount from transaction;
select * min_transaction;

create view max_transaction as select max(transaction_amount) as max_amount from transaction;
select * max_transaction;

create view transaction_count as select count(transaction_to) as transaction_to from transaction where transaction_to='Zoya';
select * from transaction_count;

create view remarks as select * from transaction where transaction_remarks='Emergency';
select * from remarks;