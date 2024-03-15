package basic.service;

import basic.service.middleware.DatabaseTarget;
import basic.service.remotes.StorageTarget;
import basic.service.services.UserDetailsServices;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
          StorageTarget storageTarget=new DatabaseTarget();
          UserDetailsServices userDetailsServices=new UserDetailsServices(storageTarget);
       // DatabaseTarget databaseTarget=new DatabaseTarget();
    }
}
