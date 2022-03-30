package com.ssafy.boonmoja.fileupload.model;

import com.mongodb.lang.Nullable;
import com.ssafy.boonmoja.api.entity.travel.Day;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.fileupload.dto.ReviewDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

/*
1. Database
- 데이터베이스는 컬렉션의 물리적 컨테이너 입니다. 하나의 데이터베이스에는 보통 여러개의 컬렉션을 가지고 있습니다.

2. Collection
- 컬렉션은 몽고DB Document 의 그룹이며 RDBMS 의 예를 들면 Table 과 개념과 유사합니다.
- 컬렉션은 단일 데이터베이스에 존재합니다.
- 컬렉션은 스키마를 강요하지 않습니다. 따라서 컬렉션 내부의 도큐먼트는 서로 다른 필드를 가질수 있습니다.
- 컬렉션 안에 도큐먼트는 일반적으로 서로 유사한 하거나 관련된 목적이 있습니다.

3. Document
- Docuemtn 는 하나의 키(key) 와 값(value)의 집합으로 이루어져 있으며 동적 스키마 입니다.
- 동적 스키마는 동일한 컬랙션 내의 도큐먼트가 동일한 필드 또는 구조를 가지필요 없을을 의미한다.
- 그리고 동일한 필드안에 다른타입의 데이타를 보유할수 있음을 의미합니다.
출처: https://javacpro.tistory.com/66 [버물리의 IT공부]
 */

@Document(collection = "reviews")
@Setter
@Getter
@NoArgsConstructor
public class Review {

//MongoDB는 SQL Database같은 auto-increment를 제공하지 않는다

    @Id
    private String id;

    @Nullable
    private String text;

    @Nullable
    private List<Binary> image;

    @Column(unique = true)
    @NotNull
    private Long travel; //FK


    public Review(Long travel, String text) {
        this.travel = travel;
        this.text = text;
    }
    public Review(ReviewDto reviewDto) {
        this.text = reviewDto.getText();
    }

    public void updateText(ReviewDto reviewDto) {
        this.text = reviewDto.getText();
    }


}
