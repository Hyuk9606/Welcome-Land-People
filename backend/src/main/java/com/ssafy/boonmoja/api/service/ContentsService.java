package com.ssafy.boonmoja.api.service;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.repository.contents.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentsService {
    private final ContentsRepository contentsRepository;
    
    public List<Contents> getContentsEqualLabel(String label) {
        return contentsRepository.findByLabelIs(label);
    }
    
    public List<Contents> getContentsEqualLabelAndAddress(String label, String address) {
        return contentsRepository.findByLabelStartingWithAndAddressContaining(label, address);
    }
    public List<Contents> getContentsEqualLabelAndTitle(String label, String title) {
        return contentsRepository.findByLabelStartingWithAndTitleContaining(label, title);
    }
}
