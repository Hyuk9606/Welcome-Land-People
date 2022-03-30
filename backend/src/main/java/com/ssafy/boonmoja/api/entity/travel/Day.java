package com.ssafy.boonmoja.api.entity.travel;

import com.ssafy.boonmoja.api.entity.joinTable.DayContents;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Day {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daySeq;
    
    @ManyToOne
    @JoinColumn(name = "TRAVEL_SEQ")
    private Travel travel;
    
    @Column(name = "DAY")
    @Builder.Default
    private Integer day = 1;
    
    @Builder.Default
    @OneToMany(mappedBy = "day")
    private List<DayContents> dayContents = new ArrayList<>();

}
