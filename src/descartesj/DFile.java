/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package descartesj;


import java.nio.file.Files;
/**
 *
 * @author tupolev
 */
public class DFile {
private String path = "";
    private String name = "";
    private String ext = "";
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
        
    public DFile(String path, String name, String ext){
        this.path = path;
        this.name = name;
        this.ext = ext;
    }

    public Boolean verify() {
        return (new java.io.File(this.getPath() + "/" + this.getName())).exists();
    }

    public Boolean move(String destFileFullPath, Boolean keepCopy) {
        Boolean ret = true;
        try
        {
            if (keepCopy) {
                Files.copy( (new java.io.File(this.getFullName())).toPath(),
                        (new java.io.File(destFileFullPath)).toPath(), null);                    
            }else{
                Files.move( (new java.io.File(this.getFullName())).toPath(),
                        (new java.io.File(destFileFullPath)).toPath(), null);
            }
        }
        catch (Exception ex) {
            System.out.print(ex.getLocalizedMessage());
            ret = false;
        } 
        return ret;    
    }

    public String getFullName() {
        return this.getPath() + "/" + this.getName();
    }
    
    public static String getFileNameWithoutExtension(String fullpath) {
        if (fullpath == null) return null;
        int pos = fullpath.lastIndexOf(".");
        if (pos == -1) return fullpath;
        return fullpath.substring(0, pos);
        
    }
    
    public static String getExtension(String fullpath) {
        int pos = fullpath.lastIndexOf('.');
        return fullpath.substring(pos+1);
    }


}
