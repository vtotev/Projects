import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.stream.Collectors;



 /*
        There is a string "sdfgabcwetrrytruyrtuabcpotre!@#abcprtort".
        The task is to implement the following method:

        private HashMap<string, string> processString(string inputStr, string separator);

        The result needs to contain the following keys:
        Count : count all substrings (itemstrings)  infront of which there is a separator string (if xxx is the string and A is the separator here: xxxAxxxAxxxAxxx, you need to return 3);
        prefix : if any string exists before the first separator, please provide the text
        sortedItems : a string with all itemstrings concatinated in alphabetical order
        evenChars : a string with concatinated all even indexed chars (2,4,6,8,10th)

		note: if there is no separator found in input string then whole inputString is counted as 1 itemString

		Example output when executed with inputString = "abcdefSEPgabcwetSEPsdsSEPdsfgSEPfro", separator = "SEP"

		Count: 4
		Prefix: abcdef
		sortedItems: dsfg fro gabcwet sds
		evenChars: aceSPaceSPdSPsgEfo
    */

class Main {
    public static void main(String[] args) {
        String inputString = "sdfgabcwetrrytruyrtuabcpotre!@#abcprtort";
        ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();

        resultList.add(processString(inputString,"abc"));
        resultList.add(processString(inputString,"s"));
        resultList.add(processString(inputString,"r"));
        resultList.add(processString(inputString,"zi"));

        // for testing
        resultList.add(processString("abcdefSEPgabcwetSEPsdsSEPdsfgSEPfro", "SEP"));

        printResult(resultList);
    }

    private static HashMap<String, String> processString(String inputStr, String separator)
    {
        HashMap<String, String> result = new HashMap<String, String>();
        String[] data = inputStr.split(separator);

        int startIndex = inputStr.indexOf(separator);
        if (startIndex < 0) {
            startIndex = 0;
        }
        String prefix = inputStr.substring(0, startIndex);

        int count = data.length;
        if (startIndex > 0) {
            count--;
        }
        String evenChars = "";
        for (int i = 0; i < inputStr.length(); i++) {
            if (i % 2 == 0) {
                evenChars += inputStr.charAt(i);
            }
        }
        List<String> sorted = new ArrayList<>();
        if (startIndex > 0) {
            for (int i = 1; i < data.length; i++) {
                sorted.add(data[i]);
            }
        } else {
            Arrays.stream(data).forEach(sorted::add);
        }
        sorted.sort(String::compareTo);

        StringBuilder sb = new StringBuilder();
        sb.append("Count: " + count).append(System.lineSeparator())
                .append("Prefix: " + inputStr.substring(0, startIndex)).append(System.lineSeparator())
                .append("sortedItems: " + String.join(" ", sorted)).append(System
                        .lineSeparator())
                .append("evenChars: " + evenChars).append(System.lineSeparator());

        //Add the implementation here
        result.put(separator, sb.toString());
        return result;
    }

    private static void printResult(ArrayList<HashMap<String, String>> resultList) {
        /*
    		Below is an example output when executed with inputString = "abcdefSEPgabcwetSEPsdsSEPdsfgSEPfro", separator = "SEP"

    		Count: 4
    		Prefix: abcdef
    		sortedItems: dsfg fro gabcwet sds
    		evenChars: aceSPaceSPdSPsgEfo
		*/

        //Add the implementation here
        resultList.forEach(r -> r.forEach((s, s2) -> System.out.println(s2)));
    }

}
