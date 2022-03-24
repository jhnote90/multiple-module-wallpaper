package bmi.mmw.controller;

import bmi.mmw.domain.Memo;
import bmi.mmw.domain.Module;
import bmi.mmw.dto.*;
import bmi.mmw.service.MemoService;
import bmi.mmw.service.SearchService;
import bmi.mmw.service.UserModuleService;
import bmi.mmw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserModuleService userModuleService;
    private final SearchService searchService;
    private final MemoService memoService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/api/v1/user", consumes = "application/json")
    public UserInfoDto save(@RequestBody UserDto userDto) {
        UserInfoDto userInfoDto = userService.join(userDto);
        return userInfoDto;
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/api/v1/user_module", consumes = "application/json")
    public void updateUserModule(@RequestBody UserModuleDto userModuleDto) {
        Long moduleId = Long.valueOf(userModuleDto.getModuleId());
        if (userModuleDto.getOn() == 1)
            userModuleService.addUserModule(userModuleDto.getId(), moduleId);
        else if (userModuleDto.getOn() == 0) {
            userModuleService.removeUserModule(userModuleDto.getId(), moduleId);
        }
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/api/v1/search", consumes = "application/json")
    public void saveSearchInfo(@RequestBody SearchDto searchDto) {
        searchService.save(searchDto.getId(), searchDto.getContent());
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/api/v1/theme", consumes = "application/json")
    public void updateTheme(@RequestBody ThemeDto themeDto) {
        String theme = userService.updateTheme(themeDto.getId(), themeDto.getContent());
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/api/v1/memo", consumes = "application/json")
    public void updateMemo(@RequestBody MemoDto memoDto) {
        if (memoDto.getOn() == 1)
            memoService.addMemo(memoDto.getId(), memoDto.getContent());
        else if (memoDto.getOn() == 0)
            memoService.removeMemo(memoDto.getId(), memoDto.getContent());
    }
}
