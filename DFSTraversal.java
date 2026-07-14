import java.util.*;

public class Session2ConnectedComponents {

    public static void main(String[] args) {

        /*
         * Business input
         */
        Map<String, List<String>> userResources = new HashMap<>();

        userResources.put("u1", Arrays.asList("ip1", "domain1"));
        userResources.put("u2", Arrays.asList("ip1"));
        userResources.put("u3", Arrays.asList("domain1"));

        userResources.put("u4", Arrays.asList("ip2"));
        userResources.put("u5", Arrays.asList("ip2", "domain2"));

        userResources.put("u6", Arrays.asList("ip3"));
        userResources.put("u7", Arrays.asList("ip3", "domain3"));

    }
}