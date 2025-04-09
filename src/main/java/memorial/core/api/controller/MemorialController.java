package memorial.core.api.controller;

import lombok.RequiredArgsConstructor;
import memorial.core.api.ApiResponse;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.api.response.MemorialResponse;
import memorial.core.domain.memorial.MemorialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MemorialController {

    private final MemorialService memorialService;


    @PostMapping("/memorial")
    public ApiResponse<MemorialResponse> save(@RequestBody MemorialRequestDto requestDto) {
        return ApiResponse.success(memorialService.save(requestDto));
    }

}
