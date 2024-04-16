CREATE OR REPLACE PROCEDURE update_customer(
    p_customer_id NUMBER,
    inp_customer_name VARCHAR2,
    inp_customer_address VARCHAR2,
    inp_customer_status varchar2,
    inp_customer_contact NUMBER,
    inp_password varchar2,
    p_customer_name OUT varchar2,
    p_customer_address OUT varchar2,
	p_customer_status OUT VARCHAR2,
    p_customer_contact OUT NUMBER,
    p_username OUT varchar2,
    p_password OUT varchar2,
    p_result OUT VARCHAR2
)
AS
    v_customer_id NUMBER;
    v_customer_status VARCHAR2(255);
BEGIN

    -- Check if the customer exists
    SELECT CUSTOMER_ID INTO v_customer_id FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = p_customer_id;
--    IF v_customer_id IS NULL THEN
--        p_result := 'SQL102';
--        RETURN;
--    END IF;
 
    -- Check if the customer is active
    SELECT CUSTOMER_STATUS INTO v_customer_status FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = p_customer_id;
    IF v_customer_status <> 'Active' THEN
        p_result := 'SQL101';
        RETURN;
    END IF;
   
   -- Update the customer 
    UPDATE MYBANK_APP_CUSTOMER
    SET CUSTOMER_NAME =inp_customer_name,
        CUSTOMER_ADDRESS = inp_customer_address,
		CUSTOMER_STATUS = inp_customer_status,
        CUSTOMER_CONTACT = inp_customer_contact,
        PASSWORD = inp_password
    WHERE CUSTOMER_ID = p_customer_id;
    
    SELECT CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD
    INTO p_customer_name, p_customer_address, p_customer_status, p_customer_contact, p_username, p_password
    FROM MYBANK_APP_CUSTOMER
    WHERE CUSTOMER_ID = p_customer_id;
 
 
    -- Set the result message
    p_result := 'SQL100';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_result := 'SQL102';
    WHEN OTHERS THEN
        p_result := 'SQL104 ' || SQLERRM;
END;



variable var1 NUMBER;
variable var2 varchar2; 
variable var3 varchar2;
variable var4 varchar2;
variable var5 number;
variable var6 varchar2;
variable var7 varchar2;
variable var8 varchar2;
 execute UPDATE_CUSTOMER(3,'JOE','Karkala','Active',8789765432,'rakesh',:var2,:var3,:var4,:var1,:var6,:var7,:var8);
 
 print var8;