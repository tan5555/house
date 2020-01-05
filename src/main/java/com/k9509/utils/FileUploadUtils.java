package com.k9509.utils;

import com.k9509.domain.Users;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by Admin on 2019/12/30.
 */
public class FileUploadUtils {

    public static void savepath(CommonsMultipartFile pfile){
        String pfileName = pfile.getOriginalFilename();
        //文件名后缀
        String sfix = pfileName.substring(pfileName.lastIndexOf("."));
        String pfix = System.currentTimeMillis()+"";

        String saveName=pfix+sfix;
        String savePath="H:\\java\\Java\\U04\\img\\"+saveName;
    }
}
