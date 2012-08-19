/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package descartesj;

import java.io.File;
import java.net.FileNameMap;
import java.nio.file.*;
import java.util.*;

/**
 *
 * @author tupolev
 */
public class DImage {
    private String status = "unrated";
    private List<DFile> fileList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<DFile> fileList) {
        this.fileList = fileList;
    }

    public String getFileNameWithoutExtension(String path){        
        String fileName = null;
        String separator = File.separator;

        int pos = path.lastIndexOf(separator);
        int pos2 = path.lastIndexOf(".");

        if(pos2>-1){
         fileName =path.substring(pos+1, pos2);
        }else{
         fileName =path.substring(pos+1);
        }
        return fileName;
   }
    
    
        public String getFileTitle() {
            return this.getFileNameWithoutExtension(this.getFileList().get(0).getName());
            
        }

        public List<DFile> getFiles()
        {
            return this.getFileList();
        }
            
        public DImage(List<DFile> fileList) {
            this.setFileList(fileList);
        }
      
        public Boolean isImage() {
            return true;
        }    
}
