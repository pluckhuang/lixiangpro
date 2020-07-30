import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenBetween {
    // condition :
    static ArrayList<String> stringList = new ArrayList<>();

    public static String Between(String chars, String id1, String id2) {
        String rt = "";

        if (id1.equals(id2)) {
            System.out.println("Error: Impossible");
            return rt;
        }

        combine(chars, new StringBuilder(), 0);

        // 缩小范围
        int left = stringList.indexOf(id1);
        int right = stringList.indexOf(id2);

        List<String> candidatesList = stringList.subList(left, right + 1);

        System.out.println("字符组合为：" + stringList);
        System.out.println("候选值域为：" + candidatesList);

        // 获取值域中间值
        int nStrs = candidatesList.size();
        int middle = nStrs / 2;
        rt = candidatesList.get(middle);

        // 处理不重复情况下的可能临近值，构造重复值
        if (rt.equals(id2)) {
            rt = id1 + id1.substring(0, 1);
        }

        return rt;
    }

    public static String sortString(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    public static void combine(String instr, StringBuilder outstr, int index) {
        for (int i = index; i < instr.length(); i++) {
            outstr.append(instr.charAt(i));
            stringList.add(outstr.toString());
            combine(instr, outstr, i + 1);
            outstr.deleteCharAt(outstr.length() - 1);
        }
    }

    public static void main(String[] args) {
        String sortedString = sortString(args[0]);

        String ret = Between(sortedString, args[1], args[2]);

        if (!ret.isEmpty()) {
            System.out.println("可能的中间值为：" + ret);
        }

    }

}