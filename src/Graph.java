import java.util.ArrayList;

public class Graph {

    int vertexNum = 1; // количество вершин
    //    int edgeNum = 0; // количество ребер
    boolean[][] adjMatrix; // матрица смежности
    boolean[] used; // массив пометок
    int start;
    int finish;
    boolean flag = false;
    ArrayList<Integer> way = new ArrayList<Integer>();

    public Graph(int vNum, boolean[][] adjM, int[] way){
        vertexNum = vNum;
        adjMatrix = adjM;
        used = new boolean[vNum];
        start = way[0];
        finish = way[1];
    }

    ArrayList dfs(int v) {
        used[v - 1] = true; // помечаем вершину
        if (!flag){
            way.add(v);
        }
        if (v == finish){
            flag = true;
        }
        for (int nv = 1; nv <= vertexNum; nv++) { // перебираем вершины
            if (!used[nv-1] && adjMatrix[v-1][nv-1]) { // если вершина не помечена, и смежна с текущей
                dfs(nv);
            }
        }
        return way;
    }

    ArrayList bfs(int v) {
        boolean[] used = new boolean [vertexNum]; // массив пометок
        int[] queue = new int [vertexNum]; // очередь
        int qH = 0; // голова очереди
        int qT = 0; // хвост
        /* <обработка вершины v> */
        used[v - 1] = true; // помечаем исходную вершину
        //way.add(v);
        queue[qT++] = v; // помещаем ее в очередь
        while (qH < qT) { // пока очередь не пуста
            v = queue[qH++]; // извлекаем текущую вершину
            way.add(v);
            if (v == finish) return way;
            for (int nv = 1; nv <= vertexNum; nv++) { // перебираем вершины
                if (!used[nv - 1] && adjMatrix[v - 1][nv - 1]) { // если nv не помечена и смежна с v
                    /* <обработка вершины nv> */
                    used[nv - 1] = true; // помечаем ее
                    queue[qT++] = nv; // и добавляем в очередь
                }
            }
        }
        return way;
    }
}