create sequence CUSTOMERID_SEQ start with 1 increment by 1;
create table MYBANK_APP_CUSTOMER(
CUSTOMER_ID NUMBER(10),
CUSTOMER_NAME VARCHAR2(255) UNIQUE NOT NULL,
CUSTOMER_ADDRESS VARCHAR2(255) NOT NULL,
CUSTOMER_STATUS VARCHAR2(255) NOT NULL,
CUSTOMER_CONTACT NUMBER(10) NOT NULL,
USERNAME VARCHAR2(255) NOT NULL,
PASSWORD VARCHAR2(255) NOT NULL
);
 
alter table MYBANK_APP_CUSTOMER add constraint CUSTOMERID_SEQ primary key(CUSTOMER_ID);

-----------------------------------------------------------------------------------------------------------------
create sequence ACCOUNTID_SEQ start with 100 increment by 1;
create sequence ACCOUNTNUMBER_SEQ start with 100000000000 increment by 1;
create table MYBANK_APP_ACCOUNT(
ACCOUNT_ID NUMBER,
CUSTOMER_ID NUMBER(10),
ACCOUNT_TYPE VARCHAR2(255) NOT NULL,
ACCOUNT_NUMBER NUMBER(20),
ACCOUNT_STATUS VARCHAR2(255) NOT NULL,
foreign key (CUSTOMER_ID) references MYBANK_APP_CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE
);
alter table MYBANK_APP_ACCOUNT add constraint ACCOUNTID_SEQ primary key(ACCOUNT_ID);

---------------------------------
create sequence KYCNUMBER_SEQ start with 232320 increment by 1;

create table MYBANK_APP_KYC(
KYC_NUMBER NUMBER(10),
CUSTOMER_ID NUMBER(10),
KYC_PAN VARCHAR2(255) NOT NULL,
KYC_AADHAR VARCHAR2(12) NOT NULL,
KYC_STATUS VARCHAR2(255) NOT NULL,
foreign key (CUSTOMER_ID) references MYBANK_APP_CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE
);
alter table MYBANK_APP_KYC add constraint KYCNUMBER_SEQ primary key(KYC_NUMBER);

--------------------------------------------------------------------------------------------

create sequence PAYEEID_SEQ start with 50 increment by 1;

create table MYBANK_APP_PAYEE(
PAYEE_ID NUMBER(10),
PAYEE_NAME VARCHAR2(255) NOT NULL,
CUSTOMER_ID NUMBER(10),
ACCOUNT_ID NUMBER(20),
foreign key (CUSTOMER_ID) references MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE,
foreign key (ACCOUNT_ID) references MYBANK_APP_ACCOUNT (ACCOUNT_ID) ON DELETE CASCADE
);
alter table MYBANK_APP_PAYEE add constraint PAYEEID_SEQ primary key (PAYEE_ID);

----------------------------------------------------------------------------------------------

create sequence TRANSACTIONID_SEQ start with 1000 increment by 1;
 
create table MYBANK_APP_TRANSACTION(
TRANSACTION_ID NUMBER(20),
ACCOUNT_ID NUMBER(20),
TRANSACTION_TYPE VARCHAR2(255) NOT NULL,
TRANSACTION_FROM VARCHAR2(255) NOT NULL,
TRANSACTION_TO VARCHAR2(255) NOT NULL,
TRANSACTION_DATE DATE NOT NULL,
TRANSACTION_AMOUNT NUMBER(30) NOT NULL,
TRANSACTION_STATUS VARCHAR2(255) NOT NULL,
FOREIGN KEY (ACCOUNT_ID) REFERENCES MYBANK_APP_ACCOUNT(ACCOUNT_ID) ON DELETE CASCADE);
 
alter table MYBANK_APP_TRANSACTION add constraint TRANSACTIONID_SEQ primary key(TRANSACTION_ID);
---------------------
 
create sequence LOANID_SEQ start with 1500 increment by 1;
 
create table MYBANK_APP_LOAN_AVAILABLE(
LOAN_ID NUMBER(20),
LOAN_TYPE VARCHAR2(255) NOT NULL,
LOAN_NAME VARCHAR2(255) NOT NULL,
LOAN_DESCRIPTION VARCHAR2(255) NOT NULL,
LOAN_ROI NUMBER(10,2) NOT NULL);
 
