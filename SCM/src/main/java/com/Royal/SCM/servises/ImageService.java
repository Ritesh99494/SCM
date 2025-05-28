package com.Royal.SCM.servises;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile contactImage, String filename);

    String getUrlFromPublicId(String publicId);

}
