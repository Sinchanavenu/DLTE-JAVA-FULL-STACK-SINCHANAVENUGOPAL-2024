package initial;

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
        defaulters= (List<String>) Stream.of("Annapoorna","Medhini","Arundhathi","Akash","Prashanth");
    }
    public String addDefaulters(String name){
        defaulters.add(name);
        return name+" has added to defaulters record";
    }
}
