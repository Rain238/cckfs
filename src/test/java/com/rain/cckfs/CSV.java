package com.rain.cckfs;

import com.csvreader.CsvReader;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Author: LightRain
 * @Description:
 * @DateTime: 2023-02-08 17:07
 * @Version：1.0
 **/
public class CSV {

    /**
     * CsvReader 读取
     *
     * @param filePath
     * @return
     */
    public static ArrayList<BillBean> readCsvByCsvReader(String filePath) {
        ArrayList<BillBean> arrList = new ArrayList<>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', StandardCharsets.UTF_8);
            int i = 0;
            while (reader.readRecord()) {
                if (i == 0) {
                    i++;
                    continue;
                }
                String[] values = reader.getValues();
                BillBean csv = new BillBean();
                String[] split = values[0].split("\\.");
                String y = null;
                String r = null;
                if (split[1].length() == 1) {
                    y = 0 + split[1];
                } else {
                    y = split[1];
                }
                if (split[2].length() == 1) {
                    r = 0 + split[2];
                } else {
                    r = split[2];
                }
                String s1 = split[0] + "-" + y + "-" + r;
                csv.set日期(s1);
                csv.set实收(new BigDecimal(values[1]));
                if (!values[2].isBlank()) {
                    csv.set旧会员((new BigDecimal(values[2])));
                } else {
                    csv.set旧会员(BigDecimal.valueOf(0));
                }
                if (!values[3].isBlank()) {
                    csv.set新会员((new BigDecimal(values[3])));
                } else {
                    csv.set新会员(BigDecimal.valueOf(0));
                }
                if (!values[4].isBlank()) {
                    String s = values[4].replaceAll("-", "");
                    csv.set进货佳禾((new BigDecimal(s)));
                } else {
                    csv.set进货佳禾((new BigDecimal(0)));
                }
                if (!values[5].isBlank()) {
                    String s = values[5].replaceAll("-", "");
                    csv.set进货众客((new BigDecimal(s)));
                } else {
                    csv.set进货众客((new BigDecimal(0)));
                }
                if (!values[6].isBlank()) {
                    String s = values[6].replaceAll("-", "");
                    csv.set詹贝配货((new BigDecimal(s)));
                } else {
                    csv.set詹贝配货((new BigDecimal(0)));
                }
                if (!values[7].isBlank()) {
                    String s = values[7].replaceAll("-", "");
                    csv.set进货华顺((new BigDecimal(s)));
                } else {
                    csv.set进货华顺((new BigDecimal(0)));
                }
                if (!values[8].isBlank()) {
                    String s = values[8].replaceAll("-", "");
                    csv.set进货瑞茂((new BigDecimal(s)));
                } else {
                    csv.set进货瑞茂((new BigDecimal(0)));
                }
                if (!values[9].isBlank()) {
                    String s = values[9].replaceAll("-", "");
                    csv.set姑姑带货((new BigDecimal(s)));
                } else {
                    csv.set姑姑带货((new BigDecimal(0)));
                }
                // 按行读取，并把每一行的数据添加到list集合
                arrList.add(csv);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrList;
    }

    public static ArrayList<MemberRecharge> readCsvByCsvReader2(String filePath) {
        ArrayList<MemberRecharge> arrList = new ArrayList<>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', StandardCharsets.UTF_8);
            int i = 0;
            while (reader.readRecord()) {
                if (i == 0) {
                    i++;
                    continue;
                }
                String[] values = reader.getValues();
                MemberRecharge mr = new MemberRecharge();
                String[] split = values[0].split("\\.");
                String y = null;
                String r = null;
                if (split[1].length() == 1) {
                    y = 0 + split[1];
                } else {
                    y = split[1];
                }
                if (split[2].length() == 1) {
                    r = 0 + split[2];
                } else {
                    r = split[2];
                }
                String s1 = split[0] + "-" + y + "-" + r;
                mr.set日期(s1);
                mr.set卡号(Integer.parseInt(values[1]));
                mr.set姓名(values[2]);
                mr.set金额(new BigDecimal(values[3]));
                mr.set手机号(values[4]);
                mr.set备注(values[5]);
                // 按行读取，并把每一行的数据添加到list集合
                arrList.add(mr);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrList;
    }

    public static Map<String, Object> readCsvByCsvReaderMap(String filePath) {
        Map<String, Object> map = new HashMap<>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', StandardCharsets.UTF_8);
            int i = 0;
            while (reader.readRecord()) {
                if (i == 0) {
                    i++;
                    continue;
                }
                String[] values = reader.getValues();
                MemberRecharge mr = new MemberRecharge();
                String[] split = values[0].split("\\.");
                String y = null;
                String r = null;
                if (split[1].length() == 1) {
                    y = 0 + split[1];
                } else {
                    y = split[1];
                }
                if (split[2].length() == 1) {
                    r = 0 + split[2];
                } else {
                    r = split[2];
                }
                String s1 = split[0] + "-" + y + "-" + r;
                if (map.containsKey(s1)) {
                    BigDecimal big = (BigDecimal) map.get(s1);
                    BigDecimal add = big.add(new BigDecimal(values[3]));
                    map.put(s1,add);
                } else {
                    map.put(s1,new BigDecimal(values[3]));
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String, ArrayList<BillBean>> csvMap = new TreeMap<>();
        ArrayList<BillBean> XlsxBeans = readCsvByCsvReader("C:\\Users\\LightRain\\Desktop\\XLSX\\日收账单.CSV");
        for (BillBean cvs : XlsxBeans) {
            System.out.println("cvs.toString() = " + cvs.toString());
        }
    }

    public static LinkedHashMap<String, Integer> sortMap2(Map<String, Integer> map) {
        class MapClass {                                                            //自定义类保存键值对
            private String key;
            private int value;

            public MapClass(String key, int value) {
                super();
                this.key = key;
                this.value = value;
            }

            public String getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }


        }
        class MapSortMethod implements Comparator<MapClass> {                    //为自定义类实现排序方法
            @Override
            public int compare(MapClass o1, MapClass o2) {
                int result = Integer.compare(o1.getValue(), o2.getValue());        //按值大小升序排列
                //int result = Integer.compare(o2.getValue(), o1.getValue());	//按值大小降序排列
                if (result != 0)
                    return result;
                return o1.getKey().compareTo(o2.getKey());                        //值相同时按键字典顺序排列
            }
        }

        ArrayList<MapClass> mapclass = new ArrayList<MapClass>();                //以ArrayList保存自定义类
        for (String k : map.keySet())
            mapclass.add(new MapClass(k, map.get(k)));
        Collections.sort(mapclass, new MapSortMethod());                        //使用Collections.sort()方法，第二个参数为自定义排序方法，需要实现Comparator接口

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (MapClass m : mapclass)
            sortedMap.put(m.getKey(), m.getValue());
        return sortedMap;                                                        //用LinkedHashMap返回排好序的Map
    }

    public static LinkedHashMap<String, Double> sortMap(Map<String, Double> map) {
        class MapClass {                                                            //自定义类保存键值对
            private String key;
            private double value;

            public MapClass(String key, Double value) {
                super();
                this.key = key;
                this.value = value;
            }

            public String getKey() {
                return key;
            }

            public double getValue() {
                return value;
            }


        }
        class MapSortMethod implements Comparator<MapClass> {                    //为自定义类实现排序方法
            @Override
            public int compare(MapClass o1, MapClass o2) {
                int result = Double.compare(o1.getValue(), o2.getValue());        //按值大小升序排列
                //int result = Integer.compare(o2.getValue(), o1.getValue());	//按值大小降序排列
                if (result != 0)
                    return result;
                return o1.getKey().compareTo(o2.getKey());                        //值相同时按键字典顺序排列
            }
        }

        ArrayList<MapClass> mapclass = new ArrayList<MapClass>();                //以ArrayList保存自定义类
        for (String k : map.keySet())
            mapclass.add(new MapClass(k, map.get(k)));
        Collections.sort(mapclass, new MapSortMethod());                        //使用Collections.sort()方法，第二个参数为自定义排序方法，需要实现Comparator接口

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (MapClass m : mapclass)
            sortedMap.put(m.getKey(), m.getValue());
        return sortedMap;                                                        //用LinkedHashMap返回排好序的Map
    }

}
