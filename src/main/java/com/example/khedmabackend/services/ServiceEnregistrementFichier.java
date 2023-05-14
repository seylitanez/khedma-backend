package com.example.khedmabackend.services;

import com.example.khedmabackend.repo.UtilisateurRepo;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ServiceEnregistrementFichier {
    @Autowired
    private final UtilisateurRepo utilisateurRepo;

    public void addCv(MultipartFile file) throws IOException {
        var me=SecurityContextHolder.getContext().getAuthentication().getName();
        var myAccount =utilisateurRepo.findByadresseMail(me).orElseThrow();
        var myId= myAccount.getId();


        File folder = new File("images/"+myId);
        if(folder.exists()&& folder.listFiles()[0]!=null){
            folder.listFiles()[0].delete();
        }



        IOUtils.copy(file.getInputStream(),new FileOutputStream("images/"+myId+"/"+file.getOriginalFilename()));



    }

}
