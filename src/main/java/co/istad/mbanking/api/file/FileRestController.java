package co.istad.mbanking.api.file;

import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/file")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;
//    @Autowired
//    public FileService getFileService() {
//        return fileService;
//    }

    @PostMapping
    public BaseRest  <?> uploadSingle(@RequestPart MultipartFile file){
        log.info("File Request ={}",file);

        FileDto fileDto = fileService.uploadSingle(file);
        return  BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been Upload")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();

    }
}
