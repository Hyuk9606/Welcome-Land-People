import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class RankingWord {

    static List<File> targetFiles = null;

    static String baseDir = "/home/j6c207/temp/part-r-00000";    //검색할 디렉토리
    static String save = "/home/j6c207/result.txt";       //검색결과가 저장된 파일명
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        // 파일 객체 생성
        Path path = Paths.get(baseDir);

        // 캐릭터셋 지정
        Charset cs = StandardCharsets.UTF_8;

        // entrySet() : key & value, keySet() : key, get(word) : value
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        BufferedReader br = new BufferedReader(new FileReader(baseDir));

        String line = "";

        while((line = br.readLine())!=null){
            st = new StringTokenizer(line, "\t");

            String word = st.nextToken();
//            System.out.println(word);
            int rank = Integer.parseInt(st.nextToken());
//            System.out.println(rank);

            map.put(word, rank);
//            System.out.println(map.keySet());
        }

//        System.out.println(map.entrySet());

        // 새 파일 내용 담을 리스트
        List<String> newList = new LinkedList<>(map.keySet());

        Collections.sort(newList, (value1, value2) -> (map.get(value2).compareTo(map.get(value1))));

//        for(String key : newList){
//            System.out.println(key + " , " + map.get(key));
//        }

        // 새 파일 쓰기
        try{
            File file = new File(save);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            if(file.isFile() && file.canWrite()){
                int index = 1;
                for(String readLine : newList){
                    // 쓰기
                    bufferedWriter.write(index + " " + readLine + " ");
                    index++;
                    // 개행문자 쓰기
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
            }
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("작업 완료");
    }
    }
