import java.util.*;

public class BuildGraphFromInput {

    public static void main(String[] args) {

        /*
         * Input data coming from database/API/etc.
         *
         * user -> list of resources used by user
         */
        Map<String, List<String>> userResources = new HashMap<>();

        userResources.put("u1", Arrays.asList("ip1", "domain1"));
        userResources.put("u2", Arrays.asList("ip1"));
        userResources.put("u3", Arrays.asList("domain1"));
        userResources.put("u4", Arrays.asList("ip2"));
        userResources.put("u5", Arrays.asList("ip2", "domain2"));

        /*
         * Build graph
         */
        Map<String, List<String>> graph = buildGraph(userResources);

        /*
         * Print graph
         */
        printGraph(graph);
    }

    private static Map<String, List<String>> buildGraph(
            Map<String, List<String>> userResources) {

    }
}