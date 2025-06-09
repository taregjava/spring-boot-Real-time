package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.ClubDTO;
import com.halfacode.spring_real_time_learning.entity.ClubEntity;
import com.halfacode.spring_real_time_learning.exception.UserNotFoundException;
import com.halfacode.spring_real_time_learning.repo.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ModelMapper modelMapper;
    private final ClubRepository clubRepository;


    public List<ClubDTO> findAll() {
        return this.clubRepository.findAll().stream().map(entity -> this.modelMapper.map(entity, ClubDTO.class)).toList();
    }


    public ClubDTO findById(Long id) {
        ClubEntity userEntity = this.clubRepository.findById(id).orElseGet(ClubEntity::new);
        return this.modelMapper.map(userEntity, ClubDTO.class);
    }


    public ClubDTO save(ClubDTO clubDTO) {
        ClubEntity userEntity = this.modelMapper.map(clubDTO, ClubEntity.class);

        ClubEntity userSaved = this.clubRepository.save(userEntity);

        return this.modelMapper.map(userSaved, ClubDTO.class);
    }


    public ClubDTO updateClub(Long id, ClubDTO clubDTO) {

        ClubEntity currentClubEntity = this.clubRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " doesnt exist."));

        currentClubEntity.setName(clubDTO.getName());
        currentClubEntity.setCountry(clubDTO.getCountry());

        ClubEntity userUpdated = this.clubRepository.save(currentClubEntity);

        return this.modelMapper.map(userUpdated, ClubDTO.class);
    }


    public String deleteClub(Long id) {

        ClubEntity currentClubEntity = this.clubRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " doesnt exist."));

        this.clubRepository.delete(currentClubEntity);

        return "User correctly removed.";
    }

}
