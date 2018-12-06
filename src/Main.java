import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int type = 0;
        int vertexNum =1;
        int edgeNum = 0;
        int[][] rowsV;
        int[][] rowsE;
        boolean[][] adjMatrix;
        int[] way = new int[2];

        ArrayList<String> fileGraph = new ArrayList<String>();
        ArrayList<Integer> wayList = new ArrayList<Integer>();

        try {
            File file = new File(args[1]);
            FileReader fr = new FileReader(file); //создаем объект FileReader для объекта File
            BufferedReader reader = new BufferedReader(fr); //создаем BufferedReader с существующего FileReader для построчного считывания
            String line = reader.readLine();
            while (line != null) {
                fileGraph.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        type = Integer.valueOf(fileGraph.get(0));
        vertexNum = Arrays.stream(fileGraph.get(1).split(" ")).mapToInt(Integer::parseInt).toArray().length;

        adjMatrix = new boolean[vertexNum][vertexNum]; // создаём матрицу смежности
        if (type == 0){
            rowsV = new int[vertexNum][vertexNum-1];
            for (int i = 0; i < vertexNum; i++){
                rowsV[i] = Arrays.stream(fileGraph.get(i+2).split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < vertexNum; j++){
                    if (rowsV[i].length > j) {
                        adjMatrix[i][rowsV[i][j] - 1] = true;
                    }
                }
            }
        }
        else if (type == 1){
            edgeNum = Integer.valueOf(fileGraph.get(2));
            rowsE = new int[edgeNum][2];
            for (int i = 0; i < edgeNum; i++){
                rowsE[i] = Arrays.stream(fileGraph.get(i+3).split(" ")).mapToInt(Integer::parseInt).toArray();
                adjMatrix[rowsE[i][0]-1][rowsE[i][1]-1] = true;
            }
        }
        else System.out.println("Invalid list!");

        way = Arrays.stream(fileGraph.get(fileGraph.size()-1).split(" ")).mapToInt(Integer::parseInt).toArray();
        Graph graph = new Graph(vertexNum, adjMatrix, way);
        switch (args[0]) {
            case "bfs":
                wayList = graph.bfs(way[0]);
                if (wayList.get(0) == way[0]
                        && wayList.get(wayList.size()-1) == way[1]){
                    for (int i = 0; i < wayList.size(); i++){
                        System.out.print(wayList.get(i) + " ");
                    }
                }
                else System.out.println("Path not found!");
                break;
            case "dfs":
                wayList = graph.dfs(way[0]);
                //System.out.println(wayList);
                if (wayList.get(0) == way[0]
                        && wayList.get(wayList.size()-1) == way[1]){
                    for (int i = 0; i < wayList.size(); i++){
                        System.out.print(wayList.get(i) + " ");
                    }
                }
                else System.out.println("Path not found!");
                break;
            default:
                System.out.println("Invalid algorithm name! Use bfs or dfs.");
                break;
        }


    }
}
