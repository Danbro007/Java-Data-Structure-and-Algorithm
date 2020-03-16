package greedyAlgorithm;

import java.util.*;

/**
 * @Classname GreedyAlgorithmDemo
 * @Description TODO 贪心算法
 * @Date 2020/3/15 13:12
 * @Author Danrbo
 */
public class GreedyAlgorithmDemo {

    public static void main(String[] args) {
        //用map存储每个电台的播放范围
        HashMap<String, HashSet<String>> channels = new HashMap<>(16);
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        channels.put("k1", hashSet1);
        channels.put("k2", hashSet2);
        channels.put("k3", hashSet3);
        channels.put("k4", hashSet4);
        channels.put("k5", hashSet5);

        //存储所有地区的hashset
        HashSet<String> allAreas = new HashSet<>();
        channels.values().forEach(v -> allAreas.addAll(v));
        //存放电台的集合
        ArrayList<String> selects = new ArrayList<>();
        String maxLengthKey = null;
        //存放电台对应的区域和allAreas的交集
        HashSet<String> tempSet = new HashSet<>();
        //存放对应的区域最多的电台和allAreas的交集
        HashSet<String> maxSet = new HashSet<>();
        //只要allareas里还有地区，地区还没有覆盖完全
        while (allAreas.size() > 0) {
            //每次遍历前都把maxLengthKey清空
            maxLengthKey = null;
            //遍历每个电台获取每个电台的覆盖地区，然后把每个电台的覆盖地区和
            //全地区列表求出交集，然后取得一个交集最大的电台放入selects里
            for (String key : channels.keySet()) {
                tempSet.clear();
                HashSet<String> areas = channels.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                //如果电台对应的覆盖区域和allAreas的交集不存在并且maxLengthKey没有指向任何电台
                //或者电台覆盖的区域比之前覆盖区域最大的电台还大则maxLengthKey设为当前电台
                if (tempSet.size() > 0 && (maxLengthKey == null || tempSet.size() > maxSet.size())){
                    maxLengthKey = key;
                    maxSet.addAll(tempSet);
                }
            }
            selects.add(maxLengthKey);
            channels.remove(maxLengthKey);
            allAreas.removeAll(maxSet);
        }
        System.out.println(selects);
    }

}
