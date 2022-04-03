package com.ssafy.boonmoja.api.entity.joinTable;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.travel.Day;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class DayContents {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAY_CONTENTS_SEQ")
    private Long dayContentsSeq;
    
    
    @ManyToOne
    @JoinColumn(name="DAY_SEQ")
    @NotNull
    private Day day;
    
//    @ManyToOne
//    @JoinColumn(name="CONTENTS_ID")
//    @NotNull
//    private Contents contents;
    
    @ManyToOne
    @JoinColumn(name="title", referencedColumnName = "title")
    @NotNull
    private Contents contents;
    
    @Column(name="DAY_CONTENTS_NO")
    Integer dayContentsNo;
    
}
