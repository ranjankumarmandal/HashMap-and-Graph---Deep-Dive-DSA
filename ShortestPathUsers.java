import java.util.*;

public class ShortestPathUsers {

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

        String startUser = "u1";
        String endUser = "u5";

        List<String> path = findShortestPath(graph, startUser, endUser);

        if (path.isEmpty()) {
            System.out.println("No path found between " + startUser + " and " + endUser);
        } else {
            System.out.println("Shortest path from " + startUser + " to " + endUser + ":");
            System.out.println(path);
        }
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

    private static List<String> findShortestPath(Map<String, List<String>> graph, String start, String end) {

        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return Collections.emptyList();
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();

        queue.offer(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(end)) {
                return reconstructPath(parent, end);
            }

            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    private static List<String> reconstructPath(Map<String, String> parent, String end) {

        List<String> path = new ArrayList<>();
        String current = end;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    private static void printGraph(Map<String, List<String>> graph) {

        System.out.println("Adjacency List:");

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            System.out.printf("%-8s -> %s%n", entry.getKey(), entry.getValue());
        }
    }
}