ALTER TABLE MYBANK_APP_LOAN_AVAILABLE ADD CONSTRAINT LOANID_SEQ PRIMARY KEY(LOAN_ID);
--------------------
CREATE SEQUENCE LOANAPPID_SEQ START WITH 2000 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_LOAN_AVAILED(
CUSTOMER_ID NUMBER(10),
LOAN_ID NUMBER(20),
LOANAPP_ID NUMBER(20),
LOAN_AMOUNT NUMBER(20) NOT NULL,
LOAN_EMI NUMBER(10,2) NOT NULL,
LOAN_TENURE NUMBER(10) NOT NULL,
FOREIGN KEY(LOAN_ID) REFERENCES MYBANK_APP_LOAN_AVAILABLE(LOAN_ID) ON DELETE CASCADE,
FOREIGN KEY(CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE);
 
ALTER TABLE MYBANK_APP_LOAN_AVAILED ADD CONSTRAINT LOANAPPID_SEQ PRIMARY KEY(LOANAPP_ID);
---------------------
>>>>>>>>>>>>>>
CREATE SEQUENCE DEBITNUMBER_SEQ START WITH 1111111111 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_DEBITCARD(
ACCOUNT_ID NUMBER(20),
CUSTOMER_ID NUMBER(10),
DEBITCARD_NUMBER NUMBER(10),
DEBITCARD_CVV NUMBER(5) NOT NULL,
DEBITCARD_PIN NUMBER(5) UNIQUE NOT NULL,
DEBITCARD_EXPIRY DATE NOT NULL,
DEBITCARD_STATUS VARCHAR2(255) NOT NULL,
DEBITCARD_DOMESTIC_LIMIT NUMBER(10) NOT NULL,
DEBITCARD_INTERNATIONAL_LIMIT NUMBER(10) NOT NULL,
FOREIGN KEY (ACCOUNT_ID) REFERENCES MYBANK_APP_ACCOUNT(ACCOUNT_ID) ON DELETE CASCADE,
FOREIGN KEY(CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE);
 
ALTER TABLE MYBANK_APP_DEBITCARD ADD CONSTRAINT DEBITNUMBER_SEQ PRIMARY KEY(DEBITCARD_NUMBER);
---------------
 
CREATE SEQUENCE INSURANCEID_SEQ START WITH 20000 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_INSURANCE_AVAILABLE(
INSURANCE_ID NUMBER(10),
INSURANCE_TYPE VARCHAR2(255) NOT NULL,
INSURANCE_NAME VARCHAR2(255) NOT NULL,
INSURANCE_KEY_BENEFITS VARCHAR2(255) NOT NULL,
INSURANCE_LIFETIME NUMBER(5) NOT NULL
);
 
ALTER TABLE MYBANK_APP_INSURANCE_AVAILABLE ADD CONSTRAINT INSURANCEID_SEQ PRIMARY KEY(INSURANCE_ID);
------------------
 
CREATE SEQUENCE INSURANCEAVAILEDID_SEQ START WITH 30000 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_INSURANCE_AVAILED(
CUSTOMER_ID NUMBER(10),
INSURANCE_ID NUMBER(10),
INSURANCE_AVAILED_ID NUMBER(10),
INSURANCE_TYPE VARCHAR2(255) NOT NULL,
INSURANCE_NAME VARCHAR2(255) NOT NULL,
INSURANCE_KEY_BENEFITS VARCHAR2(255) NOT NULL,
INSURANCE_COVERAGE NUMBER(10) NOT NULL,
INSURANCE_LIFETIME NUMBER(5) NOT NULL,
INSURANCE_PREMIUM NUMBER(5) NOT NULL,
FOREIGN KEY(INSURANCE_ID) REFERENCES MYBANK_APP_INSURANCE_AVAILABLE(INSURANCE_ID) ON DELETE CASCADE,
FOREIGN KEY(CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE);
 
ALTER TABLE MYBANK_APP_INSURANCE_AVAILED ADD CONSTRAINT INSURANCEAVAILEDID_SEQ PRIMARY KEY(INSURANCE_AVAILED_ID);
 
-----------------
 
CREATE SEQUENCE DEPOSITEID_SEQ START WITH 40000 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_DEPOSITS_AVAILABLE(
DEPOSITE_ID NUMBER(10),
DEPOSITE_NAME VARCHAR2(255) NOT NULL,
DEPOSITE_ROI NUMBER(10,2) NOT NULL,
DEPOSITE_TYPE VARCHAR2(255) NOT NULL,
DEPOSITE_DESCRIPTION VARCHAR2(255) NOT NULL);
 
ALTER TABLE MYBANK_APP_DEPOSITS_AVAILABLE ADD CONSTRAINT DEPOSITEID_SEQ PRIMARY KEY(DEPOSITE_ID);
------------------
CREATE SEQUENCE DEPOSITEAVAILID_SEQ START WITH 50000 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_DEPOSITS_AVAILED(
CUSTOMER_ID NUMBER(10),
DEPOSITE_ID NUMBER(10),
DEPOSITE_AVAIL_ID NUMBER(10),
DEPOSITE_NAME VARCHAR2(255) NOT NULL,
DEPOSITE_ROI NUMBER(10,2) NOT NULL,
DEPOSITED_AMOUNT NUMBER(10) NOT NULL,
DEPOSITED_DURATION NUMBER(5) NOT NULL,
DEPOSIT_MATURITY NUMBER(5) NOT NULL,
FOREIGN KEY(DEPOSITE_ID) REFERENCES MYBANK_APP_DEPOSITS_AVAILABLE(DEPOSITE_ID) ON DELETE CASCADE,
FOREIGN KEY(CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE);
 
ALTER TABLE MYBANK_APP_DEPOSITS_AVAILED ADD CONSTRAINT DEPOSITEAVAILID_SEQ PRIMARY KEY(DEPOSITE_AVAIL_ID);