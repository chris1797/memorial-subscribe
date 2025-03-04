package memorial.core.domain.memorial;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.api.response.MemorialResponseDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemorialService {

    @Transactional(rollbackOn = Exception.class)
    public MemorialResponseDto save(MemorialRequestDto requestDto) {
        return null;
    }
}
