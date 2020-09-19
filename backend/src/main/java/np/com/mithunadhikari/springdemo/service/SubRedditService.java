package np.com.mithunadhikari.springdemo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.mithunadhikari.springdemo.dto.SubRedditDto;
import np.com.mithunadhikari.springdemo.model.SubRedditModel;
import np.com.mithunadhikari.springdemo.repository.SubRedditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class SubRedditService {

    private final SubRedditRepository redditRepository;

    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto) {
        SubRedditModel subRedditModel = mapSubRedditToSubRedditModel(subRedditDto);
        SubRedditModel savedData = redditRepository.save(subRedditModel);
        subRedditDto.setId(savedData.getId());
        return subRedditDto;
    }


    private SubRedditModel mapSubRedditToSubRedditModel(SubRedditDto dto) {

        return SubRedditModel.builder().name(dto.getName())
                .description(dto.getDescription())
                .build();
    }


    @Transactional(readOnly = true)
    public List<SubRedditDto> getAll() {

        return redditRepository.findAll()
                .stream()
                .map(this::mapSubRedditModelToSubRedditDto)
                .collect(Collectors.toList());

    }

    private SubRedditDto mapSubRedditModelToSubRedditDto(SubRedditModel model) {

        return SubRedditDto.builder()
                .name(model.getName())
//                .description(model.getDescription())
                .id(model.getId())
                .numberOfPosts(model.getPosts().size())
                .build();
    }
}
