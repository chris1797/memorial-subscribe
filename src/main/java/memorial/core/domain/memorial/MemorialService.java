package memorial.core.domain.memorial;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.core.api.request.MemorialRequestDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemorialService {

    private final MemorialRepository memorialRepository;

    @Transactional(rollbackOn = Exception.class)
    public Long save(MemorialRequestDto requestDto) {
        Memorial memorial = Memorial.of(requestDto);
        return memorialRepository.save(memorial).getId();
    }
}
