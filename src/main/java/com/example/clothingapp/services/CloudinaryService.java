package com.example.clothingapp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CloudinaryService {

    String uploadFile(MultipartFile image);
}
