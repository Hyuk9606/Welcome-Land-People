package com.ssafy.boonmoja.api.entity.joinTable;

import com.ssafy.boonmoja.api.entity.place.Place;
import com.ssafy.boonmoja.api.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPlace {
    
    @Id
    @Column(name = "USER_PLACE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userPlaceSeq;
    
    @ManyToOne
    @JoinColumn(name="USER_SEQ")
    private User user;

    @ManyToOne
    @JoinColumn(name="PLACE_SEQ")
    private Place place;
    
}
