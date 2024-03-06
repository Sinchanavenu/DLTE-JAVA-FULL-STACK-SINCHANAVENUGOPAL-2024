//Transaction: date_of_transaction, amount_in_transaction, to, remarks(Family, Education, Emergency, Bills, Friend)

perform trigger operation

before when insert new transaction with null or empty remarks assign some valid remarks



create or replace trigger transaction_with_trigger
before insert on transaction for each row
begin
    if :new.transaction_remarks is null then :new.transaction_remarks :='General';
    end if;
end;
insert into transaction values(5,'Sneha','29-feb-2024',2000,null);