package com.ssafy.boonmoja.api.repository.contents;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {
    Contents findByContentsIdIs(String contentsId);
    List<Contents> findByLabelIs(String label);
    List<Contents> findByLabelStartingWithAndAddressContaining(String label,String address);
    List<Contents> findByLabelStartingWithAndTitleContaining(String label,String title);

}
