package basic.service.FilesImplementation;
/*
Interface "Activity" : abstract Create

Class "MyBankDatabase": ArrayList of CreditCard

implement Activity to MyBankDatabase

MAIN :

Create CreditCard object write in to the file as soon as object inserted into arraylist as append mode. when you execute Create method
 */

public interface Activity<T> {
    String createNewData(T object);
}
