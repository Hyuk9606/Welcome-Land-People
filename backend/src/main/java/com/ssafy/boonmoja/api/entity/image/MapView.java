package com.ssafy.boonmoja.api.entity.image;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/*
@Data는!?!
@Setter
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor //final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만들어줌
를 합쳐놓은 어노테이션
 */
@Document(collection = "mapviews")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MapView {

    @Id
    private String id;

    private Integer loc;

    @Enumerated(EnumType.STRING)
    private String mapType;

    @Nullable
    private String color;

    @Nullable
    private Binary image;


    // CurrentUser
//    @NotNull
    private String user;

    public MapView(Integer loc, String mapType, String user){
        this.loc = loc;
        this.mapType = mapType;
        this.user = user;
    }


//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "userSeq", unique = true)
//    private User user;

//    public MapView(MapViewDto mapViewDto){
//        this.locId = mapViewDto.getLocId();
//        this.mapType = mapViewDto.getMapType();
//        this.image = mapViewDto.getImage();
//        this.color = mapViewDto.getColor();
//    }

}
