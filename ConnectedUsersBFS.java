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

        Set<String> connectedUsers = findConnectedUsers(graph, "u1");

        System.out.println("Connected users: " + connectedUsers);
    }

    private static Map<String, List<String>> buildGraph(Map<String, List<String>> userResources) {

        Map<String, List<String>> graph = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : userResources.entrySet()) {

            String user = entry.getKey();
            List<String> resources = entry.getValue();

            for (String resource : resources) {

                graph.computeIfAbsent(user, k -> new ArrayList<>()).add(resource);
                graph.computeIfAbsent(resource, k -> new ArrayList<>()).add(user);
            }
        }

        return graph;
    }

    private static Set<String> findConnectedUsers(Map<String, List<String>> graph, String startUser) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> connectedUsers = new HashSet<>();

        queue.offer(startUser);
        visited.add(startUser);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.startsWith("u")) {
                connectedUsers.add(current);
            }

            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return connectedUsers;
    }
}