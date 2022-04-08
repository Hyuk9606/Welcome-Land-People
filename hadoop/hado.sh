#!/bin/sh

# remove all file(stringinlog.txt, hdfs wordcount_test_out directory)
rm temp/stringinlog.txt
rm temp/part-r-00000
hdfs dfs -rm wordcount_test/stringinlog.txt
hdfs dfs -rm -r wordcount_test_out

# extract the search term from the log file and execute the jar file created as stringinlog.txt file
java -jar LogString.jar

# stringinlog.txt file hdfs upload
hdfs dfs -put temp/stringinlog.txt wordcount_test

# execute mapreduce
hadoop jar ssafy.jar wordcount wordcount_test wordcount_test_out

# hdfs에 있는 part-r-00000 파일을 /home/j6c207/temp 디렉토리에 복사하기
hdfs dfs -get wordcount_test_out/part-r-00000 /home/j6c207/temp

# /temp/part-r-00000 파일을 랭킹 순으로 정렬한 출력 파일을 현재 디렉토리에 놓기
java -jar 3.Ranking.jar

#go to file was
scp -i /home/j6c207/keystored/J6C207T.pem result.txt ubuntu@172.26.1.86:/home/ubuntu/test/logfiles

#echo "hello hado.sh"
