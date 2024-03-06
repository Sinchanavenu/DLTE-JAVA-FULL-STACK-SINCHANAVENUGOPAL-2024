//create
create or replace procedure insert_new_transaction(
trans_id number,
trans_to varchar2,
trans_date date,
trans_amt number,
trans_remarks varchar2,
trans_info out varchar2
)
as
begin
insert into transaction(transaction_id, transaction_to,transaction_date,transaction_amount,transaction_remarks)
values(trans_id,trans_to,trans_date,trans_amt,trans_remarks);
trans_info:='new transaction inserted';
exception
when others then
trans_info:='Insertion failed due to '|| SQLERRM;
end;

variable err_or_info varchar2;
execute insert_new_transaction(1,'Sinchana','25-feb-2024',2500,'Family',:err_or_info);
print err_or_info;


//delete transaction

create or replace procedure delete_transaction (transactionId number, transaction_info out varchar2)
as
begin
delete from transaction where transaction_id = transactionId;
transaction_info := 'Transaction deleted';
exception
    when no_data_found then
    transaction_info := 'No transaction matched';
    when others then
    transaction_info := 'Error due to' || SQLERRM;
end;

variable info_err varchar2(255);
execute delete_transaction(1,:info_err);
print info_err;


//filter
create or replace procedure select_transaction(
remarks varchar2, amount out number, transactionTo out varchar2, trans_info out varchar2)
as
Begin
    select transaction_to, transaction_amount into transactionTo, amount from transaction where transaction_remarks = remarks;
    trans_info := 'Required data to be selected';
exception
    when no_data_found then
    trans_info := 'No such transaction';
    when others then
    transa_info := 'Error due to' || SQLERRM;
end;
execute select_transaction('Education',:found_amount,:found_to,:info_err);
print found_amount;
print found_to;
print info_err;
select * from transaction;