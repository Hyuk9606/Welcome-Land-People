package com.ssafy.boonmoja.api.entity.place;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "PLACE")
public class Place {
    
    @Id
    @Column(name = "PLACE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeSeq;
    
    @Column(name = "PLACE_NAME")
    private String placeName;
    
//    @OneToMany(mappedBy = "place")
//    private List<UserPlace> userPlaces = new ArrayList<>();
//
}
