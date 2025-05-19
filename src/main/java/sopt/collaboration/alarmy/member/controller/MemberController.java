package sopt.collaboration.alarmy.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.collaboration.alarmy.global.result.ResultCode;
import sopt.collaboration.alarmy.global.result.ResultResponse;
import sopt.collaboration.alarmy.member.service.MemberService;
import sopt.collaboration.alarmy.member.dto.response.PhraseResponse;

@RestController
@RequestMapping("/api/v1/phrase")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "오늘의 문장 조회",
            description = """
                24시간마다 랜덤으로 변경한 오늘의 문장을 조회합니다.
                userId는 Request Header로 전달해야 합니다.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "오늘의 문장 조회 성공"),
            @ApiResponse(responseCode = "400", description = "요청 헤더 값 오류"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저 / 잘못된 URL"),
            @ApiResponse(responseCode = "405", description = "지원하지 않는 HTTP 메서드"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 발생")
    })
    @GetMapping()
    public ResponseEntity<ResultResponse<PhraseResponse>> getTodayPhrase(
            @RequestHeader("userId") long userId
    ) {
        PhraseResponse response = memberService.getPhrase(userId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.TODAY_SENTENCE_FETCH_SUCCESS, response));
    }

}
