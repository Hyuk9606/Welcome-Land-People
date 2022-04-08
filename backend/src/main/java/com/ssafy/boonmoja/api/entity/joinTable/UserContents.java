package com.ssafy.boonmoja.api.entity.joinTable;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER_CONTENTS")
public class UserContents {
    
    @Id
    @Column(name = "USER_CONTENTS_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userContentsSeq;
    
    @ManyToOne
    @JoinColumn(name="USER_SEQ")
    private User user;

    @ManyToOne
    @JoinColumn(name="CONTENTS_ID")
    private Contents contents;
    
    @Override
    public String toString() {
        return "UserContents{" +
                "userContentsSeq=" + userContentsSeq +
                ", user=" + user +
                ", contents=" + contents +
                '}';
    }
}
