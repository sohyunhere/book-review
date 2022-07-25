package com.example.bookreview.dto;

import com.example.bookreview.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class SignupDto {

    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}",
            message = "올바르지 않은 이메일 형식입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
    private String password;
    @NotEmpty
    private String password2;
    @NotEmpty
    private String nickname;

    public Member toEntity() {
        return Member.builder()
                .memberEmail(email)
                .memberPassword(password)
                .memberNickname(nickname)
                .build();
    }
}
