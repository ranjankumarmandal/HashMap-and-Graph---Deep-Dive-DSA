import java.util.*;

public class FindUserGroupsDFS {

    public static void main(String[] args) {

        Map<String, List<String>> userResources = new HashMap<>();

        userResources.put("u1", Arrays.asList("ip1", "domain1"));
        userResources.put("u2", Arrays.asList("ip1"));
        userResources.put("u3", Arrays.asList("domain1"));
        userResources.put("u4", Arrays.asList("ip2"));
        userResources.put("u5", Arrays.asList("ip2", "domain2"));

        Map<String, List<String>> graph = buildGraph(userResources);

        printGraph(graph);

        System.out.println("\nConnected User Groups:");

        List<Set<String>> groups = findGroups(graph);

        int groupNumber = 1;

        for (Set<String> group : groups) {
            System.out.println(
                    "Group " + groupNumber + " : " + group
            );
            groupNumber++;
        }
    }


    private static Map<String, List<String>> buildGraph(Map<String, List<String>> userResources) {

        Map<String, List<String>> graph = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : userResources.entrySet()) {

            String user = entry.getKey();

            for (String resource : entry.getValue()) {

                graph
                        .computeIfAbsent(user, k -> new ArrayList<>())
                        .add(resource);

                graph
                        .computeIfAbsent(resource, k -> new ArrayList<>())
                        .add(user);
            }
        }

        return graph;
    }


    private static List<Set<String>> findGroups(Map<String, List<String>> graph) {

        Set<String> visited = new HashSet<>();

        List<Set<String>> groups = new ArrayList<>();

        for (String node : graph.keySet()) {

            if (!visited.contains(node)) {

                Set<String> currentGroup = new HashSet<>();

                dfs(node, graph, visited, currentGroup);

                Set<String> usersOnly = new HashSet<>();

                for (String item : currentGroup) {

                    if (item.startsWith("u")) {
                        usersOnly.add(item);
                    }
                }

                groups.add(usersOnly);
            }
        }

        return groups;
    }


    private static void dfs(
            String current,
            Map<String, List<String>> graph,
            Set<String> visited,
            Set<String> currentGroup) {

        visited.add(current);

        currentGroup.add(current);

        for (String neighbor : graph.getOrDefault(
                current,
                Collections.emptyList())) {

            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, currentGroup);
            }
        }
    }


    private static void printGraph(
            Map<String, List<String>> graph) {

        System.out.println("Adjacency List:\n");

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {

            System.out.printf(
                    "%-8s -> %s%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }
}