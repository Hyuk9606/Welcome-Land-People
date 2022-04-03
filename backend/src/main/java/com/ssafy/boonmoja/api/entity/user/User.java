package com.ssafy.boonmoja.api.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.boonmoja.api.entity.joinTable.UserContents;
import com.ssafy.boonmoja.api.entity.travel.Travel;
import com.ssafy.boonmoja.oauth.entity.ProviderType;
import com.ssafy.boonmoja.oauth.entity.RoleType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "USER")
public class User {
    //    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSeq;

    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    //    @Column(name = "EMAIL", length = 512, unique = true)
    @Column(name = "EMAIL", length = 512)
    @NotNull
    @Size(max = 512)
    private String email;

//    @Column(name = "PROFILE_IMAGE_URL", length = 512)
//    @NotNull
//    @Size(max = 512)
//    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "CREATED_AT")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserContents> userContents = new ArrayList<>();

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "user_seq")
    private List<Travel> userTravels = new ArrayList<>();


    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
//            @NotNull @Size(max = 512) String profileImageUrl,
            ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt,
            List<UserContents> userContents
    ) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
//        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userContents = userContents;
    }

    @Override
    public String toString() {
        return "User{" +
                "userSeq=" + userSeq +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", providerType=" + providerType +
                ", roleType=" + roleType +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", userContents=" + userContents +
                '}';
    }
}
