import java.util.*;

public class ConnectedUsersBFS {

    public static void main(String[] args) {

        Map<String, List<String>> userResources = new HashMap<>();

        userResources.put("u1", Arrays.asList("ip1", "domain1"));
        userResources.put("u2", Arrays.asList("ip1"));
        userResources.put("u3", Arrays.asList("domain1"));
        userResources.put("u4", Arrays.asList("ip2"));
        userResources.put("u5", Arrays.asList("ip2", "domain2"));

        Map<String, List<String>> graph = buildGraph(userResources);

        printGraph(graph);

        System.out.println();
    }
}