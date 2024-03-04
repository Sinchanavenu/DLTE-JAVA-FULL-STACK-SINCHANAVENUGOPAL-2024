package basic.service.Generics;
/*
Generic Interface "Activity" : Generic abstract CRUD methods

Generic Class "MyBankDatabase": Generic Array of Objects

implement Activity to MyBankDatabase

MAIN :

Create instance to Generic MyBankDatabse to store CreditCard object and perform CRUD
Create instance to Generic MyBankDatabase to store Transaction object and perform CRUD
 */


import java.util.ArrayList;
import java.util.List;

public class MyBankDatabase<T> implements Activity<T> {
    private List<T> data;

    public MyBankDatabase() {
        this.data= new ArrayList<>();
    }

    @Override
    public T insertNewRecord(T object) {
        if (object != null)
            data.add(object);
            return object;

//        return object;
    }

    @Override
    public T read(int index) throws NullPointerException {
        if(data!= null && index>= 0 && index<data.size()){
            return data.get(index);
        }
        return null;
    }

    @Override
    public void update(int index, T object) {
        if(data!=null && index>=0 && index< data.size()) {
            data.size();
        }
    }


    @Override
    public void delete(int index) throws NullPointerException {
        if(data!=null && index>=0 && index<data.size()){
            data.remove(index);
        }
    }

}
