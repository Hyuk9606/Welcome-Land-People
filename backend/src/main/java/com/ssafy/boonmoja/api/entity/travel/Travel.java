package com.ssafy.boonmoja.api.entity.travel;

import com.ssafy.boonmoja.api.entity.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TRAVEL")
public class Travel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_SEQ")
    private Long travelSeq;
    
    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;
    
    @Column(name = "TRAVEL_TITLE")
    @NotNull
    private String travelTitle;
    
    @Column(name = "START_AT")
    private LocalDate startAt;
    
    @Column(name = "END_AT")
    private LocalDate endAt;
    
    @Column(name="IS_FINISHED")
    @Builder.Default
    private Boolean isFinished = false;
    
    @Builder.Default
    @OneToMany
    @JoinColumn(name="travel_seq")
    private List<Day> travelDays = new ArrayList<>();
    
    
    @Override
    public String toString() {
        return "Travel{" +
                "travelSeq=" + travelSeq +
                ", user=" + user +
                ", travelTitle='" + travelTitle + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", isFinished=" + isFinished +
                '}';
    }
}
