package co.istad.mbanking.api.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface FileService {
    /**
     * upload file Single
     * @param file
     * @return
     */
    FileDto uploadSingle(MultipartFile file);

    /**
     * upload file Multiple
     * @param files
     * @return
     */
    List <FileDto>uploadMultiple(List <MultipartFile> files);
}
