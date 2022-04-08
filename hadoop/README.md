#### Hadoop Cluster Directory

- /data : log파일 쌓이는 곳

- /temp 

  - stringinlog.txt : 로그에서 검색어 추출
  - part-r-00000 : hdfs에서 맵리듀스 후 생긴 wordcounting 된 part-r-00000 파일 복사

  

#### Directory & File Explain

- LogString : 로그에서 검색어 스트링 추출

- 3.Ranking : 맵리듀스 후 카운팅 된 숫자를 기준으로 정렬

- ssafy.jar : 맵리듀스

- hado.sh : 로그를 맵리듀스를 통하여 랭킹 정렬한 파일을 백엔드 서버에 파일 전송해주는 쉘 스크립트
  - hado.sh 쉘 스크립트 실행

    ```shell
    $ sh hado.sh
    ```

    



