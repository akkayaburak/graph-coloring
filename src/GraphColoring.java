import java.util.*;

public class GraphColoring {
    public String girdİ;
    SimpleGraph<String> graph=new SimpleGraph();
    HashMap<String, Integer> komsulukSayilari = new HashMap();
    Map<String, String> boyanmis= new HashMap();
    public GraphColoring(String girdi){
        String[] girdi1 = girdi.split("\n");
        for (String girdi11 : girdi1) {
            graph.addVertex(girdi11.split(" ")[0]);
        }
        for (int i = 0; i < girdi1.length; i++) {
            String[] sutunGirdi=girdi1[i].split(" ");
            komsulukSayilari.put(sutunGirdi[0], sutunGirdi.length-1);
            for (int j = 1; j < sutunGirdi.length; j++) {
                graph.addEdge(sutunGirdi[0],sutunGirdi[j]);
            }
        }
    }
    public LinkedHashMap<String, Integer> sortHashMapByValues(HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.reverse(mapValues);
        Collections.sort(mapKeys);
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();
            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;
                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
    public boolean komsusuBuRenkMi(String vertexVal, String renk){
        for (Edge e: graph.verticesMap.get(vertexVal).edges) {
            if (boyanmis.get(e.to.value)!=null && boyanmis.get(e.to.value).equals(renk)) {
                return true;
            }
        }
        return false;
    }
    public Map<String, String> paint(String[] renkler) {
        int renkIndex=0;
        LinkedHashMap sorted=sortHashMapByValues(komsulukSayilari);
        List<String> sirali=new ArrayList();
        Iterator it = sorted.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            sirali.add(pair.getKey().toString());
            it.remove();
        }
        boolean flag=false;
        while(!sirali.isEmpty()){
            for (int i=0;i<sirali.size();i++) {
                //System.out.println(sirali.get(i));
                if (!komsusuBuRenkMi(sirali.get(i),renkler[renkIndex])) {
                    boyanmis.put(sirali.get(i), renkler[renkIndex]);
                    sirali.remove(i);
                    i--;
                    flag=true;
                }
            }
            if (flag) {
                renkIndex++;
                flag=false;
            }
        }
        graph.print();
        System.out.println(Arrays.asList(boyanmis));
        return boyanmis;
    }
}

