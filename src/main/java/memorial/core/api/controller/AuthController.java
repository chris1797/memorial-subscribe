package memorial.core.api.controller;

import lombok.RequiredArgsConstructor;
import memorial.core.api.ApiResponse;
import memorial.core.api.request.LoginRequestDto;
import memorial.core.api.request.SignUpRequest;
import memorial.core.api.response.LoginResponse;
import memorial.core.api.response.SignUpResponse;
import memorial.core.domain.member.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;


    @PostMapping("/signUp")
    public ApiResponse<SignUpResponse> signup(SignUpRequest signUpRequest) {
        return ApiResponse.success(memberService.signUp(signUpRequest));
    }

    @GetMapping("/login")
    public ApiResponse<LoginResponse> login(LoginRequestDto loginRequestDto) {
        return ApiResponse.success(memberService.login(loginRequestDto));
    }
}
