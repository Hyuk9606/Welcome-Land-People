# Python News Crawling TIL 3

2022-03-08

[[3탄] 쉽게 따라하는 네이버 뉴스 크롤링 - 본문 가져오기](https://everyday-tech.tistory.com/entry/3%ED%83%84-%EC%89%BD%EA%B2%8C-%EB%94%B0%EB%9D%BC%ED%95%98%EB%8A%94-%EB%84%A4%EC%9D%B4%EB%B2%84-%EB%89%B4%EC%8A%A4-%ED%81%AC%EB%A1%A4%EB%A7%81-%EB%B3%B8%EB%AC%B8-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0?category=922285)

### **TIL 3 목표**

네이버 뉴스의 title, url, **본문**을 가져오는 크롤링 방법 배우기

### 본문 크롤링이 어려운 이유와 해결 방법

- **본문 크롤링이 어려운 이유**
    - 언론사 마다 웹 페이지의 구성이 다름 (본문이 들어있는 tag, 속성 값 등등)
    - 모든 언론사의 웹 페이지 규칙을 알고 있다면 url 에 따라 어떤 언론사인지 확인하고 본문이 담긴 tag를 찾아 크롤링 가능하지만 너무 많은 시간이 들기 때문에 효율성이 떨어짐
- **해결 방법 1 : 네이버 뉴스 검색 시 제목 밑에 보이는 일부 기사 발췌**
    - 한계 : 본문 전체를 가져오지 못하기 때문에 큰 의미가 없음
- **해결 방법 2 : 일부 언론사 선택 후 기사 본문 크롤링**
    - 주로 보는 일부 언론사 선택 및 검색 후 해당 언론사의 title, url 만 크롤링
    - 언론사 별로 웹 페이지에서 본문이 위치한 tag 를 확인하고 해당 url 에 들어가서 본문 크롤링

### **해결 방법 2 에 관한 크롤링 방법 및 세부 수행 단계**

- **크롤링 방법**
    - selenium 을 이용한 동적 크롤링을 사용하여 언론사를 선택하여 검색된 뉴스 기사를 크롤링 하는 방법 사용
- **URL 조건 확인**
    
    ![Untitled](Python%20New%205ac95/Untitled.png)
    
    - “우크라이나” 키워드 검색 후 URL : 
    https://search.naver.com/search.naver?
    where=news&ie=utf8&sm=nws_hty&**query=우크라이나**
    - “국민일보” 언론사 선택 후 URL : 
    https://search.naver.com/search.naver?
    where=news&query=우크라이나&sm=tab_opt&sort=0&photo=0&field=0&pd=0&ds=&de=&docid=&related=0
    &mynews=1&office_type=1&office_section_code=1
    &**news_office_checked=1005**&nso=&is_sug_officeid=0
- **세부 수행 단계**
    - 환경 설정
    - STEP 1. 검색할 언론사 선택
    - STEP 2. 일부 언론사만 검색하는 기능
    - STEP 3. 선택한 언론사별 본문 tag 위치

---

### 환경 설정

- **Chrome 버전 확인**
    
    ![Untitled](Python%20New%205ac95/Untitled%201.png)
    
- **chromedriver.exe 설치**
    
    [ChromeDriver - WebDriver for Chrome - Downloads](https://chromedriver.chromium.org/downloads)
    
    ![Untitled](Python%20New%205ac95/Untitled%202.png)
    
    ![Untitled](Python%20New%205ac95/Untitled%203.png)
    

### STEP 1. 검색할 언론사 선택

- **KBS**

### STEP 2. 일부 언론사만 검색하는 기능

- **크롤링 방법**
    - selenium 패키지로 웹 브라우저를 제어하는 동적 크롤링을 사용
    - 언론사를 선택하여 뉴스 기사를 필터링
- **세부 수행 단계**
    - **url 에 키워드를 넣어 검색**
        - 검색 URL https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&**query=”키워드”**
        
        ![Untitled](Python%20New%205ac95/Untitled%204.png)
        
    - **“옵션” bar 활성화**
        - 옵션 바 선택 xpath : a[@class="btn_option _search_option_open_btn"]
        
        ![Untitled](Python%20New%205ac95/Untitled%205.png)
        
    - **“언론사 분류순” bar 열기**
        - 전체 박스 선택 xpath : div[@role="listbox" and @class="api_group_option_sort _search_option_detail_wrap"]//li[@class="bx press"]
        - 언론사 분류순 바 선택 xpath : div[@role="tablist" and @class="option"]/a
        
        ![Untitled](Python%20New%205ac95/Untitled%206.png)
        
    - **“언론사 종류” 선택**
        - 전체 박스 선택 xpath : div[@class="group_select _list_root"]
        - 언론사 종류 선택 xpath : ul[@role="tablist" and @class="lst_item _ul"]/li/a
        
        ![Untitled](Python%20New%205ac95/Untitled%207.png)
        
    - **원하는 언론사 선택**
        - 전체 박스 선택 xpath : div[@class="group_select _list_root"]
        - 언론사 선택 xpath : ul[@role="tablist" and @class="lst_item _ul"]/li/a
        
        ![Untitled](Python%20New%205ac95/Untitled%208.png)
        

### STEP 3. 선택한 언론사별 본문 tag 위치

- KBS
    - url : [http://news.kbs.co.kr/](http://news.kbs.co.kr/)
    - 본문을 담고 있는 태그 위치 : div[@id=“cont_newstext”]
        
        ![Untitled](Python%20New%205ac95/Untitled%209.png)
        

### 소스코드 및 결과

```python
import sys, os
import requests
import selenium
from selenium import webdriver
import requests
from pandas import DataFrame
from bs4 import BeautifulSoup
import re
from datetime import datetime
import pickle, progressbar, json, glob, time
from tqdm import tqdm

###### 날짜 저장 ##########
date = str(datetime.now())
date = date[:date.rfind(':')].replace(' ', '_')
date = date.replace(':','시') + '분'

sleep_sec = 0.5

####### 언론사별 본문 위치 태그 파싱 함수 ###########
print('본문 크롤링에 필요한 함수를 로딩하고 있습니다...\n' + '-' * 100)
def crawling_main_text(url):

    req = requests.get(url)
    req.encoding = None
    soup = BeautifulSoup(req.text, 'html.parser')
    
    # 연합뉴스
    if ('://yna' in url) | ('app.yonhapnews' in url): 
        main_article = soup.find('div', {'class':'story-news article'})
        if main_article == None:
            main_article = soup.find('div', {'class' : 'article-txt'})
            
        text = main_article.text
        
    # MBC 
    elif '//imnews.imbc' in url: 
        text = soup.find('div', {'itemprop' : 'articleBody'}).text
        
    # 매일경제(미라클), req.encoding = None 설정 필요
    elif 'mirakle.mk' in url:
        text = soup.find('div', {'class' : 'view_txt'}).text
        
    # 매일경제, req.encoding = None 설정 필요
    elif 'mk.co' in url:
        text = soup.find('div', {'class' : 'art_txt'}).text
        
    # SBS
    elif 'news.sbs' in url:
        text = soup.find('div', {'itemprop' : 'articleBody'}).text
    
    # KBS
    elif 'news.kbs' in url:
        text = soup.find('div', {'id' : 'cont_newstext'}).text
        
    # JTBC
    elif 'news.jtbc' in url:
        text = soup.find('div', {'class' : 'article_content'}).text
        
    # 그 외
    else:
        text == None
        
    return text.replace('\n','').replace('\r','').replace('<br>','').replace('\t','')
    
    
press_nm = 'KBS'

print('검색할 언론사 : {}'.format(press_nm))

############### 브라우저를 켜고 검색 키워드 입력 ####################
query = input('검색할 키워드  : ')
news_num = int(input('수집 뉴스의 수(숫자만 입력) : '))

print('\n' + '=' * 100 + '\n')

print('브라우저를 실행시킵니다(자동 제어)\n')
chrome_path = 'C:/chromedriver/chromedriver.exe'
browser = webdriver.Chrome(chrome_path)

news_url = 'https://search.naver.com/search.naver?where=news&query={}'.format(query)
browser.get(news_url)
time.sleep(sleep_sec)

######### 언론사 선택 및 confirm #####################
print('설정한 언론사를 선택합니다.\n')

search_opn_btn = browser.find_element_by_xpath('//a[@class="btn_option _search_option_open_btn"]')
search_opn_btn.click()
time.sleep(sleep_sec)

bx_press = browser.find_element_by_xpath('//div[@role="listbox" and @class="api_group_option_sort _search_option_detail_wrap"]//li[@class="bx press"]')

# 기준 두번 째(언론사 분류순) 클릭하고 오픈하기
press_tablist = bx_press.find_elements_by_xpath('.//div[@role="tablist" and @class="option"]/a')
press_tablist[1].click()
time.sleep(sleep_sec)

# 첫 번째 것(언론사 분류선택)
bx_group = bx_press.find_elements_by_xpath('.//div[@class="api_select_option type_group _category_select_layer"]/div[@class="select_wrap _root"]')[0]

press_kind_bx = bx_group.find_elements_by_xpath('.//div[@class="group_select _list_root"]')[0]
press_kind_btn_list = press_kind_bx.find_elements_by_xpath('.//ul[@role="tablist" and @class="lst_item _ul"]/li/a')

for press_kind_btn in press_kind_btn_list:
    
    # 언론사 종류를 순차적으로 클릭(좌측)
    press_kind_btn.click()
    time.sleep(sleep_sec)
    
    # 언론사선택(우측)
    press_slct_bx = bx_group.find_elements_by_xpath('.//div[@class="group_select _list_root"]')[1]
    # 언론사 선택할 수 있는 클릭 버튼
    press_slct_btn_list = press_slct_bx.find_elements_by_xpath('.//ul[@role="tablist" and @class="lst_item _ul"]/li/a')
    # 언론사 이름들 추출
    press_slct_btn_list_nm = [psl.text for psl in press_slct_btn_list]
    
    # 언론사 이름 : 언론사 클릭 버튼 인 딕셔너리 생성
    press_slct_btn_dict = dict(zip(press_slct_btn_list_nm, press_slct_btn_list))
    
    # 원하는 언론사가 해당 이름 안에 있는 경우
    # 1) 클릭하고
    # 2) 더이상 언론사분류선택 탐색 중지
    if press_nm in press_slct_btn_dict.keys():
        print('<{}> 카테고리에서 <{}>를 찾았으므로 탐색을 종료합니다'.format(press_kind_btn.text, press_nm))
        
        press_slct_btn_dict[press_nm].click()
        time.sleep(sleep_sec)
        
        break

################ 뉴스 크롤링 ########################

print('\n크롤링을 시작합니다.')
# ####동적 제어로 페이지 넘어가며 크롤링
news_dict = {}
idx = 1
cur_page = 1

pbar = tqdm(total=news_num ,leave = True)
    
while idx < news_num:

    table = browser.find_element_by_xpath('//ul[@class="list_news"]')
    li_list = table.find_elements_by_xpath('./li[contains(@id, "sp_nws")]')
    area_list = [li.find_element_by_xpath('.//div[@class="news_area"]') for li in li_list]
    a_list = [area.find_element_by_xpath('.//a[@class="news_tit"]') for area in area_list]
 
    for n in a_list[:min(len(a_list), news_num-idx+1)]:
        n_url = n.get_attribute('href')
        news_dict[idx] = {'title' : n.get_attribute('title'), 
                          'url' : n_url,
                          'text' : crawling_main_text(n_url)}
        
        idx += 1
        pbar.update(1)
        
    if idx < news_num:
        cur_page +=1

        pages = browser.find_element_by_xpath('//div[@class="sc_page_inner"]')
        next_page_url = [p for p in pages.find_elements_by_xpath('.//a') if p.text == str(cur_page)][0].get_attribute('href')

        browser.get(next_page_url)
        time.sleep(sleep_sec)
    else:
        pbar.close()
        
        print('\n브라우저를 종료합니다.\n' + '=' * 100)
        time.sleep(0.7)
        browser.close()
        break

#### 데이터 전처리하기 ###################################################### 

print('데이터프레임 변환\n')
news_df = DataFrame(news_dict).T

folder_path = os.getcwd()
xlsx_file_name = '네이버뉴스_본문_{}개_{}_{}.xlsx'.format(news_num, query, date)

news_df.to_excel(xlsx_file_name)

print('엑셀 저장 완료 | 경로 : {}\\{}\n'.format(folder_path, xlsx_file_name))

os.startfile(folder_path)

print('=' * 100 + '\n결과물의 일부')
news_df
```

헐 개신기해 미쳐따

![Untitled](Python%20New%205ac95/Untitled%2010.png)

![Untitled](Python%20New%205ac95/Untitled%2011.png)

![Untitled](Python%20New%205ac95/Untitled%2012.png)

### **자잘한 python setting**

- **selenium**
    
    ![Untitled](Python%20New%205ac95/Untitled%2013.png)
    
    ![Untitled](Python%20New%205ac95/Untitled%2014.png)
    
- **progressbar**
    
    ![Untitled](Python%20New%205ac95/Untitled%2015.png)
    
- **tqdm**
    
    ![Untitled](Python%20New%205ac95/Untitled%2016.png)
    
    ![Untitled](Python%20New%205ac95/Untitled%2017.png)