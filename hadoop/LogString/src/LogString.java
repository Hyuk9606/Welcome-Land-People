
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LogString {
    static List<File> targetFiles = null;

    static String baseDir = "/home/j6c207/data/1649277359905-1";    //검색할 디렉토리
    static String save = "/home/j6c207/temp/stringinlog.txt";       //검색결과가 저장된 파일명
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        // 파일 객체 생성
        Path path = Paths.get(baseDir);

        // 캐릭터셋 지정
        Charset cs = StandardCharsets.UTF_8;

        // 새 파일 내용 담을 리스트
        List<String> newList = new ArrayList<String>();


        BufferedReader br = new BufferedReader(new FileReader(baseDir));

        String line = "";

        while((line = br.readLine())!=null){
            st = new StringTokenizer(line, " ");

            for(int j=0; j<6; j++) {
                st.nextToken();
            }
            String word = st.nextToken();
//            System.out.println(word7);

            newList.add(word);
        }


        // 새 파일 쓰기
        try{
            File file = new File(save);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            if(file.isFile() && file.canWrite()){
                for(String readLine : newList){
                    // 쓰기
                    bufferedWriter.write(readLine + " ");

                    // 개행문자 쓰기
//                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
            }
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("작업 완료");
    }
}
