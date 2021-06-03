package erp.client.javafx.container;

import org.apache.log4j.Logger;

import java.util.HashMap;

public class Arguments {

    private static final Logger logger = Logger.getLogger(Arguments.class);

    private HashMap<String, Object> arguments;

    public Arguments() {
        arguments = new HashMap<>();
    }

    public void setArgument(String key, Object obj) {
        arguments.put(key, obj);
    }

    public <T> T getArgument(String key, Class<T> objectType) {
        if(arguments.containsKey(key)){
            return (T) arguments.get(key);
        }else{
            logger.error("There is no object found for key '" + key + "' in arguments");
            return null;
        }
    }

    public HashMap<String, Object> getArguments() {
        return arguments;
    }
}
