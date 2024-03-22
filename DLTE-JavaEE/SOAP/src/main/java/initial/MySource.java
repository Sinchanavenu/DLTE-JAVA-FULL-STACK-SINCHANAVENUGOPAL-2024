package initial;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class MySource {
    List<String> defaulters;

    public MySource() {
        defaulters= (List<String>) Stream.of("Ramesh","Suresh","Subeen","Ashvitha","Raj");
    }

    @WebMethod
    public String addDefaulter(String name){
        defaulters.add(name);
        return name+" has added to defaulters record";
    }
}
