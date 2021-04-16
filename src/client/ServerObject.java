import java.io.Serializable;

public class ServerObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String commandName;
    private String[] commandArgs;

    public ServerObject(String cn, String ... args){
        commandArgs=args;
        commandName=cn;
    }
}
